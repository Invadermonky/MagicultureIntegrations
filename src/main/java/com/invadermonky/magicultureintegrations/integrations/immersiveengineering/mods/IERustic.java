package com.invadermonky.magicultureintegrations.integrations.immersiveengineering.mods;

import blusunrize.immersiveengineering.api.tool.ExternalHeaterHandler;
import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.mods.immersiveengineering.ImmersiveEngineeringUtils;
import com.invadermonky.magicultureintegrations.api.tile.IHeatableTile;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import rustic.common.tileentity.TileEntityCondenserBase;

public class IERustic implements IModIntegration {
    @Override
    public void preInit() {
        if(ConfigHandlerMI.heatables.rustic.external_heater) {
            ExternalHeaterHandler.registerHeatableAdapter(TileEntityCondenserBase.class, new RusticFurnaceAdapter());
        }
    }

    public static class RusticFurnaceAdapter extends ExternalHeaterHandler.HeatableAdapter<TileEntityCondenserBase> {
        @Override
        public int doHeatTick(TileEntityCondenserBase condenser, int energy, boolean redstone) {
            return condenser instanceof IHeatableTile ? ImmersiveEngineeringUtils.doHeatableHeatTick((IHeatableTile) condenser, energy, redstone) : 0;
        }
    }
}
