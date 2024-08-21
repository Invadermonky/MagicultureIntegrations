package com.invadermonky.magicultureintegrations.integrations.naturesaura.compat;

import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.mods.rustic.RusticHeatable;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.integrations.naturesaura.events.NAExternalHeaterHandler;
import rustic.common.tileentity.TileEntityCondenserBase;

public class NARustic implements IModIntegration {
    @Override
    public void preInit() {
        if(ConfigHandlerMI.natures_aura.extraneous_firestarter.rustic) {
            NAExternalHeaterHandler.registerExternalHeaterHeatable(TileEntityCondenserBase.class, RusticHeatable.class);
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void postInit() {

    }
}
