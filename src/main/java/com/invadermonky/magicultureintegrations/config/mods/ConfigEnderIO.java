package com.invadermonky.magicultureintegrations.config.mods;

import net.minecraftforge.common.config.Config;

public class ConfigEnderIO {
    @Config.RequiresMcRestart
    @Config.Comment("Attained Drops crops can now be harvested by the Farming Station.")
    public boolean attainedDropsHarvest = true;

    @Config.RequiresMcRestart
    @Config.Comment("Fixes the crash that occurs when Ender IO's Farming Station attempts to harvest Agricraft crops.")
    public boolean fixAgricraftHarvestCrash = true;

}
