package com.invadermonky.magicultureintegrations.integrations.thaumcraft.compat;

import com.blakebr0.mysticalagriculture.tileentity.furnace.TileEssenceFurnace;
import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.mods.mysticalagriculture.MysticalAgricultureHeatable;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.integrations.thaumcraft.events.TCBellowsHandler;

public class TCMysticalAgriculture implements IModIntegration {
    @Override
    public void preInit() {
        if(ConfigHandlerMI.thaumcraft.arcane_bellows.mystical_agriculture)
            TCBellowsHandler.registerBellowsHeatable(TileEssenceFurnace.class, MysticalAgricultureHeatable.class);
    }

    @Override
    public void init() {

    }

    @Override
    public void postInit() {

    }
}
