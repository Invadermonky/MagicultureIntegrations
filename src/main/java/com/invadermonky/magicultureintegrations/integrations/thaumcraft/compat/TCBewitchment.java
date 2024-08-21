package com.invadermonky.magicultureintegrations.integrations.thaumcraft.compat;

import com.bewitchment.common.block.tile.entity.TileEntityWitchesOven;
import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.mods.bewitchment.BewitchmentHeatable;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.integrations.thaumcraft.events.TCBellowsHandler;

public class TCBewitchment implements IModIntegration {
    @Override
    public void preInit() {
        if(ConfigHandlerMI.thaumcraft.arcane_bellows.bewitchment)
            TCBellowsHandler.registerBellowsHeatable(TileEntityWitchesOven.class, BewitchmentHeatable.class);
    }

    @Override
    public void init() {

    }

    @Override
    public void postInit() {

    }
}
