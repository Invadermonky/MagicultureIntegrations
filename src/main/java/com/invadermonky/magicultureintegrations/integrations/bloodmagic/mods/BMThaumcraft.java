package com.invadermonky.magicultureintegrations.integrations.bloodmagic.mods;

import WayofTime.bloodmagic.alchemyArray.AlchemyArrayEffectFurnaceFuel;
import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.tile.HeatableUtils;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import thaumcraft.common.tiles.essentia.TileSmelter;

public class BMThaumcraft implements IModIntegration {
    @Override
    public void preInit() {
        if(!ConfigHandlerMI.heatables.thaumcraft.furnace_heater_array) {
            HeatableUtils.blacklistHeatable(AlchemyArrayEffectFurnaceFuel.class, TileSmelter.class);
        }
    }
}
