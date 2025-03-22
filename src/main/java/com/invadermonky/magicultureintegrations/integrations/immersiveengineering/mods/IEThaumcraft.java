package com.invadermonky.magicultureintegrations.integrations.immersiveengineering.mods;

import blusunrize.immersiveengineering.api.tool.ExternalHeaterHandler;
import com.invadermonky.magicultureintegrations.api.IProxy;
import com.invadermonky.magicultureintegrations.api.mods.immersiveengineering.ImmersiveEngineeringUtils;
import com.invadermonky.magicultureintegrations.api.tile.IHeatableTile;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import thaumcraft.common.tiles.essentia.TileSmelter;

public class IEThaumcraft implements IProxy {
    @Override
    public void preInit() {
        if(ConfigHandlerMI.heatables.thaumcraft.external_heater) {
            ExternalHeaterHandler.registerHeatableAdapter(TileSmelter.class, new ThaumcraftFurnaceAdapter());
        }
    }

    public static class ThaumcraftFurnaceAdapter extends ExternalHeaterHandler.HeatableAdapter<TileSmelter> {
        @Override
        public int doHeatTick(TileSmelter smelter, int energy, boolean redstone) {
            return smelter instanceof IHeatableTile ? ImmersiveEngineeringUtils.doHeatableHeatTick((IHeatableTile) smelter, energy, redstone) : 0;
        }
    }
}
