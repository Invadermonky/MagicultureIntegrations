package com.invadermonky.magicultureintegrations.integrations.naturesaura.mods;

import com.bewitchment.common.block.tile.entity.TileEntityWitchesOven;
import com.invadermonky.magicultureintegrations.api.mods.bewitchment.BewitchmentHeatable;
import com.invadermonky.magicultureintegrations.api.mods.naturesaura.INAIntegration;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.integrations.naturesaura.events.NAExternalHeaterHandler;

public class NABewitchment implements INAIntegration {
    @Override
    public void registerExtraneousHeaterHandler() {
        if(ConfigHandlerMI.natures_aura.extraneous_firestarter.bewitchment) {
            NAExternalHeaterHandler.registerExternalHeaterHeatable(TileEntityWitchesOven.class, BewitchmentHeatable.class);
        }
    }
}
