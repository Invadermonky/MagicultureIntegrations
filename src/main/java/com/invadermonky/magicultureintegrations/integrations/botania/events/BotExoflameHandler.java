package com.invadermonky.magicultureintegrations.integrations.botania.events;

import com.invadermonky.magicultureintegrations.api.events.ITileTickEvent;
import com.invadermonky.magicultureintegrations.api.mods.botania.BotaniaUtils;
import com.invadermonky.magicultureintegrations.api.tile.IHeatableTile;
import com.invadermonky.magicultureintegrations.util.ReflectionHelper;
import gnu.trove.map.hash.THashMap;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import vazkii.botania.common.block.subtile.functional.SubTileExoflame;
import vazkii.botania.common.block.tile.TileSpecialFlower;

public class BotExoflameHandler implements ITileTickEvent {
    public static THashMap<Class<? extends TileEntity>, Class<? extends IHeatableTile>> exoflameHeatableMap = new THashMap<>();

    public static void registerExoflameHeatable(Class<? extends TileEntity> tileClass, Class<? extends IHeatableTile> heatableClass) {
        exoflameHeatableMap.put(tileClass, heatableClass);
    }

    private static boolean isHeatableTile(TileEntity tile) {
        return tile != null && (exoflameHeatableMap.containsKey(tile.getClass()) || exoflameHeatableMap.containsKey(tile.getClass().getSuperclass()));
    }

    @Override
    public void onTileEntityTick(World world, TileEntity tile) {
        if(world.isRemote)
            return;

        boolean did = false;

        TileSpecialFlower flower = (TileSpecialFlower) tile;
        SubTileExoflame exoflame = (SubTileExoflame) flower.getSubTile();
        BlockPos flowerPos = flower.getPos();

        for(BlockPos checkPos : BotaniaUtils.getExoflameArea(world, flowerPos)) {
            try {
                TileEntity checkTile = world.getTileEntity(checkPos);

                if(checkTile == null)
                    continue;

                if(!isHeatableTile(checkTile))
                    continue;

                IHeatableTile heatable = ReflectionHelper.getIHeatableInstance(exoflameHeatableMap, checkTile);
                if(heatable.canSmelt() && exoflame.mana > 2) {
                    if(heatable.getBurnTime() < 2) {
                        heatable.boostBurnTime(200);
                        exoflame.mana = Math.max(0, exoflame.mana - 300);
                    }

                    if(exoflame.ticksExisted % 2 == 0) {
                        heatable.boostCookTime(1);
                    }
                    did = true;
                }

                if(did)
                    heatable.updateTile();

                if(exoflame.mana <= 0)
                    break;
            } catch (Exception ignored) {}
        }

        if(did)
            exoflame.sync();
    }

    @Override
    public boolean checkTile(TileEntity tile) {
        return tile instanceof TileSpecialFlower && ((TileSpecialFlower) tile).getSubTile() instanceof SubTileExoflame;
    }
}
