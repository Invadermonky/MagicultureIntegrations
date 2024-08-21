package com.invadermonky.magicultureintegrations.integrations.botania.mods;

import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.mods.cookingforblockheads.CookingForBlockheadsHeatable;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.integrations.botania.events.BotExoflameHandler;
import net.blay09.mods.cookingforblockheads.tile.TileOven;

public class BotCookingForBlockheads implements IModIntegration {
    @Override
    public void preInit() {
        if(ConfigHandlerMI.botania.exoflame.cooking_for_blockheads) {
            BotExoflameHandler.registerExoflameHeatable(TileOven.class, CookingForBlockheadsHeatable.class);
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void postInit() {

    }
}
