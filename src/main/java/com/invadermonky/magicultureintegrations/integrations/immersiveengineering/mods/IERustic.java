package com.invadermonky.magicultureintegrations.integrations.immersiveengineering.mods;

import blusunrize.immersiveengineering.api.tool.ExternalHeaterHandler;
import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.mods.immersiveengineering.ImmersiveEngineeringUtils;
import com.invadermonky.magicultureintegrations.api.mods.rustic.RusticHeatable;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import rustic.common.tileentity.TileEntityCondenserBase;

public class IERustic implements IModIntegration {
    @Override
    public void preInit() {
        if(ConfigHandlerMI.immersive_engineering.externalHeater.rustic) {
            ExternalHeaterHandler.registerHeatableAdapter(TileEntityCondenserBase.class, new RusticFurnaceAdapter());
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void postInit() {

    }

    public static class RusticFurnaceAdapter extends ExternalHeaterHandler.HeatableAdapter<TileEntityCondenserBase> {
        @Override
        public int doHeatTick(TileEntityCondenserBase condenser, int energy, boolean redstone) {
            return ImmersiveEngineeringUtils.doHeatableHeatTick(new RusticHeatable(condenser), energy, redstone);
        }
    }
}
