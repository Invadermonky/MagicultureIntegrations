package com.invadermonky.magicultureintegrations.additions.mods.naturesaura;

import com.invadermonky.magicultureintegrations.additions.mods.naturesaura.item.ItemTemperatureAmulet;
import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.init.RegistrarMI;
import com.invadermonky.magicultureintegrations.util.ModIds;
import vazkii.patchouli.api.PatchouliAPI;

public class NaturesAuraAdditions implements IModIntegration {
    @Override
    public void buildModules() {

    }

    @Override
    public void preInit() {
        if((ModIds.simpledifficulty.isLoaded || ModIds.tough_as_nails.isLoaded) && ModIds.baubles.isLoaded) {
            RegistrarMI.registerItem(ItemTemperatureAmulet.TEMPERATURE_AMULET);
        }
        PatchouliAPI.instance.setConfigFlag("magicultureintegrations:temperature_amulet",
                (ModIds.simpledifficulty.isLoaded || ModIds.tough_as_nails.isLoaded) &&
                ModIds.baubles.isLoaded &&
                ItemTemperatureAmulet.TEMPERATURE_AMULET.isEnabled()
        );
    }

    @Override
    public void init() {

    }

    @Override
    public void postInit() {

    }
}
