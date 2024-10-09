package com.invadermonky.magicultureintegrations.integrations.naturesaura.mods;

import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.tile.HeatableUtils;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import de.ellpeck.naturesaura.blocks.tiles.TileEntityFurnaceHeater;
import thedarkcolour.futuremc.tile.TileFurnaceAdvanced;

public class NAFutureMC implements IModIntegration {
    @Override
    public void preInit() {
        if(!ConfigHandlerMI.heatables.future_mc.extraneous_firestarter) {
            HeatableUtils.blacklistHeatable(TileEntityFurnaceHeater.class, TileFurnaceAdvanced.class);
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void postInit() {

    }
}
