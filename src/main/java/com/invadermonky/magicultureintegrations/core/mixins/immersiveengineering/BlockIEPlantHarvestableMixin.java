package com.invadermonky.magicultureintegrations.core.mixins.immersiveengineering;

import blusunrize.immersiveengineering.common.IEContent;
import blusunrize.immersiveengineering.common.blocks.plant.BlockIECrop;
import com.invadermonky.magicultureintegrations.api.block.IHarvestableCrop;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = BlockIECrop.class, remap = false)
public abstract class BlockIEPlantHarvestableMixin implements IHarvestableCrop {
    @Shadow(remap = true)
    public abstract IBlockState getStateFromMeta(int meta);

    @Shadow(remap = true)
    public abstract int getMetaFromState(IBlockState state);

    @Override
    public BlockPos getHarvestPosition(World world, BlockPos cropPos) {
        return cropPos;
    }

    @Override
    public @NotNull HarvestResult getHarvestResult(World world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        IBlockState upState = world.getBlockState(pos.up());
        if (state.getBlock() == IEContent.blockCrop) {
            return upState.getBlock() == IEContent.blockCrop ? HarvestResult.HARVEST : HarvestResult.CLAIM;
        }
        return HarvestResult.PASS;
    }

    @Override
    public @NotNull NonNullList<ItemStack> harvestCrop(@Nullable EntityPlayer player, World world, BlockPos pos, boolean silkTouch, int fortune) {
        NonNullList<ItemStack> drops = NonNullList.create();
        if (this.getHarvestResult(world, pos) == HarvestResult.HARVEST) {
            IBlockState state = world.getBlockState(pos);
            IBlockState upState = world.getBlockState(pos.up());
            upState.getBlock().getDrops(drops, world, pos.up(), upState, fortune);
            world.setBlockToAir(pos.up());
            state.getBlock().getDrops(drops, world, pos, state, fortune);
            world.setBlockState(pos, state.getBlock().getDefaultState());
        }
        return drops;
    }
}
