package com.invadermonky.magicultureintegrations.integrations.bloodmagic.mods;

import WayofTime.bloodmagic.alchemyArray.AlchemyArrayEffectFurnaceFuel;
import com.invadermonky.magicultureintegrations.api.IProxy;
import com.invadermonky.magicultureintegrations.api.tile.HeatableUtils;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import wile.engineersdecor.blocks.BlockDecorFurnace;

public class BMEngineersDecor implements IProxy {
    @Override
    public void preInit() {
        if(!ConfigHandlerMI.heatables.engineers_decor.furnace_heater_array) {
            HeatableUtils.blacklistHeatable(AlchemyArrayEffectFurnaceFuel.class, BlockDecorFurnace.BTileEntity.class);
        }
    }
}
