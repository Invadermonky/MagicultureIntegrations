package com.invadermonky.magicultureintegrations.config.mods;

import net.minecraftforge.common.config.Config;

public class ConfigAstralSorcery {
    @Config.RequiresMcRestart
    public Features features = new Features();

    @Config.RequiresMcRestart
    public static class Features {
        @Config.Comment("Right clicking on a block at night while holding an Ichosic Resonator will display the fluid reservoir contained in that chunk.")
        public boolean show_reservoir = true;
    }
}
