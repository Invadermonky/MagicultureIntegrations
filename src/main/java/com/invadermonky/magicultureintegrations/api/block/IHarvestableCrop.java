package com.invadermonky.magicultureintegrations.api.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

/**
 * A WIP interface intended to be injected into crop blocks. It will be used to provide support for crops that need
 * special handlers for modded harvesting methods such as Thaumcraft Golems and Roots Elemental Soil. <b>DO NOT</b> use
 * this interface until it has been finalized.
 */
public interface IHarvestableCrop {
    /**
     * Returns true if this block will be uprooted when harvested.
     */
    boolean canHarvest(World world, BlockPos pos, IBlockState state);

    /**
     * Returns true if this block has a special harvest that does not uproot the plant itself. An example of this
     * would be Sugar Cane or Cacti.
     */
    boolean hasSpecialHarvest(World world, BlockPos pos, IBlockState state);

    /**
     * Returns a list of items dropped when the crop is harvested.
     */
    NonNullList<ItemStack> getHarvestDrops(World world, BlockPos pos, IBlockState state);

    /**
     * Called when actually harvesting the crop. This should handle BlockState resetting. Returns true
     * if block was harvested, false otherwise.
     */
    void harvestCrop(World world, BlockPos pos, IBlockState state, @Nullable EntityPlayer player, boolean dropCropsInWorld);
}
