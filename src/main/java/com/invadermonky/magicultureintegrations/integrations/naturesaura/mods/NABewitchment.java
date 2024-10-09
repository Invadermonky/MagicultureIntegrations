package com.invadermonky.magicultureintegrations.integrations.naturesaura.mods;

import com.bewitchment.common.block.tile.entity.TileEntityWitchesOven;
import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.tile.HeatableUtils;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import de.ellpeck.naturesaura.blocks.tiles.TileEntityFurnaceHeater;

public class NABewitchment implements IModIntegration {
    @Override
    public void preInit() {
        if(!ConfigHandlerMI.heatables.bewitchment.extraneous_firestarter) {
            HeatableUtils.blacklistHeatable(TileEntityFurnaceHeater.class, TileEntityWitchesOven.class);
        }
    }
}
