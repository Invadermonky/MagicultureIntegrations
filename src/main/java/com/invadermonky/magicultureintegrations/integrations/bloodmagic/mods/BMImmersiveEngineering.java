package com.invadermonky.magicultureintegrations.integrations.bloodmagic.mods;

import WayofTime.bloodmagic.ritual.harvest.HarvestRegistry;
import WayofTime.bloodmagic.ritual.harvest.IHarvestHandler;
import blusunrize.immersiveengineering.common.IEContent;
import blusunrize.immersiveengineering.common.blocks.plant.BlockIECrop;
import com.invadermonky.magicultureintegrations.api.IProxy;
import com.invadermonky.magicultureintegrations.config.MIConfigIntegrations;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.Optional;

public class BMImmersiveEngineering implements IProxy, IHarvestHandler {
    @Override
    public void init() {
        if(MIConfigIntegrations.blood_magic.ritual_harvest.immersive_engineering) {
            HarvestRegistry.registerHandler(this);
        }
    }

    @Override
    public boolean harvest(World world, BlockPos pos, IBlockState state, List<ItemStack> drops) {
        NonNullList<ItemStack> blockDrops = NonNullList.create();
        BlockPos posUp = pos.up();
        IBlockState stateUp = world.getBlockState(posUp);
        state.getBlock().getDrops(blockDrops, world, pos, state, 0);
        stateUp.getBlock().getDrops(blockDrops, world, posUp, stateUp, 0);
        Optional<ItemStack> seedStackOpt = blockDrops.stream().filter(drop -> drop.getItem() == IEContent.itemSeeds).findFirst();
        if(seedStackOpt.isPresent()) {
            seedStackOpt.ifPresent(drop -> drop.shrink(1));
            blockDrops.removeIf(ItemStack::isEmpty);
            drops.addAll(blockDrops);
            world.destroyBlock(posUp, false);
            world.destroyBlock(pos, false);
            world.setBlockState(pos, state.getBlock().getDefaultState());
            return true;
        }
        return false;
    }

    @Override
    public boolean test(World world, BlockPos pos, IBlockState state) {
        return state.getBlock() instanceof BlockIECrop && world.getBlockState(pos.up()).getBlock() instanceof BlockIECrop;
    }
}
