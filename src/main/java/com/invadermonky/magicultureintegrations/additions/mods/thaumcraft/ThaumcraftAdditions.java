package com.invadermonky.magicultureintegrations.additions.mods.thaumcraft;

import com.invadermonky.magicultureintegrations.additions.mods.thaumcraft.item.ItemThaumicRegulator;
import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.init.RegistrarMI;
import com.invadermonky.magicultureintegrations.util.ModIds;

public class ThaumcraftAdditions implements IModIntegration {
    @Override
    public void buildModules() {

    }

    @Override
    public void preInit() {
        if(ModIds.simpledifficulty.isLoaded || ModIds.tough_as_nails.isLoaded) {
            RegistrarMI.registerItem(ItemThaumicRegulator.THAUMIC_REGULATOR);
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void postInit() {

    }
}
