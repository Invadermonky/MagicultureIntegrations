package com.invadermonky.magicultureintegrations.config.mods;

import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.RequiresMcRestart;

public class ConfigBloodMagic {
    @RequiresMcRestart
    @Comment("Burning Furnace Alchemic Array integrations.")
    public BurningFurnaceArray furnaceArray = new BurningFurnaceArray();

    @RequiresMcRestart
    @Comment("Reap of the Harvest Moon ritual integrations.")
    public HarvestRitualIntegrations harvestRitual = new HarvestRitualIntegrations();

    public QualityTools quality_tools = new QualityTools();

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
}
