package com.invadermonky.magicultureintegrations.integrations.immersiveengineering.mods;

import blusunrize.immersiveengineering.api.tool.ExternalHeaterHandler;
import blusunrize.immersiveengineering.api.tool.ExternalHeaterHandler.HeatableAdapter;
import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.mods.futuremc.FutureMCHeatable;
import com.invadermonky.magicultureintegrations.api.mods.immersiveengineering.ImmersiveEngineeringUtils;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import thedarkcolour.futuremc.tile.TileFurnaceAdvanced;

public class IEFutureMC implements IModIntegration {
    @Override
    public void preInit() {
        if(ConfigHandlerMI.immersive_engineering.externalHeater.future_mc) {
            ExternalHeaterHandler.registerHeatableAdapter(TileFurnaceAdvanced.class, new FutureMCFurnaceAdapter());
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void postInit() {

    }

    public static class FutureMCFurnaceAdapter extends HeatableAdapter<TileFurnaceAdvanced> {
        @Override
        public int doHeatTick(TileFurnaceAdvanced furnace, int energy, boolean redstone) {
            return ImmersiveEngineeringUtils.doHeatableHeatTick(new FutureMCHeatable(furnace), energy, redstone);
        }
    }
}
