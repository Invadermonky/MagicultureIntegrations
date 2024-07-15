package com.invadermonky.magicultureintegrations.config.mods;

import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.RequiresMcRestart;

public class ConfigBotania {
    @RequiresMcRestart
    @Comment("Exoflame integrations.")
    public ExoflameIntegrations exoflame = new ExoflameIntegrations();

    public static class ExoflameIntegrations {
        @Comment("The Exoflame will heat Cooking for Blockheads' Oven.")
        public boolean cooking_for_blockheads = true;
        @Comment("The Exoflame will heat Future MC's Blast Furnace and Smoker.")
        public boolean future_mc = true;
        @Comment("The Exoflame will heat Mystical Agriculture's Essence Furnaces.")
        public boolean mystical_agriculture = true;
        @Comment("The Exoflame will heat Rustic's Alchemic Condenser and Advanced Alchemic Condenser.")
        public boolean rustic = true;
    }
}
