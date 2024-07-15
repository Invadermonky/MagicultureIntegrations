package com.invadermonky.magicultureintegrations.integrations.botania.mods;

import com.invadermonky.magicultureintegrations.api.mods.botania.IBotIntegration;
import com.invadermonky.magicultureintegrations.api.mods.futuremc.FutureMCHeatable;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.integrations.botania.events.BotExoflameHandler;
import thedarkcolour.futuremc.tile.TileFurnaceAdvanced;

public class BotFutureMC implements IBotIntegration {
    @Override
    public void registerExoflameHandler() {
        if(ConfigHandlerMI.botania.exoflame.future_mc) {
            BotExoflameHandler.registerExoflameHeatable(TileFurnaceAdvanced.class, FutureMCHeatable.class);
        }
    }
}
