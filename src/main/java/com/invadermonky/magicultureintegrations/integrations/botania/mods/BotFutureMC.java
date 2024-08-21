package com.invadermonky.magicultureintegrations.integrations.botania.mods;

import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.mods.futuremc.FutureMCHeatable;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.integrations.botania.events.BotExoflameHandler;
import thedarkcolour.futuremc.tile.TileFurnaceAdvanced;

public class BotFutureMC implements IModIntegration {
    @Override
    public void preInit() {
        if(ConfigHandlerMI.botania.exoflame.future_mc) {
            BotExoflameHandler.registerExoflameHeatable(TileFurnaceAdvanced.class, FutureMCHeatable.class);
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void postInit() {

    }
}
