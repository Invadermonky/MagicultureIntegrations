package com.invadermonky.magicultureintegrations.config.modules;

import net.minecraftforge.common.config.Config;

public class ConfigFixes {
    @Config.Comment("Fixes the bug that causes Cooking For Blockhead's Oven to consume container items.")
    public boolean fixCfbOven = true;
    @Config.Comment("Fixes the bug that causes Bewitchment's Witches' Oven to consume container items.")
    public boolean fixWitchesOven = true;
}
