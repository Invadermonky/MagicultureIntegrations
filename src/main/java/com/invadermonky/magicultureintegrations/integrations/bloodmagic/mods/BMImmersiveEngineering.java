package com.invadermonky.magicultureintegrations.integrations.bloodmagic.mods;

import WayofTime.bloodmagic.ritual.harvest.HarvestRegistry;
import WayofTime.bloodmagic.ritual.harvest.IHarvestHandler;
import blusunrize.immersiveengineering.common.blocks.plant.BlockIECrop;
import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class BMImmersiveEngineering implements IModIntegration, IHarvestHandler {
    @Override
    public void init() {
        if(ConfigHandlerMI.integrations.blood_magic.harvest_ritual.immersive_engineering) {
            HarvestRegistry.registerHandler(this);
        }
    }

    @Override
    public boolean harvest(World world, BlockPos pos, IBlockState state, List<ItemStack> drops) {
        NonNullList<ItemStack> blockDrops = NonNullList.create();
        BlockPos posUp = pos.up();
        IBlockState stateUp = world.getBlockState(posUp);
        stateUp.getBlock().getDrops(blockDrops, world, posUp, stateUp, 0);
        for(ItemStack drop : blockDrops) {
            if(!drop.isEmpty())
                drops.add(drop);
        }
        world.setBlockState(posUp, Blocks.AIR.getDefaultState());
        return true;
    }

    @Override
    public boolean test(World world, BlockPos pos, IBlockState state) {
        return state.getBlock() instanceof BlockIECrop && world.getBlockState(pos.up()).getBlock() instanceof BlockIECrop;
    }
}
