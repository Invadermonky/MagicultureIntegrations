package com.invadermonky.magicultureintegrations.integrations.immersiveengineering.mods;

import blusunrize.immersiveengineering.api.tool.ExternalHeaterHandler;
import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.mods.immersiveengineering.ImmersiveEngineeringUtils;
import com.invadermonky.magicultureintegrations.api.tile.IBoostableTile;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import slimeknights.tconstruct.smeltery.tileentity.TileHeatingStructure;

public class IETinkersConstruct implements IModIntegration {
    @Override
    public void preInit() {
        if(ConfigHandlerMI.heatables.tinkers_construct.external_heater) {
            ExternalHeaterHandler.registerHeatableAdapter(TileHeatingStructure.class, new TinkersConstructFurnaceAdapter());
        }
    }

    public static class TinkersConstructFurnaceAdapter extends ExternalHeaterHandler.HeatableAdapter<TileHeatingStructure<?>> {
        @Override
        public int doHeatTick(TileHeatingStructure furnace, int energy, boolean redstone) {
            return furnace instanceof IBoostableTile ? ImmersiveEngineeringUtils.doBoostableTick(((IBoostableTile) furnace).getTrueBoostable(), energy, redstone) : 0;
        }
    }
}
