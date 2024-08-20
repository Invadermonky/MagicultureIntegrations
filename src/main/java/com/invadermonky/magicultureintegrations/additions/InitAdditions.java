package com.invadermonky.magicultureintegrations.additions;

import com.invadermonky.magicultureintegrations.additions.mods.bloodmagic.BloodMagicAdditions;
import com.invadermonky.magicultureintegrations.additions.mods.botania.BotaniaAdditions;
import com.invadermonky.magicultureintegrations.additions.mods.embers.EmbersAdditions;
import com.invadermonky.magicultureintegrations.additions.mods.naturesaura.NaturesAuraAdditions;
import com.invadermonky.magicultureintegrations.additions.mods.thaumcraft.ThaumcraftAdditions;
import com.invadermonky.magicultureintegrations.api.mods.IModAddition;
import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.init.RegistrarMI;
import com.invadermonky.magicultureintegrations.util.LogHelper;
import com.invadermonky.magicultureintegrations.util.ModIds;

import java.util.ArrayList;

public class InitAdditions {
    public static ArrayList<IModIntegration> additionModules = new ArrayList<>();

    private static void buildModules() {
        loadModule(ModIds.bloodmagic, BloodMagicAdditions.class);
        loadModule(ModIds.botania, BotaniaAdditions.class);
        loadModule(ModIds.embers, EmbersAdditions.class);
        loadModule(ModIds.natures_aura, NaturesAuraAdditions.class);
        loadModule(ModIds.thaumcraft, ThaumcraftAdditions.class);
    }

    public static void preInit() {
        buildModules();
        additionModules.forEach(IModIntegration::buildModules);
        additionModules.forEach(IModIntegration::preInit);
    }

    public static void init() {
        additionModules.forEach(IModIntegration::init);
    }

    public static void postInit() {
        RegistrarMI.ITEMS.forEach(item -> {
            if(item instanceof IModAddition) {
                ((IModAddition) item).registerRecipe();
            }
        });

        additionModules.forEach(IModIntegration::postInit);
    }

    private static void loadModule(ModIds mod, Class<? extends IModIntegration> moduleClass) {
        try {
            if(mod.isLoaded) {
                additionModules.add(moduleClass.newInstance());
                LogHelper.info("Loaded integration module: " + mod.modId);
            }
        } catch (Exception e) {
            LogHelper.error("Failed to load integration module: " + mod.modId);
        }
    }
}
