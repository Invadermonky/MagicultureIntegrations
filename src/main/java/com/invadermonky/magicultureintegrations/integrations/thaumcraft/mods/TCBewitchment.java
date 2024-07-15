package com.invadermonky.magicultureintegrations.integrations.thaumcraft.mods;

import com.bewitchment.common.block.tile.entity.TileEntityWitchesOven;
import com.invadermonky.magicultureintegrations.api.mods.bewitchment.BewitchmentHeatable;
import com.invadermonky.magicultureintegrations.api.mods.thaumcraft.ITCIntegration;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.integrations.thaumcraft.events.TCBellowsHandler;

public class TCBewitchment implements ITCIntegration {
    @Override
    public void registerBellowsHandler() {
        if(ConfigHandlerMI.thaumcraft.arcane_bellows.bewitchment)
            TCBellowsHandler.registerBellowsHeatable(TileEntityWitchesOven.class, BewitchmentHeatable.class);
    }
}
