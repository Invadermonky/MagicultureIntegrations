package com.invadermonky.magicultureintegrations.integrations.bloodmagic.mods;

import WayofTime.bloodmagic.ritual.harvest.HarvestRegistry;
import WayofTime.bloodmagic.ritual.harvest.IHarvestHandler;
import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.pam.harvestcraft.blocks.growables.BlockPamFruit;
import com.pam.harvestcraft.blocks.growables.BlockPamFruitLog;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class BMHarvestcraft implements IModIntegration, IHarvestHandler {
    @Override
    public void init() {
        if(ConfigHandlerMI.integrations.blood_magic.harvest_ritual.harvestcraft) {
            HarvestRegistry.registerHandler(this);
        }
    }

    @Override
    public boolean harvest(World world, BlockPos pos, IBlockState state, List<ItemStack> drops) {
        NonNullList<ItemStack> blockDrops = NonNullList.create();
        state.getBlock().getDrops(blockDrops, world, pos, state, 0);
        world.setBlockState(pos, state.getBlock().getDefaultState());
        world.playEvent(2001, pos, Block.getStateId(state));
        blockDrops.remove(0);
        blockDrops.forEach(item -> {
            if(!item.isEmpty())
                drops.add(item);
        });
        return true;
    }

    @Override
    public boolean test(World world, BlockPos pos, IBlockState state) {
        return (state.getBlock() instanceof BlockPamFruit && state.getValue(BlockPamFruit.AGE) >= ((BlockPamFruit) state.getBlock()).getMatureAge()) ||
                (state.getBlock() instanceof BlockPamFruitLog && state.getValue(BlockPamFruitLog.AGE) >= ((BlockPamFruitLog) state.getBlock()).getMatureAge());
    }
}
