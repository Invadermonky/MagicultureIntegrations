package com.invadermonky.magicultureintegrations.config.mods;

import com.invadermonky.magicultureintegrations.config.generics.GenericCostConfig;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.RequiresMcRestart;

public class ConfigBotania {
    @RequiresMcRestart
    @Comment("Exoflame integrations.")
    public ExoflameIntegrations exoflame = new ExoflameIntegrations();
    public Flowers flowers = new Flowers();
    public SurvivalMods survival_mods = new SurvivalMods();

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

    public static class Flowers {
        @Comment("Botania functional flower. Uses Thaumcraft vis and mana to generate mana bursts. See Lexicon entry for more details")
        public Auromeria auromeria = new Auromeria();
        @Comment("Botania functional flower. Uses mana to regulate player temperature.")
        public GenericCostConfig gryllzalia = new GenericCostConfig(20);

        public static class Auromeria {
            @RequiresMcRestart
            @Comment("Enables the Auromeria flower and the Flux Lens.")
            public boolean enableAuromeria = true;
            @Comment("The amount of damage a flux mana burst deals when colliding with an entity.")
            public float fluxBurstDamage = 12.0f;
            @Comment("The amount of flux drained from the environment when the Auromeria drains flux when creating a mana burst. ")
            public double fluxDrainAmount = 0.05;
            @Comment("The base chance that the Auromeria will drain flux when generating a mana burst.")
            public double fluxDrainChance = 0.05;
            @Comment("The amount of flux generated when a flux mana burst explodes.")
            public double pollutionAmount = 0.2;
            @Comment("The chance of aura pollution when a flux mana burst explodes.")
            public double pollutionChance = 0.5;
        }
    }

    public static class SurvivalMods {
        @Comment("SimpleDifficulty/Tough As Nails mana-powered temperature control ring settings.")
        public GenericCostConfig ring_of_seasons = new GenericCostConfig();
    }
}
