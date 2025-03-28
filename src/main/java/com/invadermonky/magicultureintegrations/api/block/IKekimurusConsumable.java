package com.invadermonky.magicultureintegrations.api.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Interface used to add Kekimurus integration to blocks.
 */
public interface IKekimurusConsumable {
    /**
     * Returns true if the Kekumurus can consume this block.
     */
    boolean canBeConsumed(World world, BlockPos pos, IBlockState state);

    /**
     * Consumes the block, updating the block state or setting the block to air.
     */
    void consumeBlock(World world, BlockPos pos, IBlockState state);

    /**
     * Returns the amount of mana that will be gained by the Kekimurus when this block is consumed.
     */
    default int getManaGain(World world, BlockPos pos, IBlockState state) {
        return 1800;
    }

}
