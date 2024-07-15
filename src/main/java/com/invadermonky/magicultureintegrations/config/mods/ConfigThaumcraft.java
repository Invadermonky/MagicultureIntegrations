package com.invadermonky.magicultureintegrations.config.mods;

import net.minecraftforge.common.config.Config.Comment;

public class ConfigThaumcraft {
    public ArcaneBellowsIntegrations arcane_bellows = new ArcaneBellowsIntegrations();

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
}
