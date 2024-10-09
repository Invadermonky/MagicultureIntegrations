package com.invadermonky.magicultureintegrations.integrations.naturesaura.mods;

import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.tile.HeatableUtils;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import de.ellpeck.naturesaura.blocks.tiles.TileEntityFurnaceHeater;
import thaumcraft.common.tiles.essentia.TileSmelter;

public class NAThaumcraft implements IModIntegration {
    @Override
    public void preInit() {
        if(!ConfigHandlerMI.heatables.thaumcraft.extraneous_firestarter) {
            HeatableUtils.blacklistHeatable(TileEntityFurnaceHeater.class, TileSmelter.class);
        }
    }
}
