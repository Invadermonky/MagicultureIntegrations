package com.invadermonky.magicultureintegrations.integrations.immersiveengineering.mods;

import blusunrize.immersiveengineering.api.tool.ExternalHeaterHandler;
import blusunrize.immersiveengineering.api.tool.ExternalHeaterHandler.HeatableAdapter;
import com.bewitchment.common.block.tile.entity.TileEntityWitchesOven;
import com.invadermonky.magicultureintegrations.api.IProxy;
import com.invadermonky.magicultureintegrations.api.mods.immersiveengineering.ImmersiveEngineeringUtils;
import com.invadermonky.magicultureintegrations.api.tile.IHeatableTile;
import com.invadermonky.magicultureintegrations.config.MIConfigIntegrations;

public class IEBewitchment implements IProxy {
    @Override
    public void preInit() {
        if (MIConfigIntegrations.immersive_engineering.external_heater_integrations.witches_oven) {
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
