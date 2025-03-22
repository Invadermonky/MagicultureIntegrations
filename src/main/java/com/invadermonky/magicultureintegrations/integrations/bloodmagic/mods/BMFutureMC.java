package com.invadermonky.magicultureintegrations.integrations.bloodmagic.mods;

import WayofTime.bloodmagic.alchemyArray.AlchemyArrayEffectFurnaceFuel;
import com.invadermonky.magicultureintegrations.api.IProxy;
import com.invadermonky.magicultureintegrations.api.tile.HeatableUtils;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import thedarkcolour.futuremc.tile.TileFurnaceAdvanced;

public class BMFutureMC implements IProxy {
    @Override
    public void preInit() {
        if(!ConfigHandlerMI.heatables.future_mc.furnace_heater_array) {
            HeatableUtils.blacklistHeatable(AlchemyArrayEffectFurnaceFuel.class, TileFurnaceAdvanced.class);
        }
    }
}
