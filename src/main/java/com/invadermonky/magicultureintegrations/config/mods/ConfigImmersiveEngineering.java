package com.invadermonky.magicultureintegrations.config.mods;

import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.RequiresMcRestart;

public class ConfigImmersiveEngineering {
    @RequiresMcRestart
    @Comment("External Heater integrations.")
    public ExternalHeaterIntegrations external_heater = new ExternalHeaterIntegrations();

    public static class ExternalHeaterIntegrations {
        @Comment("The External Heater will heat Bewitchment's Witches' Oven.")
        public boolean bewitchment = true;
        @Comment("The External heater will heat Future versions of Immersive Engineering have this behavior disabled.")
        public boolean future_mc = true;
        @Comment("The External Heater will heat Mystical Agriculture's Essence Furnaces.")
        public boolean mystical_agriculture = true;
        @Comment("The External Heater will heat Bewitchment's Witches' Oven.")
        public boolean rustic = true;
    }
}
