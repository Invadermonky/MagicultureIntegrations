package com.invadermonky.magicultureintegrations.additions.mods.botania;

import com.invadermonky.magicultureintegrations.additions.mods.botania.item.ItemTemperatureRing;
import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.init.RegistrarMI;
import com.invadermonky.magicultureintegrations.util.ModIds;

public class BotaniaAdditions implements IModIntegration {
    @Override
    public void buildModules() {

    }

    @Override
    public void preInit() {
        if(ModIds.simpledifficulty.isLoaded || ModIds.tough_as_nails.isLoaded) {
            RegistrarMI.registerItem(ItemTemperatureRing.TEMPERATURE_RING);
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void postInit() {

    }
}
