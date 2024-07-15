package com.invadermonky.magicultureintegrations.api.tile;

public interface IHeatableTile {
    /** Whether the tile entity will ignite if provided with fuel. */
    boolean canSmelt();
    /** The remaining fuel burn duration. */
    int getBurnTime();
    /** The current fuel max burn time. */
    int getBurnTimeMax();
    /** The current item cook time */
    int getCookTime();
    /** The maximum cook time for the item. Used for progress bar. */
    int getCookTimeMax();
    /** */
    void setBurnTimeMax(int burnTimeMax);
    /** Increase fuel burn duration. */
    void boostBurnTime(int boostAmount);
    /** Increase speed of items being smelted/cooked. */
    void boostCookTime(int boostAmount);
    /** Updates the tile in necessary. */
    void updateTile();
}
