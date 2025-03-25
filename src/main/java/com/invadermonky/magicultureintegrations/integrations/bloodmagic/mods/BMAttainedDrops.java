package com.invadermonky.magicultureintegrations.integrations.bloodmagic.mods;

import WayofTime.bloodmagic.ritual.harvest.HarvestRegistry;
import WayofTime.bloodmagic.ritual.harvest.IHarvestHandler;
import com.invadermonky.magicultureintegrations.api.IProxy;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import shadows.attained.blocks.BlockBulb;
import shadows.attained.blocks.BlockPlant;
import shadows.attained.init.ModRegistry;

import java.util.List;

public class BMAttainedDrops implements IProxy, IHarvestHandler {
    @Override
    public void init() {
        if(ConfigHandlerMI.integrations.blood_magic.harvest_ritual.attained_drops) {
            HarvestRegistry.registerHandler(this);
        }
    }

    @Override
    public boolean harvest(World world, BlockPos pos, IBlockState state, List<ItemStack> drops) {
        IBlockState up = world.getBlockState(pos.up());
        if(up.getBlock() instanceof BlockBulb) {
            NonNullList<ItemStack> blockDrops = NonNullList.create();
            up.getBlock().getDrops(blockDrops, world, pos.up(), up, 0);
            drops.addAll(blockDrops);
            world.destroyBlock(pos.up(), false);
            return true;
        }
        return false;
    }

    @Override
    public boolean test(World world, BlockPos pos, IBlockState state) {
        IBlockState stateUp = world.getBlockState(pos.up());
        return state.getBlock() instanceof BlockPlant
                && state.getValue(BlockPlant.AGE) == ((BlockPlant) ModRegistry.PLANT).getMaxAge()
                && stateUp.getBlock() == ModRegistry.BULB;
    }
}
