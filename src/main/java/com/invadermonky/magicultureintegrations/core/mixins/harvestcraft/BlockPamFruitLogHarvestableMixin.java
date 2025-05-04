package com.invadermonky.magicultureintegrations.core.mixins.harvestcraft;

import com.invadermonky.magicultureintegrations.api.block.IHarvestableCrop;
import com.pam.harvestcraft.blocks.growables.BlockPamFruit;
import com.pam.harvestcraft.blocks.growables.BlockPamFruitLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = BlockPamFruitLog.class)
public abstract class BlockPamFruitLogHarvestableMixin implements IHarvestableCrop {
    @Override
    public BlockPos getHarvestPosition(World world, BlockPos cropPos) {
        return cropPos;
    }

    @Override
    public @NotNull HarvestResult getHarvestResult(World world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() instanceof BlockPamFruit) {
            return state.getValue(BlockPamFruit.AGE) >= ((BlockPamFruit) state.getBlock()).getMatureAge() ? HarvestResult.HARVEST : HarvestResult.CLAIM;
        }
        return HarvestResult.PASS;
    }

    @Override
    public @NotNull NonNullList<ItemStack> harvestCrop(@Nullable EntityPlayer player, World world, BlockPos pos, boolean silkTouch, int fortune) {
        NonNullList<ItemStack> drops = NonNullList.create();
        if (this.getHarvestResult(world, pos) == HarvestResult.HARVEST) {
            IBlockState state = world.getBlockState(pos);
            state.getBlock().getDrops(drops, world, pos, state, fortune);
            drops.remove(drops.size() - 1);
            world.setBlockState(pos, state.withProperty(BlockPamFruit.AGE, 0));
        }
        return drops;
    }
}
