package com.invadermonky.magicultureintegrations.core.mixins.industrialcraft;

import com.invadermonky.magicultureintegrations.config.MIConfigIntegrations;
import ic2.core.block.BlockTileEntity;
import ic2.core.crop.TileEntityCrop;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Random;

@Mixin(value = BlockTileEntity.class, remap = false)
public abstract class BlockTileEntityGrowableMixin implements IGrowable {
    //This mixin is only used for testing since IC2 crops take ungodly long times to grow.

    @Override
    public boolean canGrow(@NotNull World worldIn, @NotNull BlockPos pos, @NotNull IBlockState state, boolean isClient) {
        TileEntity tile = worldIn.getTileEntity(pos);
        if (tile instanceof TileEntityCrop) {
            TileEntityCrop tileCrop = (TileEntityCrop) tile;
            if (tileCrop.getCrop() != null && tileCrop.getCrop().canGrow(tileCrop)) {
                tileCrop.setCurrentSize(tileCrop.getCurrentSize() + 1);
                tileCrop.setGrowthPoints(0);
                tileCrop.dirty = true;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canUseBonemeal(@NotNull World worldIn, @NotNull Random rand, @NotNull BlockPos pos, @NotNull IBlockState state) {
        return MIConfigIntegrations.industrial_craft.growableCropsBonemeal;
    }

    @Override
    public void grow(@NotNull World worldIn, @NotNull Random rand, @NotNull BlockPos pos, @NotNull IBlockState state) {
        TileEntity tile = worldIn.getTileEntity(pos);
        if (tile instanceof TileEntityCrop) {
            TileEntityCrop tileCrop = (TileEntityCrop) tile;
            if (tileCrop.getCrop() != null && tileCrop.getCrop().canGrow(tileCrop)) {
                tileCrop.setCurrentSize(tileCrop.getCurrentSize() + 1);
                tileCrop.dirty = true;
            }
        }
    }
}
