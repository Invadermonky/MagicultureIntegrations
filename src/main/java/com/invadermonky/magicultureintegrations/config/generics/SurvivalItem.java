package com.invadermonky.magicultureintegrations.config.generics;

import net.minecraftforge.common.config.Config;

public class SurvivalItem {
    @Config.RequiresMcRestart
    @Config.Comment("Enables this tool.")
    public boolean enable = true;
    @Config.Comment("The delay, in ticks, between each operation.")
    public int delay;
    @Config.Comment("The cost of each operation. This cost will be applied for as long as the tool is active or equipped.")
    public int cost;

    /**
     * Creates a Survival Item config with specified cooldown and cost.
     */
    public SurvivalItem(int delay, int cost) {
        this.delay = delay;
        this.cost = cost;
    }

    /**
     * Creates a Survival Item config with a cooldown of 100 ticks.
     */
    public SurvivalItem(int cost) {
        this(100, cost);
    }

    /**
     * Creates a Survival Item config with a cost of 150 per use and a cooldown of 100 ticks.
     */
    public SurvivalItem() {
        this(150);
    }
}
