package com.invadermonky.magicultureintegrations.integrations.naturesaura.mods;

import com.invadermonky.magicultureintegrations.api.mods.naturesaura.INAIntegration;
import com.invadermonky.magicultureintegrations.api.mods.rustic.RusticHeatable;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.integrations.naturesaura.events.NAExternalHeaterHandler;
import rustic.common.tileentity.TileEntityCondenserBase;

public class NARustic implements INAIntegration {
    @Override
    public void registerExtraneousHeaterHandler() {
        if(ConfigHandlerMI.natures_aura.extraneous_firestarter.rustic)
            NAExternalHeaterHandler.registerExternalHeaterHeatable(TileEntityCondenserBase.class, RusticHeatable.class);
    }
}
