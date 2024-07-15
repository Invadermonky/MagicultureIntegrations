package com.invadermonky.magicultureintegrations.integrations.naturesaura.mods;

import com.blakebr0.mysticalagriculture.tileentity.furnace.TileEssenceFurnace;
import com.invadermonky.magicultureintegrations.api.mods.mysticalagriculture.MysticalAgricultureHeatable;
import com.invadermonky.magicultureintegrations.api.mods.naturesaura.INAIntegration;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.integrations.naturesaura.events.NAExternalHeaterHandler;

public class NAMysticalAgriculture implements INAIntegration {
    @Override
    public void registerExtraneousHeaterHandler() {
        if(ConfigHandlerMI.natures_aura.extraneous_firestarter.mystical_agriculture)
            NAExternalHeaterHandler.registerExternalHeaterHeatable(TileEssenceFurnace.class, MysticalAgricultureHeatable.class);
    }
}
