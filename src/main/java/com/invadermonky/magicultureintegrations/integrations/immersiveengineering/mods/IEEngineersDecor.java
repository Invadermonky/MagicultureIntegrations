package com.invadermonky.magicultureintegrations.integrations.immersiveengineering.mods;

import blusunrize.immersiveengineering.api.tool.ExternalHeaterHandler;
import com.invadermonky.magicultureintegrations.api.IProxy;
import com.invadermonky.magicultureintegrations.api.mods.immersiveengineering.ImmersiveEngineeringUtils;
import com.invadermonky.magicultureintegrations.api.tile.IHeatableTile;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import wile.engineersdecor.blocks.BlockDecorFurnace;

public class IEEngineersDecor implements IProxy {
    @Override
    public void preInit() {
        if(ConfigHandlerMI.heatables.engineers_decor.external_heater) {
            ExternalHeaterHandler.registerHeatableAdapter(BlockDecorFurnace.BTileEntity.class, new EngineersDecorFurnaceAdapter());
        }
    }

    public static class EngineersDecorFurnaceAdapter extends ExternalHeaterHandler.HeatableAdapter<BlockDecorFurnace.BTileEntity> {
        @Override
        public int doHeatTick(BlockDecorFurnace.BTileEntity furnace, int energy, boolean redstone) {
            return furnace instanceof IHeatableTile ? ImmersiveEngineeringUtils.doHeatableHeatTick((IHeatableTile) furnace, energy, redstone) : 0;
        }
    }
}
