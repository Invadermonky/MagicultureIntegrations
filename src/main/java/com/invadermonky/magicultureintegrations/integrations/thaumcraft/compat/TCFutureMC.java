package com.invadermonky.magicultureintegrations.integrations.thaumcraft.compat;

import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.mods.futuremc.FutureMCHeatable;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.integrations.thaumcraft.events.TCBellowsHandler;
import thedarkcolour.futuremc.tile.TileFurnaceAdvanced;

public class TCFutureMC implements IModIntegration {
    @Override
    public void preInit() {
        if(ConfigHandlerMI.thaumcraft.arcane_bellows.futuremc)
            TCBellowsHandler.registerBellowsHeatable(TileFurnaceAdvanced.class, FutureMCHeatable.class);
    }

    @Override
    public void init() {

    }

    @Override
    public void postInit() {

    }
}
