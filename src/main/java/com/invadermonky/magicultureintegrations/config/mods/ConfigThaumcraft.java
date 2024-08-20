package com.invadermonky.magicultureintegrations.config.mods;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;

public class ConfigThaumcraft {
    public ArcaneBellowsIntegrations arcane_bellows = new ArcaneBellowsIntegrations();

    @Comment("Integration for survival mods such as SimpleDifficulty or Tough As Nails.")
    public SurvivalMods survival_mods = new SurvivalMods();

    public static class ArcaneBellowsIntegrations {
        @Comment("The Arcane Bellows will speed up Bewitchment's Witches' Oven.")
        public boolean bewitchment = true;
        @Comment("The Arcane Bellows will speed up Cooking for Blockheads' Oven.")
        public boolean cooking_for_blockheads = true;
        @Comment("The Arcane Bellows will speed up Future MC's Blast Furnace and Smoker.")
        public boolean futuremc = true;
        @Comment("The Arcane Bellows will speed up Mystical Agriculture's Essence Furnaces.")
        public boolean mystical_agriculture = true;
        @Comment("The Arcane Bellows will speed up Rustic's Alchemic Condenser and Advanced Alchemic Condenser.")
        public boolean rustic = true;
    }

    public static class SurvivalMods {
        public SurvivalItemTC thaumic_regulator = new SurvivalItemTC();

        public static class SurvivalItemTC {
            @Config.RequiresMcRestart
            @Comment("Enables this tool.")
            public boolean enable = true;
            @Comment("The delay between activations of the ")
            public int delay = 100;
            @Config.RangeInt(min = 1, max = 60)
            @Comment("How much energy consumed per operation. A value of 10 with a delay of 100 will consume 1 Vis every 30 seconds.")
            public int cost = 10;
        }
    }
}
