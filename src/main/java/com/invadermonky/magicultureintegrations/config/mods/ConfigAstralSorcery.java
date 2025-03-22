package com.invadermonky.magicultureintegrations.config.mods;

import net.minecraftforge.common.config.Config;

public class ConfigAstralSorcery {
    public ConfigCrystalSorter crystal_sorter = new ConfigCrystalSorter();

    @Config.Comment("Right clicking on a block at night while holding an Ichosic Resonator will display the fluid reservoir contained in that chunk.")
    public boolean show_reservoir = true;

    public static class ConfigCrystalSorter {
        @Config.RequiresMcRestart
        @Config.Comment("Enables the Astral Sorcery crystal sorter block.")
        public boolean enable = true;

        @Config.Comment("The Crystal Sorter will sort sort according to size in addition to purity.")
        public boolean sortSize = true;
    }
}
