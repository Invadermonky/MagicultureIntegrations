package com.invadermonky.magicultureintegrations.config;

import com.invadermonky.magicultureintegrations.MagicultureIntegrations;
import com.invadermonky.magicultureintegrations.config.mods.*;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = MagicultureIntegrations.MOD_ID)
public class ConfigHandlerMI {
    public static ConfigBloodMagic blood_magic = new ConfigBloodMagic();
    public static ConfigBotania botania = new ConfigBotania();
    public static ConfigEmbers embers = new ConfigEmbers();
    public static ConfigImmersiveEngineering immersive_engineering = new ConfigImmersiveEngineering();
    public static ConfigNaturesAura natures_aura = new ConfigNaturesAura();
    public static ConfigQualityTools quality_tools = new ConfigQualityTools();
    public static ConfigThaumcraft thaumcraft = new ConfigThaumcraft();

    @Mod.EventBusSubscriber(modid = MagicultureIntegrations.MOD_ID)
    public static class ConfigChangeListener {
        @SubscribeEvent
        public static void onConfigChange(ConfigChangedEvent.OnConfigChangedEvent event) {
            if(event.getModID().equals(MagicultureIntegrations.MOD_ID))
                ConfigManager.sync(MagicultureIntegrations.MOD_ID, Config.Type.INSTANCE);
        }
    }
}
