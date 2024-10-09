package com.invadermonky.magicultureintegrations.config;

import com.invadermonky.magicultureintegrations.MagicultureIntegrations;
import com.invadermonky.magicultureintegrations.config.mods.*;
import com.invadermonky.magicultureintegrations.config.modules.ConfigFixes;
import com.invadermonky.magicultureintegrations.config.modules.ConfigHeatables;
import com.invadermonky.magicultureintegrations.config.modules.ConfigMixins;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = MagicultureIntegrations.MOD_ID)
public class ConfigHandlerMI {
    @Config.RequiresMcRestart
    public static ConfigMixins _mixins = new ConfigMixins();
    @Config.RequiresMcRestart
    @Config.Comment("Configuration settings for the various heatable/boostable furnaces and devices.")
    public static ConfigHeatables heatables = new ConfigHeatables();
    @Config.RequiresMcRestart
    public static ConfigFixes fixes = new ConfigFixes();
    public static ConfigIntegrations integrations = new ConfigIntegrations();

    public static class ConfigIntegrations {
        public ConfigAstralSorcery astral_sorcery = new ConfigAstralSorcery();
        public ConfigBloodMagic blood_magic = new ConfigBloodMagic();
        public ConfigBotania botania = new ConfigBotania();
        public ConfigQualityTools quality_tools = new ConfigQualityTools();
        public ConfigQuark quark = new ConfigQuark();
        public ConfigTheOneProbe the_one_probe = new ConfigTheOneProbe();
    }


    @Mod.EventBusSubscriber(modid = MagicultureIntegrations.MOD_ID)
    public static class ConfigChangeListener {
        @SubscribeEvent
        public static void onConfigChange(ConfigChangedEvent.OnConfigChangedEvent event) {
            if(event.getModID().equals(MagicultureIntegrations.MOD_ID))
                ConfigManager.sync(MagicultureIntegrations.MOD_ID, Config.Type.INSTANCE);
        }
    }
}
