package com.invadermonky.magicultureintegrations.additions.mods.embers;

import com.invadermonky.magicultureintegrations.additions.mods.embers.item.ItemMantleCloak;
import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.init.RegistrarMI;
import com.invadermonky.magicultureintegrations.util.ModIds;

public class EmbersAdditions implements IModIntegration {
    @Override
    public void buildModules() {

    }

    @Override
    public void preInit() {
        if((ModIds.simpledifficulty.isLoaded || ModIds.tough_as_nails.isLoaded) && ModIds.baubles.isLoaded) {
            RegistrarMI.registerItem(ItemMantleCloak.MANTLE_CLOAK);
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void postInit() {

    }
}
