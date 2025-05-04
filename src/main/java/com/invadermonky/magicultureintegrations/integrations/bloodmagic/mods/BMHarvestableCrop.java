package com.invadermonky.magicultureintegrations.integrations.bloodmagic.mods;

import WayofTime.bloodmagic.ritual.harvest.IHarvestHandler;
import com.invadermonky.magicultureintegrations.api.block.IHarvestableCrop;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of Blood Magic's IHarvestHandler for generic {@link IHarvestableCrop}.
 */
public class BMHarvestableCrop implements IHarvestHandler {
    @Override
    public boolean harvest(World world, BlockPos pos, IBlockState state, List<ItemStack> drops) {
        if (state.getBlock() instanceof IHarvestableCrop) {
            IHarvestableCrop harvestable = (IHarvestableCrop) state.getBlock();
            if (harvestable.getHarvestResult(world, pos) == IHarvestableCrop.HarvestResult.HARVEST) {
                drops.addAll(harvestable.harvestCrop(null, world, pos, false, 0).stream().filter(stack -> !stack.isEmpty()).collect(Collectors.toList()));
                BlockPos harvestPos = harvestable.getHarvestPosition(world, pos);
                world.playEvent(Constants.WorldEvents.BREAK_BLOCK_EFFECTS, harvestPos, Block.getStateId(world.getBlockState(harvestPos)));
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean test(World world, BlockPos pos, IBlockState state) {
        if (state.getBlock() instanceof IHarvestableCrop) {
            return ((IHarvestableCrop) state.getBlock()).getHarvestResult(world, pos) == IHarvestableCrop.HarvestResult.HARVEST;
        }
        return false;
    }
}
