package com.invadermonky.magicultureintegrations.integrations.botania.mods;

import com.blakebr0.mysticalagriculture.tileentity.furnace.TileEssenceFurnace;
import com.invadermonky.magicultureintegrations.api.mods.botania.IBotIntegration;
import com.invadermonky.magicultureintegrations.api.mods.mysticalagriculture.MysticalAgricultureHeatable;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.integrations.botania.events.BotExoflameHandler;

public class BotMysticalAgriculture implements IBotIntegration {
    @Override
    public void registerExoflameHandler() {
        if(ConfigHandlerMI.botania.exoflame.mystical_agriculture)
            BotExoflameHandler.registerExoflameHeatable(TileEssenceFurnace.class, MysticalAgricultureHeatable.class);
    }
}
