package com.invadermonky.magicultureintegrations.integrations.embers;

import com.invadermonky.magicultureintegrations.api.mods.IIntegrationModule;
import com.invadermonky.magicultureintegrations.init.RegistrarMI;
import com.invadermonky.magicultureintegrations.integrations.embers.item.ItemMantleCloak;
import com.invadermonky.magicultureintegrations.util.IntegrationList;
import com.invadermonky.magicultureintegrations.util.ModIds;
import org.jetbrains.annotations.Nullable;

public class InitEmbers implements IIntegrationModule {
    @Override
    public void buildModIntegrations() {

    }

    @Nullable
    @Override
    public IntegrationList getModIntegrations() {
        return null;
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
