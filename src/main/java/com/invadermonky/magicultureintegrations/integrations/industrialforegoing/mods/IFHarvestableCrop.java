package com.invadermonky.magicultureintegrations.integrations.industrialforegoing.mods;

import com.buuz135.industrial.api.plant.PlantRecollectable;
import com.invadermonky.magicultureintegrations.api.block.IHarvestableCrop;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Collections;
import java.util.List;

public class IFHarvestableCrop extends PlantRecollectable {
    public IFHarvestableCrop() {
        super("mi_harvestable_crop");
    }

    @Override
    public boolean canBeHarvested(World world, BlockPos blockPos, IBlockState state) {
        if (state.getBlock() instanceof IHarvestableCrop) {
            return ((IHarvestableCrop) state.getBlock()).getHarvestResult(world, blockPos) == IHarvestableCrop.HarvestResult.HARVEST;
        }
        return false;
    }

    @Override
    public List<ItemStack> doHarvestOperation(World world, BlockPos blockPos, IBlockState state) {
        if (state.getBlock() instanceof IHarvestableCrop && ((IHarvestableCrop) state.getBlock()).getHarvestResult(world, blockPos) == IHarvestableCrop.HarvestResult.HARVEST) {
            return ((IHarvestableCrop) state.getBlock()).harvestCrop(null, world, blockPos, false, 0);
        }
        return Collections.emptyList();
    }

    @Override
    public boolean shouldCheckNextPlant(World world, BlockPos blockPos, IBlockState iBlockState) {
        return true;
    }

    @Override
    public int getPriority() {
        return 0;
    }
}
