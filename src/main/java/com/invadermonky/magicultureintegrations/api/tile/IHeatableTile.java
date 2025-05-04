package com.invadermonky.magicultureintegrations.api.tile;

public interface IHeatableTile {
    /** Whether the tile entity will ignite if provided with fuel. */
    boolean canSmeltHeatable();

    /** The remaining fuel burn duration. */
    int getBurnTimeHeatable();

    /** The current fuel max burn time. */
    int getBurnTimeMaxHeatable();

    /**  */
    void setBurnTimeMaxHeatable(int burnTimeMax);

    /** The current item cook time */
    int getCookTimeHeatable();

    /** The maximum cook time for the item. Used for progress bar. */
    int getCookTimeMaxHeatable();

    /** Increase fuel burn duration. Returns true if operation was successful. */
    void boostBurnTimeHeatable(int boostAmount);

    /** Increase speed of items being smelted/cooked. Returns true if operation was successful. */
    void boostCookTimeHeatable(int boostAmount);

    /** Updates the tile in necessary. */
    void updateTileHeatable();
}
