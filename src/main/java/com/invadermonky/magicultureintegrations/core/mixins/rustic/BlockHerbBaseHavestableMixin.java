package com.invadermonky.magicultureintegrations.core.mixins.rustic;

import com.invadermonky.magicultureintegrations.api.block.IHarvestableCrop;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import rustic.common.blocks.crops.BlockHerbBase;

import java.util.List;

@Mixin(value = BlockHerbBase.class, remap = false)
public abstract class BlockHerbBaseHavestableMixin implements IHarvestableCrop {
    @Shadow
    @Final
    public static PropertyInteger AGE;

    @Shadow
    protected abstract int getAge(IBlockState state);

    @Shadow
    public abstract int getMaxAge();

    @Shadow
    public abstract List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune);

    @Override
    public BlockPos getHarvestPosition(World world, BlockPos cropPos) {
        return cropPos;
    }

    @Override
    public @NotNull HarvestResult getHarvestResult(World world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() instanceof IHarvestableCrop) {
            return getAge(state) >= getMaxAge() ? HarvestResult.HARVEST : HarvestResult.CLAIM;
        }
        return HarvestResult.PASS;
    }

    @Override
    public @NotNull NonNullList<ItemStack> harvestCrop(@Nullable EntityPlayer player, World world, BlockPos pos, boolean silkTouch, int fortune) {
        NonNullList<ItemStack> drops = NonNullList.create();
        if (getHarvestResult(world, pos) == HarvestResult.HARVEST) {
            IBlockState state = world.getBlockState(pos);
            drops.addAll(getDrops(world, pos, state, fortune));
            world.setBlockState(pos, state.withProperty(AGE, 0));
        }
        return drops;
    }
}
