package com.invadermonky.magicultureintegrations.config.mods;

import net.minecraftforge.common.config.Config;

public class ConfigThaumcraft {
    @Config.RequiresMcRestart
    @Config.Comment("Allows Golems to harvest Agricraft crops.")
    public boolean agricraftHarvestSupport = true;
}
