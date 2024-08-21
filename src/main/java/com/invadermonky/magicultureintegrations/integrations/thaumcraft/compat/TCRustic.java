package com.invadermonky.magicultureintegrations.integrations.thaumcraft.compat;

import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.mods.rustic.RusticHeatable;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.integrations.thaumcraft.events.TCBellowsHandler;
import rustic.common.tileentity.TileEntityCondenserBase;

public class TCRustic implements IModIntegration {
    @Override
    public void preInit() {
        if(ConfigHandlerMI.thaumcraft.arcane_bellows.rustic)
            TCBellowsHandler.registerBellowsHeatable(TileEntityCondenserBase.class, RusticHeatable.class);
    }

    @Override
    public void init() {

    }

    @Override
    public void postInit() {

    }
}
