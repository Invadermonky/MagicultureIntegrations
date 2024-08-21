package com.invadermonky.magicultureintegrations.integrations.naturesaura.compat;

import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.mods.futuremc.FutureMCHeatable;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.integrations.naturesaura.events.NAExternalHeaterHandler;
import thedarkcolour.futuremc.tile.TileFurnaceAdvanced;

public class NAFutureMC implements IModIntegration {
    @Override
    public void preInit() {
        if(ConfigHandlerMI.natures_aura.extraneous_firestarter.future_mc) {
            NAExternalHeaterHandler.registerExternalHeaterHeatable(TileFurnaceAdvanced.class, FutureMCHeatable.class);
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void postInit() {

    }
}
