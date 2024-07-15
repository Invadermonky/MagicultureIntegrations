package com.invadermonky.magicultureintegrations.integrations.thaumcraft.events;

import com.invadermonky.magicultureintegrations.api.events.ITileTickEvent;
import com.invadermonky.magicultureintegrations.api.tile.IHeatableTile;
import com.invadermonky.magicultureintegrations.util.ReflectionHelper;
import gnu.trove.map.hash.THashMap;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import thaumcraft.common.lib.utils.BlockStateUtils;
import thaumcraft.common.tiles.devices.TileBellows;

public class TCBellowsHandler implements ITileTickEvent {
    public static THashMap<Class<? extends TileEntity>, Class<? extends IHeatableTile>> tcHeatableMap = new THashMap<>();

    public static void registerBellowsHeatable(Class<? extends TileEntity> tileClass, Class<? extends IHeatableTile> heatableClass) {
        tcHeatableMap.put(tileClass, heatableClass);
    }

    public static boolean isHeatableTile(TileEntity tile) {
        return tile != null && (tcHeatableMap.containsKey(tile.getClass()) || tcHeatableMap.containsKey(tile.getClass().getSuperclass()));
    }

    @Override
    public void onTileEntityTick(World world, TileEntity tile) {
        if(world.isRemote || world.getTotalWorldTime() % 4L != 0)
            return;

        TileBellows bellows = (TileBellows) tile;
        TileEntity facingTile = world.getTileEntity(bellows.getPos().offset(BlockStateUtils.getFacing(bellows.getBlockMetadata())));
        if(!BlockStateUtils.isEnabled(bellows.getBlockMetadata()) || !isHeatableTile(facingTile))
            return;

        try {
            IHeatableTile heatable = ReflectionHelper.getIHeatableInstance(tcHeatableMap, facingTile);
            if(heatable.getBurnTime() > 0) {
                heatable.boostCookTime(1);
                heatable.updateTile();
            }
        } catch (Exception ignored) {}
    }

    @Override
    public boolean checkTile(TileEntity tile) {
        return tile instanceof TileBellows;
    }
}
