package com.invadermonky.magicultureintegrations.core.mixins.forestry;

import com.invadermonky.magicultureintegrations.api.block.IHarvestableCrop;
import forestry.arboriculture.blocks.BlockForestryLeaves;
import forestry.arboriculture.tiles.TileLeaves;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = BlockForestryLeaves.class, remap = false)
public class BlockFruitLeavesHarvestableMixin implements IHarvestableCrop {
    @Override
    public BlockPos getHarvestPosition(World world, BlockPos cropPos) {
        return cropPos;
    }

    @Override
    public @NotNull HarvestResult getHarvestResult(World world, BlockPos pos) {
        TileEntity tile = world.getTileEntity(pos);
        if (tile instanceof TileLeaves && ((TileLeaves) tile).hasFruit()) {
            return ((TileLeaves) tile).getRipeness() < 1.0f ? HarvestResult.CLAIM : HarvestResult.HARVEST;
        }
        return HarvestResult.PASS;
    }

    @Override
    public @NotNull NonNullList<ItemStack> harvestCrop(@Nullable EntityPlayer player, World world, BlockPos pos, boolean silkTouch, int fortune) {
        NonNullList<ItemStack> drops = NonNullList.create();
        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() instanceof IHarvestableCrop && ((IHarvestableCrop) state.getBlock()).getHarvestResult(world, pos) == HarvestResult.HARVEST) {
            TileEntity tile = world.getTileEntity(pos);
            if (tile instanceof TileLeaves) {
                drops.addAll(((TileLeaves) tile).pickFruit(ItemStack.EMPTY));
            }
        }
        return drops;
    }

}
