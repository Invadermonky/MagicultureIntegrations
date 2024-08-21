package com.invadermonky.magicultureintegrations.integrations.naturesaura.compat;

import com.bewitchment.common.block.tile.entity.TileEntityWitchesOven;
import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.mods.bewitchment.BewitchmentHeatable;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.integrations.naturesaura.events.NAExternalHeaterHandler;

public class NABewitchment implements IModIntegration {
    @Override
    public void preInit() {
        if(ConfigHandlerMI.natures_aura.extraneous_firestarter.bewitchment) {
            NAExternalHeaterHandler.registerExternalHeaterHeatable(TileEntityWitchesOven.class, BewitchmentHeatable.class);
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void postInit() {

    }
}
