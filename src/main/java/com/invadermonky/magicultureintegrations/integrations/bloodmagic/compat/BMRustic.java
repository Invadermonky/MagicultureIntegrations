package com.invadermonky.magicultureintegrations.integrations.bloodmagic.compat;

import WayofTime.bloodmagic.ritual.harvest.HarvestRegistry;
import WayofTime.bloodmagic.ritual.harvest.IHarvestHandler;
import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.mods.rustic.RusticHeatable;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.integrations.bloodmagic.events.BMFurnaceArrayHandler;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import rustic.common.blocks.crops.BlockGrapeLeaves;
import rustic.common.blocks.crops.BlockHerbBase;
import rustic.common.blocks.crops.BlockLeavesApple;
import rustic.common.blocks.crops.BlockStakeCrop;
import rustic.common.tileentity.TileEntityCondenserBase;

import java.util.List;

public class BMRustic implements IModIntegration, IHarvestHandler {
    @Override
    public void preInit() {
        if(ConfigHandlerMI.blood_magic.furnace_array.rustic) {
            BMFurnaceArrayHandler.registerFurnaceArrayHeatable(TileEntityCondenserBase.class, RusticHeatable.class);
        }
    }

    @Override
    public void init() {
        if(ConfigHandlerMI.blood_magic.harvest_ritual.rustic) {
            HarvestRegistry.registerHandler(this);
        }
    }

    @Override
    public void postInit() {

    }

    @Override
    public boolean harvest(World world, BlockPos pos, IBlockState state, List<ItemStack> drops) {
        NonNullList<ItemStack> blockDrops = NonNullList.create();
        boolean doHarvest = false;

        if (state.getBlock() instanceof BlockHerbBase) {
            blockDrops.addAll(((BlockHerbBase) state.getBlock()).getDrops(world, pos, state, 0));
            boolean foundSeed = false;
            for (ItemStack stack : blockDrops) {
                if (!stack.isEmpty() && stack.getItem() instanceof IPlantable) {
                    stack.shrink(1);
                    foundSeed = true;
                    break;
                }
            }
            if (foundSeed) {
                world.playEvent(2001, pos, Block.getStateId(state));
                world.setBlockState(pos, state.getBlock().getDefaultState());
                doHarvest = true;
            }
        } else if (state.getBlock() instanceof BlockGrapeLeaves) {
            BlockGrapeLeaves grapeLeaves = (BlockGrapeLeaves) state.getBlock();
            if (state.getValue(BlockGrapeLeaves.GRAPES)) {
                blockDrops.addAll(grapeLeaves.getDrops(world, pos, state, 0));
                world.setBlockState(pos, state.withProperty(BlockGrapeLeaves.GRAPES, false));
                doHarvest = true;
            }
        } else if (state.getBlock() instanceof BlockLeavesApple) {
            blockDrops.addAll(((BlockLeavesApple) state.getBlock()).getDrops(world, pos, state, 0));
            world.setBlockState(pos, state.getBlock().getDefaultState());
            doHarvest = true;
        } else if (state.getBlock() instanceof BlockStakeCrop) {
            blockDrops.addAll(((BlockStakeCrop) state.getBlock()).getDrops(world, pos, state, 0));
            world.setBlockState(pos, state.withProperty(BlockStakeCrop.AGE, ((BlockStakeCrop) state.getBlock()).getMaxAge() - 1));
            doHarvest = true;
        }

        if (doHarvest) {
            for (ItemStack drop : blockDrops) {
                if (!drop.isEmpty())
                    drops.add(drop);
            }
            return true;
        }

        return false;
    }

    @Override
    public boolean test(World world, BlockPos pos, IBlockState state) {
        return (state.getBlock() instanceof BlockHerbBase && state.getValue(BlockHerbBase.AGE) >= ((BlockHerbBase) state.getBlock()).getMaxAge()) ||
                (state.getBlock() instanceof BlockStakeCrop && state.getValue(BlockStakeCrop.AGE) >= ((BlockStakeCrop) state.getBlock()).getMaxAge()) ||
                (state.getBlock() instanceof BlockGrapeLeaves);
    }
}
