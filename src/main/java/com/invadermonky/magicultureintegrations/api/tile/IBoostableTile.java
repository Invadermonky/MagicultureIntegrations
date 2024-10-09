package com.invadermonky.magicultureintegrations.api.tile;

public interface IBoostableTile {
    /** Any additional checks the tile may need */
    boolean isTrueBoostable();
    /** Use to retrieve the "main" tile entity if it is a multiblock structure with dummy tiles. */
    IBoostableTile getTrueBoostable();
    /** Whether the tile entity will ignite if provided with fuel. */
    boolean canSmeltBoostable();
    /** The current item cook time */
    int getCookTimeBoostable();
    /** The maximum cook time for the item. Used for progress bar. */
    int getCookTimeMaxBoostable();
    /** Increase speed of items being smelted/cooked. Returns true if operation was successful. */
    void boostCookTimeBoostable(int boostAmount);
    /** Updates the tile in necessary. */
    void updateTileBoostable();
}
