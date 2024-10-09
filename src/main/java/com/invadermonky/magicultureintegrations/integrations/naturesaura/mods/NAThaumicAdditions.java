package com.invadermonky.magicultureintegrations.integrations.naturesaura.mods;

import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.tile.HeatableUtils;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import de.ellpeck.naturesaura.blocks.tiles.TileEntityFurnaceHeater;
import org.zeith.thaumicadditions.tiles.TileAbstractSmelter;

public class NAThaumicAdditions implements IModIntegration {
    @Override
    public void preInit() {
        if(!ConfigHandlerMI.heatables.thaumic_additions.extraneous_firestarter) {
            HeatableUtils.blacklistHeatable(TileEntityFurnaceHeater.class, TileAbstractSmelter.class);
        }
    }
}
