package com.invadermonky.magicultureintegrations.config.mods;

import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.RequiresMcRestart;

public class ConfigBloodMagic {
    @RequiresMcRestart
    @Comment("Reap of the Harvest Moon ritual integrations.")
    public HarvestRitualIntegrations harvest_ritual = new HarvestRitualIntegrations();

    @RequiresMcRestart
    @Comment("Blood Magic Quality tools integration.")
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
        public boolean thaumic_additions = true;
    }

    public static class QualityTools {
        @Comment("Sentient armor will copy the quality of any currently worn armor when equipped.")
        public boolean sentientArmorQualityCopy = true;
    }
}
