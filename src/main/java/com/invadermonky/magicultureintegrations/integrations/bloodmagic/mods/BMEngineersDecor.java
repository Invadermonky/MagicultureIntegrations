package com.invadermonky.magicultureintegrations.integrations.bloodmagic.mods;

import WayofTime.bloodmagic.alchemyArray.AlchemyArrayEffectFurnaceFuel;
import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.tile.HeatableUtils;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import wile.engineersdecor.blocks.BlockDecorFurnace;

public class BMEngineersDecor implements IModIntegration {
    @Override
    public void preInit() {
        if(!ConfigHandlerMI.heatables.engineers_decor.furnace_heater_array) {
            HeatableUtils.blacklistHeatable(AlchemyArrayEffectFurnaceFuel.class, BlockDecorFurnace.BTileEntity.class);
        }
    }
}
