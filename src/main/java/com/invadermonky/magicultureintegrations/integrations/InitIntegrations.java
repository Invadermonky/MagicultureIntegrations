package com.invadermonky.magicultureintegrations.integrations;

import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.integrations.bloodmagic.InitBloodMagic;
import com.invadermonky.magicultureintegrations.integrations.botania.InitBotania;
import com.invadermonky.magicultureintegrations.integrations.immersiveengineering.InitImmersiveEngineering;
import com.invadermonky.magicultureintegrations.integrations.naturesaura.InitNaturesAura;
import com.invadermonky.magicultureintegrations.integrations.qualitytools.InitQualityTools;
import com.invadermonky.magicultureintegrations.integrations.thaumcraft.InitThaumcraft;
import com.invadermonky.magicultureintegrations.util.LogHelper;
import com.invadermonky.magicultureintegrations.util.ModIds;

import java.util.ArrayList;

public class InitIntegrations {
    public static ArrayList<IModIntegration> integrationModules = new ArrayList<>();

    private static void buildModules() {
        loadModule(ModIds.bloodmagic, InitBloodMagic.class);
        loadModule(ModIds.botania, InitBotania.class);
        loadModule(ModIds.immersive_engineering, InitImmersiveEngineering.class);
        loadModule(ModIds.natures_aura, InitNaturesAura.class);
        loadModule(ModIds.quality_tools, InitQualityTools.class);
        loadModule(ModIds.thaumcraft, InitThaumcraft.class);
    }

    public static void preInit() {
        buildModules();
        integrationModules.forEach(IModIntegration::buildModules);
        integrationModules.forEach(IModIntegration::preInit);
    }

    public static void init() {
        integrationModules.forEach(IModIntegration::init);
    }

    public static void postInit() {
        integrationModules.forEach(IModIntegration::postInit);
    }

    private static void loadModule(ModIds mod, Class<? extends IModIntegration> moduleClass) {
        try {
            if(mod.isLoaded) {
                integrationModules.add(moduleClass.newInstance());
                LogHelper.info("Loaded integration module: " + mod.modId);
            }
        } catch (Exception e) {
            LogHelper.error("Failed to load integration module: " + mod.modId);
        }
    }

}
