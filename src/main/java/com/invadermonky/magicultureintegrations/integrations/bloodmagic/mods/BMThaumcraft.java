package com.invadermonky.magicultureintegrations.integrations.bloodmagic.mods;

import WayofTime.bloodmagic.alchemyArray.AlchemyArrayEffectFurnaceFuel;
import com.invadermonky.magicultureintegrations.api.IProxy;
import com.invadermonky.magicultureintegrations.api.tile.HeatableUtils;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import thaumcraft.common.tiles.essentia.TileSmelter;

public class BMThaumcraft implements IProxy {
    @Override
    public void preInit() {
        if(!ConfigHandlerMI.heatables.thaumcraft.furnace_heater_array) {
            HeatableUtils.blacklistHeatable(AlchemyArrayEffectFurnaceFuel.class, TileSmelter.class);
        }
    }
}
