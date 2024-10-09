package com.invadermonky.magicultureintegrations.config.mods;

import net.minecraftforge.common.config.Config;

public class ConfigQuark {
    @Config.RequiresMcRestart
    @Config.Comment("The cook time boost a sitting Foxhound provides a heatable tile. Processing time will be reduced by this amount every 3 ticks.")
    public int foxButtSpeedup = 1;
}
