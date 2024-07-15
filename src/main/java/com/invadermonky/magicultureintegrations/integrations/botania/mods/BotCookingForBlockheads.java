package com.invadermonky.magicultureintegrations.integrations.botania.mods;

import com.invadermonky.magicultureintegrations.api.mods.botania.IBotIntegration;
import com.invadermonky.magicultureintegrations.api.mods.cookingforblockheads.CookingForBlockheadsHeatable;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.integrations.botania.events.BotExoflameHandler;
import net.blay09.mods.cookingforblockheads.tile.TileOven;

public class BotCookingForBlockheads implements IBotIntegration {
    @Override
    public void registerExoflameHandler() {
        if(ConfigHandlerMI.botania.exoflame.cooking_for_blockheads) {
            BotExoflameHandler.registerExoflameHeatable(TileOven.class, CookingForBlockheadsHeatable.class);
        }
    }
}
