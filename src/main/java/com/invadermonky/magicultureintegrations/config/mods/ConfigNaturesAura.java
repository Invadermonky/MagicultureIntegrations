package com.invadermonky.magicultureintegrations.config.mods;

import com.invadermonky.magicultureintegrations.config.generics.GenericCostConfig;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.RequiresMcRestart;

public class ConfigNaturesAura {
    //@Comment("Due to a limitation of Nature's Aura's config string parsing, certain block properties could not be registered. Specifically if the block property contained an = sign.")
    //public NaturesAuraConfigExtended configExtended = new NaturesAuraConfigExtended();

    @RequiresMcRestart
    @Comment("Extraneous Firestarter integrations.")
    public ExtraneousFirestarterIntegrations extraneous_firestarter = new ExtraneousFirestarterIntegrations();
    @Comment("Integration for survival mods such as SimpleDifficulty or Tough As Nails.")
    public SurvivalMods survival_mods = new SurvivalMods();

    public static class NaturesAuraConfigExtended {
        @Comment("Additional conversion recipes for the Botanist's Pickaxe right click function. Each entry needs to be formatted as modid:input_block[prop1=value1,...]->modid:output_block[prop1=value1,...] where block state properties are optional")
        public String[] additionalBotanistPickaxeConversionsExtended = new String[] {};
        @Comment("Additional blocks that several mechanics identify as flowers. Each entry needs to be formatted as modid:block[prop1=value1,...] where block state properties are optional")
        public String[] additionalFlowersExtended = new String[] {};
        @Comment("Blocks that are exempt from being recognized as generatable ores for the passive ore generation effect. Each entry needs to be formatted as modid:block[prop1=value1,...] where block state properties are optional")
        public String[] oreExceptionsExtended = new String[] {};
    }

    public static class ExtraneousFirestarterIntegrations {
        @Comment("The Extraneous Firestarter will heat Bewitchment's Witches' Oven.")
        public boolean bewitchment = true;
        @Comment("The Extraneous Firestarter will heat Cooking for Blockheads' Oven.")
        public boolean cooking_for_blockheads = true;
        @Comment("The Extraneous Firestarter will heat Future MC's Smoker and Blast Furnace.")
        public boolean future_mc = true;
        @Comment("The Extraneous Firestarter will heat Mystical Agriculture's Essence Furnaces.")
        public boolean mystical_agriculture = true;
        @Comment("The Extraneous Firestarter will heat Rustic's Alchemic Condenser and Advanced Alchemic Condenser.")
        public boolean rustic = true;
    }

    public static class SurvivalMods {
        @Comment("")//TODO
        public GenericCostConfig environmental_ring = new GenericCostConfig(400);
    }
}
