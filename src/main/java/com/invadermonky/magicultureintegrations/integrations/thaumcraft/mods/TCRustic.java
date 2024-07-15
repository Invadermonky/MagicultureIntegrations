package com.invadermonky.magicultureintegrations.integrations.thaumcraft.mods;

import com.invadermonky.magicultureintegrations.api.mods.rustic.RusticHeatable;
import com.invadermonky.magicultureintegrations.api.mods.thaumcraft.ITCIntegration;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.integrations.thaumcraft.events.TCBellowsHandler;
import rustic.common.tileentity.TileEntityCondenserBase;

public class TCRustic implements ITCIntegration {
    @Override
    public void registerBellowsHandler() {
        if(ConfigHandlerMI.thaumcraft.arcane_bellows.rustic)
            TCBellowsHandler.registerBellowsHeatable(TileEntityCondenserBase.class, RusticHeatable.class);
    }
}
