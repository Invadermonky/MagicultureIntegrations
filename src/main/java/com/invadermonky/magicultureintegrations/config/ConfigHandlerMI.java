package com.invadermonky.magicultureintegrations.config;

import com.invadermonky.magicultureintegrations.MagicultureIntegrations;
import com.invadermonky.magicultureintegrations.config.mods.*;
import net.minecraftforge.common.config.Config;

@Config(modid = MagicultureIntegrations.MOD_ID)
public class ConfigHandlerMI {
    public static ConfigBloodMagic blood_magic = new ConfigBloodMagic();
    public static ConfigBotania botania = new ConfigBotania();
    public static ConfigImmersiveEngineering immersive_engineering = new ConfigImmersiveEngineering();
    public static ConfigNaturesAura natures_aura = new ConfigNaturesAura();
    public static ConfigQualityTools quality_tools = new ConfigQualityTools();
    public static ConfigThaumcraft thaumcraft = new ConfigThaumcraft();
}
