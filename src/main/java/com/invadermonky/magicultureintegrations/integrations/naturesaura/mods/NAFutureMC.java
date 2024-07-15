package com.invadermonky.magicultureintegrations.integrations.naturesaura.mods;

import com.invadermonky.magicultureintegrations.api.mods.futuremc.FutureMCHeatable;
import com.invadermonky.magicultureintegrations.api.mods.naturesaura.INAIntegration;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.integrations.naturesaura.events.NAExternalHeaterHandler;
import thedarkcolour.futuremc.tile.TileFurnaceAdvanced;

public class NAFutureMC implements INAIntegration {
    @Override
    public void registerExtraneousHeaterHandler() {
        if(ConfigHandlerMI.natures_aura.extraneous_firestarter.future_mc) {
            NAExternalHeaterHandler.registerExternalHeaterHeatable(TileFurnaceAdvanced.class, FutureMCHeatable.class);
        }
    }
}
