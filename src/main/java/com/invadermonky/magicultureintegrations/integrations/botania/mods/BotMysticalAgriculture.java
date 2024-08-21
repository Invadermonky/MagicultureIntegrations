package com.invadermonky.magicultureintegrations.integrations.botania.mods;

import com.blakebr0.mysticalagriculture.tileentity.furnace.TileEssenceFurnace;
import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.mods.mysticalagriculture.MysticalAgricultureHeatable;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.integrations.botania.events.BotExoflameHandler;

public class BotMysticalAgriculture implements IModIntegration {
    @Override
    public void preInit() {
        if(ConfigHandlerMI.botania.exoflame.mystical_agriculture) {
            BotExoflameHandler.registerExoflameHeatable(TileEssenceFurnace.class, MysticalAgricultureHeatable.class);
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void postInit() {

    }
}
