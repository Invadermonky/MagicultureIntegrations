package com.invadermonky.magicultureintegrations.integrations.immersiveengineering.mods;

import blusunrize.immersiveengineering.api.tool.ExternalHeaterHandler;
import com.invadermonky.magicultureintegrations.api.IProxy;
import com.invadermonky.magicultureintegrations.api.mods.immersiveengineering.ImmersiveEngineeringUtils;
import com.invadermonky.magicultureintegrations.api.tile.IHeatableTile;
import com.invadermonky.magicultureintegrations.config.MIConfigIntegrations;
import org.zeith.thaumicadditions.tiles.TileAbstractSmelter;

public class IEThaumicAdditions implements IProxy {
    @Override
    public void preInit() {
        if (MIConfigIntegrations.immersive_engineering.externalHeaterHeatables.ta_essentia_smelter) {
            ExternalHeaterHandler.registerHeatableAdapter(TileAbstractSmelter.class, new ThaumicAdditionsFurnaceAdapter());
        }
    }

    public static class ThaumicAdditionsFurnaceAdapter extends ExternalHeaterHandler.HeatableAdapter<TileAbstractSmelter> {
        @Override
        public int doHeatTick(TileAbstractSmelter furnace, int energy, boolean redstone) {
            return furnace instanceof IHeatableTile ? ImmersiveEngineeringUtils.doHeatableHeatTick((IHeatableTile) furnace, energy, redstone) : 0;
        }
    }
}
