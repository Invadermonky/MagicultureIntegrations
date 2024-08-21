package com.invadermonky.magicultureintegrations.integrations.naturesaura.compat;

import com.blakebr0.mysticalagriculture.tileentity.furnace.TileEssenceFurnace;
import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.mods.mysticalagriculture.MysticalAgricultureHeatable;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.integrations.naturesaura.events.NAExternalHeaterHandler;

public class NAMysticalAgriculture implements IModIntegration {
    @Override
    public void preInit() {
        if(ConfigHandlerMI.natures_aura.extraneous_firestarter.mystical_agriculture) {
            NAExternalHeaterHandler.registerExternalHeaterHeatable(TileEssenceFurnace.class, MysticalAgricultureHeatable.class);
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void postInit() {

    }
}
