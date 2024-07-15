package com.invadermonky.magicultureintegrations.integrations.naturesaura.events;

import com.invadermonky.magicultureintegrations.api.events.ITileTickEvent;
import com.invadermonky.magicultureintegrations.api.mods.naturesaura.NaturesAuraUtils;
import com.invadermonky.magicultureintegrations.api.tile.IHeatableTile;
import com.invadermonky.magicultureintegrations.util.ReflectionHelper;
import de.ellpeck.naturesaura.api.aura.chunk.IAuraChunk;
import de.ellpeck.naturesaura.blocks.BlockFurnaceHeater;
import de.ellpeck.naturesaura.blocks.tiles.TileEntityFurnaceHeater;
import gnu.trove.map.hash.THashMap;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class NAExternalHeaterHandler implements ITileTickEvent {
    public static THashMap<Class<? extends TileEntity>, Class<? extends IHeatableTile>> naHeaterHeatableMap = new THashMap<>();

    public static void registerExternalHeaterHeatable(Class<? extends TileEntity> tileClass, Class<? extends IHeatableTile> heatableClass) {
        naHeaterHeatableMap.put(tileClass, heatableClass);
    }

    private static boolean isHeatableTile(TileEntity tile) {
        return tile != null && (naHeaterHeatableMap.containsKey(tile.getClass()) || naHeaterHeatableMap.containsKey(tile.getClass().getSuperclass()));
    }

    @Override
    public void onTileEntityTick(World world, TileEntity tile) {
        if(world.isRemote || world.getTotalWorldTime() % 5L != 0)
            return;

        boolean did = false;
        TileEntityFurnaceHeater heater = (TileEntityFurnaceHeater) tile;
        BlockPos heaterPos = heater.getPos();
        EnumFacing heaterFacing = world.getBlockState(heaterPos).getValue(BlockFurnaceHeater.FACING);
        BlockPos facingPos = heaterPos.offset(heaterFacing.getOpposite());
        TileEntity facingTile = world.getTileEntity(facingPos);

        if(!isHeatableTile(facingTile))
            return;

        try {
            IHeatableTile heatable = ReflectionHelper.getIHeatableInstance(naHeaterHeatableMap, facingTile);
            if(heatable.canSmelt()) {
                int time = heatable.getBurnTime();
                heatable.boostBurnTime(Math.max(0, 200 - time));
                heatable.boostCookTime(5);
                heatable.updateTile();
                BlockPos spot = IAuraChunk.getHighestSpot(world, heaterPos, 20, heaterPos);
                IAuraChunk chunk = IAuraChunk.getAuraChunk(world, spot);
                chunk.drainAura(spot, MathHelper.ceil((float)(200 - time) * 16.6f));
                did = true;
                NaturesAuraUtils.spawnHeaterParticles(world, heaterPos, facingPos);
            }
            if(did != heater.isActive) {
                heater.isActive = did;
                heater.sendToClients();
            }
        } catch (Exception ignored) {}
    }

    @Override
    public boolean checkTile(TileEntity tile) {
        return tile instanceof TileEntityFurnaceHeater;
    }
}
