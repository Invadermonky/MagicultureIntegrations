package com.invadermonky.magicultureintegrations.config.mods;

import net.minecraftforge.common.config.Config;

public class ConfigTheOneProbe {
    @Config.RequiresMcRestart
    @Config.Comment("Enables The One Probe Ore Stage support, fixing tooltip error on servers.")
    public boolean ore_stages = true;

    @Config.RequiresMcRestart
    @Config.Comment("Enables The One Probe Redstone Paste support.")
    public boolean redstone_paste = true;
}
