package com.invadermonky.magicultureintegrations.integrations.immersiveengineering.mods;

import blusunrize.immersiveengineering.api.tool.ExternalHeaterHandler;
import blusunrize.immersiveengineering.api.tool.ExternalHeaterHandler.HeatableAdapter;
import com.bewitchment.common.block.tile.entity.TileEntityWitchesOven;
import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.mods.immersiveengineering.ImmersiveEngineeringUtils;
import com.invadermonky.magicultureintegrations.api.tile.IHeatableTile;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;

public class IEBewitchment implements IModIntegration {
    @Override
    public void preInit() {
        if(ConfigHandlerMI.heatables.bewitchment.external_heater) {
            ExternalHeaterHandler.registerHeatableAdapter(TileEntityWitchesOven.class, new BewitchmentFurnaceAdapter());
        }
    }

    public static class BewitchmentFurnaceAdapter extends HeatableAdapter<TileEntityWitchesOven> {
        @Override
        public int doHeatTick(TileEntityWitchesOven oven, int energy, boolean redstone) {
            return oven instanceof IHeatableTile ? ImmersiveEngineeringUtils.doHeatableHeatTick((IHeatableTile) oven, energy, redstone) : 0;
        }
    }
}
