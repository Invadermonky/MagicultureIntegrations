package com.invadermonky.magicultureintegrations.api.block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

/**
 * <p>A WIP interface intended to be injected into crop blocks. It will be used to provide support for crops that need
 * special handlers, such as Agricraft or Attained Drops. This doubles as a simple way to add integration for less than
 * well implemented modded harvesting methods such as Thaumcraft Golems or Roots Elemental Soil.</p><br>
 */
public interface IHarvestableCrop {

    /**
     * Returns the harvested crop location. This is normally the crop position, but it can be elsewhere, such as the
     * case with Pumpkins and Melons.
     *
     * @param world The world object
     * @param cropPos The crop position
     * @return The harvested block position
     */
    BlockPos getHarvestPosition(World world, BlockPos cropPos);

    /**
     * <p>Returns whether this block belongs to this handler and whether it can be harvested.</p>
     * <u1>
     *     <li>{@link HarvestResult#HARVEST} - This block belongs to this handler and is ready to be harvested.</li>
     *     <li>{@link HarvestResult#CLAIM} - This block belongs to this handler but is not ready to be harvested.</li>
     *     <li>{@link HarvestResult#PASS} - This block does not belong to this handler.</li>
     * </u1>
     *
     * @param world The world object
     * @param pos The crop position
     * @return {@link HarvestResult} based on block harvestability
     */
    @Nonnull
    HarvestResult getHarvestResult(World world, BlockPos pos);

    /**
     * <p>Called when actually harvesting the crop. This method should handle block state resetting/destruction
     * and return any crop drops.</p>
     *
     * <p><b>DO NOT</b> fire the {@link net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent} in this method.
     * The individual harvest handlers should fire the event.</p>
     *
     * @param player The player or fake player harvesting the block.
     * @param world The world object
     * @param pos The crop position
     * @param silkTouch If the crop is being harvested with silk touch
     * @param fortune The fortune modifier on the tool harvested the crop
     * @return A list of items containing all drops from the crop block.
     */
    @Nonnull
    NonNullList<ItemStack> harvestCrop(@Nullable EntityPlayer player, World world, BlockPos pos, boolean silkTouch, int fortune);

    /**
     * A helper enum used for modded harvesting methods.
     */
    enum HarvestResult {
        /**
         * The block belongs to this harvest handler and is ready to be harvested. The {@link IHarvestableCrop#harvestCrop(EntityPlayer, World, BlockPos, boolean, int)}
         * will fire if this result is returned.
         */
        HARVEST,
        /**
         * The block belongs to this harvest handler. Return this result when the block belongs to the handler, but is
         * is not ready to be harvested.
         */
        CLAIM,
        /**
         * The harvest check will skip this block. Return this result if the crop does not belong to this harvest
         * handler.
         */
        PASS
    }
}
