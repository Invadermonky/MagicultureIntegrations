package com.invadermonky.magicultureintegrations.config.mods;

import com.invadermonky.magicultureintegrations.config.generics.SurvivalItem;
import net.minecraftforge.common.config.Config;

public class ConfigEmbers {
    @Config.Comment("Integration for survival mods such as SimpleDifficulty or Tough As Nails.")
    public SurvivalMods survival_mods = new SurvivalMods();

    public static class SurvivalMods {
        public SurvivalItem mantle_cloak = new SurvivalItem(60, 1);
    }
}
