package com.invadermonky.magicultureintegrations.integrations;

import com.invadermonky.magicultureintegrations.api.IProxy;
import com.invadermonky.magicultureintegrations.api.mods.IntegrationModule;
import com.invadermonky.magicultureintegrations.integrations.animania.InitAnimania;
import com.invadermonky.magicultureintegrations.integrations.astralsorcery.InitAstralSorcery;
import com.invadermonky.magicultureintegrations.integrations.bloodmagic.InitBloodMagic;
import com.invadermonky.magicultureintegrations.integrations.botania.InitBotania;
import com.invadermonky.magicultureintegrations.integrations.enderio.InitEnderIO;
import com.invadermonky.magicultureintegrations.integrations.harvestcraft.InitHarvestcraft;
import com.invadermonky.magicultureintegrations.integrations.immersiveengineering.InitImmersiveEngineering;
import com.invadermonky.magicultureintegrations.integrations.naturesaura.InitNaturesAura;
import com.invadermonky.magicultureintegrations.integrations.qualitytools.InitQualityTools;
import com.invadermonky.magicultureintegrations.integrations.quark.InitQuark;
import com.invadermonky.magicultureintegrations.integrations.thaumcraft.InitThaumcraft;
import com.invadermonky.magicultureintegrations.integrations.theoneprobe.InitTheOneProbe;
import com.invadermonky.magicultureintegrations.util.LogHelper;
import com.invadermonky.magicultureintegrations.util.ModIds;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;

public class InitIntegrations {
    public static ArrayList<IntegrationModule> integrationModules = new ArrayList<>();

    private static void buildModules() {
        loadModule(ModIds.animania, InitAnimania.class);
        loadModule(ModIds.astral_sorcery, InitAstralSorcery.class);
        loadModule(ModIds.bloodmagic, InitBloodMagic.class);
        loadModule(ModIds.botania, InitBotania.class);
        loadModule(ModIds.enderio, InitEnderIO.class);
        loadModule(ModIds.harvestcraft, InitHarvestcraft.class);
        loadModule(ModIds.immersive_engineering, InitImmersiveEngineering.class);
        loadModule(ModIds.natures_aura, InitNaturesAura.class);
        loadModule(ModIds.quality_tools, InitQualityTools.class);
        loadModule(ModIds.quark, InitQuark.class);
        loadModule(ModIds.thaumcraft, InitThaumcraft.class);
        loadModule(ModIds.the_one_probe, InitTheOneProbe.class);
    }

    public static void preInit() {
        buildModules();
        integrationModules.forEach(module -> {
            module.buildModIntegrations();
            module.preInit();
            if (module.getModIntegrations() != null)
                module.getModIntegrations().forEach(IProxy::preInit);
        });
    }

    @SideOnly(Side.CLIENT)
    public static void preInitClient() {
        integrationModules.forEach(module -> {
            module.preInitClient();
            if (module.getModIntegrations() != null) {
                module.getModIntegrations().forEach(IProxy::preInitClient);
            }
        });
    }

    public static void init() {
        integrationModules.forEach(module -> {
            module.init();
            if (module.getModIntegrations() != null)
                module.getModIntegrations().forEach(IProxy::init);
        });
    }

    @SideOnly(Side.CLIENT)
    public static void initClient() {
        integrationModules.forEach(module -> {
            module.initClient();
            if (module.getModIntegrations() != null)
                module.getModIntegrations().forEach(IProxy::initClient);
        });
    }

    public static void postInit() {
        integrationModules.forEach(module -> {
            module.postInit();
            if (module.getModIntegrations() != null)
                module.getModIntegrations().forEach(IProxy::postInit);
        });
    }

    @SideOnly(Side.CLIENT)
    public static void postInitClient() {
        integrationModules.forEach(module -> {
            module.postInitClient();
            if (module.getModIntegrations() != null)
                module.getModIntegrations().forEach(IProxy::postInitClient);
        });
    }

    private static void loadModule(ModIds mod, Class<? extends IntegrationModule> moduleClass) {
        try {
            if (mod.isLoaded) {
                integrationModules.add(moduleClass.newInstance());
                LogHelper.info("Loaded integration module: " + mod.modId);
            }
        } catch (NoClassDefFoundError e) {
            LogHelper.error("Critical error occurred in " + mod.modId + " report this bug to the Magiculture Integrations issue tracker.");
            LogHelper.error(e);
        } catch (Exception e) {
            LogHelper.error("Failed to load integration module: " + mod.modId);
        }
    }

}
