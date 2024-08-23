package com.invadermonky.magicultureintegrations.config.mods;

import com.invadermonky.magicultureintegrations.config.generics.GenericCostConfig;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.RequiresMcRestart;

public class ConfigBloodMagic {
    @RequiresMcRestart
    @Comment("Burning Furnace Alchemic Array integrations.")
    public BurningFurnaceArray furnace_array = new BurningFurnaceArray();

    @RequiresMcRestart
    @Comment("Reap of the Harvest Moon ritual integrations.")
    public HarvestRitualIntegrations harvest_ritual = new HarvestRitualIntegrations();

    @RequiresMcRestart
    @Comment("")
    public Rituals ritual_configs = new Rituals();

    @RequiresMcRestart
    @Comment("Blood Magic Quality tools integration.")
    public QualityTools quality_tools = new QualityTools();



    @Comment("Integration for survival mods such as SimpleDifficulty or Tough As Nails.")
    public SurvivalMods survival_mods = new SurvivalMods();

    public static class HarvestRitualIntegrations {
        public boolean agricraft = true;
        public boolean attained_drops = true;
        public boolean bewitchment = true;
        public boolean harvestcraft = true;
        public boolean immersive_engineering = true;
        public boolean mystical_agriculture = true;
        public boolean mystical_world = true;
        public boolean roots = true;
        public boolean rustic = true;
    }

    public static class BurningFurnaceArray{
        @Comment("The Burning Furnace Array will heat Bewitchment's Witches' Oven.")
        public boolean bewitchment = true;
        @Comment("The Burning Furnace Array will heat Cooking for Blockheads' Oven.")
        public boolean cooking_for_blockheads = true;
        @Comment("The Burning Furnace Array will heat Future MC's Blast Furnace and Smoker.")
        public boolean future_mc = true;
        @Comment("The Burning Furnace Array will heat Mystical Agriculture's Infernium Furnaces.")
        public boolean mystical_agriculture = true;
        @Comment("The Burning Furnace Array will heat Rustic's Alchemic Condenser and Advanced Alchemic Condenser.")
        public boolean rustic = true;
    }

    public static class QualityTools {
        @Comment("Sentient armor will copy the quality of any currently worn armor when equipped.")
        public boolean sentientArmorQualityCopy = true;
    }

    public static class SurvivalMods {
        @Comment("")//TODO
        public GenericCostConfig temperature_sigil = new GenericCostConfig(150);
        @Comment("")//TODO
        public GenericCostConfig thirst_sigil = new GenericCostConfig(150);
    }

    public static class Rituals {
        @RequiresMcRestart
        @Comment("Ritual of the Soothing Hearth configuration. Ritual enable/disable configuration can be found in the Blood Magic rituals.cfg.")
        public RitualConfig soothing_hearth = new RitualConfig(10000, 10, 100);

        public static class RitualConfig {
            public int activationCost;
            public int refreshCost;
            public int refreshInterval;

            public RitualConfig(int activationCost, int refreshCost, int refreshInterval) {
                this.activationCost = activationCost;
                this.refreshCost = refreshCost;
                this.refreshInterval = refreshInterval;
            }
        }
    }
}
