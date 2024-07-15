package com.invadermonky.magicultureintegrations.api.events;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public interface ITileTickEvent {
    /**
     * Updates this on every world tick.
     */
    void onTileEntityTick(World world, TileEntity tile);

    /**
     * Check whether the tile is correct for tick processing.
     */
    boolean checkTile(TileEntity tile);
}
