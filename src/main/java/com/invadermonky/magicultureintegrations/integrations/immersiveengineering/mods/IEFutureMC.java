package com.invadermonky.magicultureintegrations.integrations.immersiveengineering.mods;

import blusunrize.immersiveengineering.api.tool.ExternalHeaterHandler;
import blusunrize.immersiveengineering.api.tool.ExternalHeaterHandler.HeatableAdapter;
import com.invadermonky.magicultureintegrations.api.IProxy;
import com.invadermonky.magicultureintegrations.api.mods.immersiveengineering.ImmersiveEngineeringUtils;
import com.invadermonky.magicultureintegrations.api.tile.IHeatableTile;
import com.invadermonky.magicultureintegrations.config.MIConfigIntegrations;
import thedarkcolour.futuremc.tile.TileFurnaceAdvanced;

public class IEFutureMC implements IProxy {
    @Override
    public void preInit() {
        if (MIConfigIntegrations.immersive_engineering.externalHeaterHeatables.fmc_advanced_furnaces) {
            ExternalHeaterHandler.registerHeatableAdapter(TileFurnaceAdvanced.class, new FutureMCFurnaceAdapter());
        }
    }

    public static class FutureMCFurnaceAdapter extends HeatableAdapter<TileFurnaceAdvanced> {
        @Override
        public int doHeatTick(TileFurnaceAdvanced furnace, int energy, boolean redstone) {
            return furnace instanceof IHeatableTile ? ImmersiveEngineeringUtils.doHeatableHeatTick((IHeatableTile) furnace, energy, redstone) : 0;
        }
    }
}
