package com.invadermonky.magicultureintegrations.integrations.thaumcraft.mods;

import com.invadermonky.magicultureintegrations.api.mods.futuremc.FutureMCHeatable;
import com.invadermonky.magicultureintegrations.api.mods.thaumcraft.ITCIntegration;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.integrations.thaumcraft.events.TCBellowsHandler;
import thedarkcolour.futuremc.tile.TileFurnaceAdvanced;

public class TCFutureMC implements ITCIntegration {
    @Override
    public void registerBellowsHandler() {
        if(ConfigHandlerMI.thaumcraft.arcane_bellows.futuremc)
            TCBellowsHandler.registerBellowsHeatable(TileFurnaceAdvanced.class, FutureMCHeatable.class);
    }
}
