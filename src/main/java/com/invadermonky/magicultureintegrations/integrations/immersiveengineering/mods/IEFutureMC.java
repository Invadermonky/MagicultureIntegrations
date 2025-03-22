package com.invadermonky.magicultureintegrations.integrations.immersiveengineering.mods;

import blusunrize.immersiveengineering.api.tool.ExternalHeaterHandler;
import blusunrize.immersiveengineering.api.tool.ExternalHeaterHandler.HeatableAdapter;
import com.invadermonky.magicultureintegrations.api.IProxy;
import com.invadermonky.magicultureintegrations.api.mods.immersiveengineering.ImmersiveEngineeringUtils;
import com.invadermonky.magicultureintegrations.api.tile.IHeatableTile;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import thedarkcolour.futuremc.tile.TileFurnaceAdvanced;

public class IEFutureMC implements IProxy {
    @Override
    public void preInit() {
        if(ConfigHandlerMI.heatables.future_mc.external_heater) {
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
