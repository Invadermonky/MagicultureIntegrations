package com.invadermonky.magicultureintegrations.integrations.botania.mods;

import com.invadermonky.magicultureintegrations.api.mods.botania.IBotIntegration;
import com.invadermonky.magicultureintegrations.api.mods.rustic.RusticHeatable;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.integrations.botania.events.BotExoflameHandler;
import rustic.common.tileentity.TileEntityCondenserBase;

public class BotRustic implements IBotIntegration {
    @Override
    public void registerExoflameHandler() {
        if(ConfigHandlerMI.botania.exoflame.rustic) {
            BotExoflameHandler.registerExoflameHeatable(TileEntityCondenserBase.class, RusticHeatable.class);
        }
    }
}
