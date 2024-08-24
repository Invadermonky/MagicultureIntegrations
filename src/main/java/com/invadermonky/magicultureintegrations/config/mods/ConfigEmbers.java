package com.invadermonky.magicultureintegrations.config.mods;

import com.invadermonky.magicultureintegrations.config.generics.GenericCostConfig;
import net.minecraftforge.common.config.Config;

public class ConfigEmbers {
    @Config.Comment("Integration for survival mods such as SimpleDifficulty or Tough As Nails.")
    public SurvivalMods survival_mods = new SurvivalMods();

    public static class SurvivalMods {
        @Config.Comment("Embers SimpleDifficulty/Tough As Nails temperature regulation bauble settings.")
        public GenericCostConfig mantle_cloak = new GenericCostConfig(60, 1);
    }
}
