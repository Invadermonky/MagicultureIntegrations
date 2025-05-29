package com.invadermonky.magicultureintegrations.core.mixins.newcrimsonrevelations;

import com.invadermonky.magicultureintegrations.api.block.IHarvestableCrop;
import mod.icarus.crimsonrevelations.block.CRBlockManaPod;
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

import java.util.ArrayList;

@Mixin(value = CRBlockManaPod.class, remap = false)
public abstract class CRBlockManaPodHarvestableMixin implements IHarvestableCrop {
    @Shadow
    @Final
    public static PropertyInteger AGE;

    @Shadow
    protected abstract int getAge(IBlockState state);

    @Shadow
    public abstract ArrayList<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune);

    @Override
    public BlockPos getHarvestPosition(World world, BlockPos cropPos) {
        return cropPos;
    }

    @Override
    public @NotNull HarvestResult getHarvestResult(World world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() instanceof IHarvestableCrop) {
            return this.getAge(state) >= 8 ? HarvestResult.HARVEST : HarvestResult.CLAIM;
        }
        return HarvestResult.PASS;
    }

    @Override
    public @NotNull NonNullList<ItemStack> harvestCrop(@Nullable EntityPlayer player, World world, BlockPos pos, boolean silkTouch, int fortune) {
        IBlockState state = world.getBlockState(pos);
        NonNullList<ItemStack> drops = NonNullList.create();
        if (state.getBlock() instanceof IHarvestableCrop && ((IHarvestableCrop) state.getBlock()).getHarvestResult(world, pos) == HarvestResult.HARVEST) {
            drops.addAll(getDrops(world, pos, state, fortune));
            world.setBlockState(pos, state.getBlock().getDefaultState());
        }
        return drops;
    }
}
