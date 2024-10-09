package com.invadermonky.magicultureintegrations.config.mods;

import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.RequiresMcRestart;

public class ConfigBotania {
    @RequiresMcRestart
    @Comment("Botania functional flower. Uses Thaumcraft vis and mana to generate mana bursts. See Lexicon entry for more details")
    public Auromeria auromeria = new Auromeria();
    public KekimurusIntegrations kekimurus = new KekimurusIntegrations();

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

    public static class KekimurusIntegrations {
        @Comment("The Kekimurus will now eat Twilight Forest's Experiment 115.")
        public boolean experiment115 = true;
        @Comment("Will the Kekimurus destroy Experiment 115 completely if the cake is consumed?")
        public boolean experiment115Consume = false;
    }
}
