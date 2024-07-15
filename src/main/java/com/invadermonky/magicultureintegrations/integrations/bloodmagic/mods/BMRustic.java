package com.invadermonky.magicultureintegrations.integrations.bloodmagic.mods;

import WayofTime.bloodmagic.ritual.harvest.HarvestRegistry;
import WayofTime.bloodmagic.ritual.harvest.IHarvestHandler;
import com.invadermonky.magicultureintegrations.api.mods.bloodmagic.IBMIntegration;
import com.invadermonky.magicultureintegrations.api.mods.rustic.RusticHeatable;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.integrations.bloodmagic.events.BMFurnaceArrayHandler;
import gnu.trove.map.hash.THashMap;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import rustic.common.blocks.ModBlocks;
import rustic.common.blocks.crops.*;
import rustic.common.tileentity.TileEntityCondenserBase;

import java.util.List;

public class BMRustic implements IBMIntegration {
    public static THashMap<Block, Integer> RUSTIC_CROPS = new THashMap<>();
    
    @Override
    public void registerCropHandlers() {
        if(ConfigHandlerMI.blood_magic.harvestRitual.rustic) {
            //Herbs
            RUSTIC_CROPS.put(Herbs.ALOE_VERA, Herbs.ALOE_VERA.getMaxAge());
            RUSTIC_CROPS.put(Herbs.BLOOD_ORCHID, Herbs.BLOOD_ORCHID.getMaxAge());
            RUSTIC_CROPS.put(Herbs.CHAMOMILE, Herbs.CHAMOMILE.getMaxAge());
            RUSTIC_CROPS.put(Herbs.CLOUDSBLUFF_CROP, Herbs.CLOUDSBLUFF_CROP.getMaxAge());
            RUSTIC_CROPS.put(Herbs.COHOSH, Herbs.COHOSH.getMaxAge());
            RUSTIC_CROPS.put(Herbs.CORE_ROOT_CROP, Herbs.CORE_ROOT_CROP.getMaxAge());
            RUSTIC_CROPS.put(Herbs.DEATHSTALK, Herbs.DEATHSTALK.getMaxAge());
            RUSTIC_CROPS.put(Herbs.GINSENG_CROP, Herbs.GINSENG_CROP.getMaxAge());
            RUSTIC_CROPS.put(Herbs.HORSETAIL, Herbs.HORSETAIL.getMaxAge());
            RUSTIC_CROPS.put(Herbs.MARSH_MALLOW_CROP, Herbs.MARSH_MALLOW_CROP.getMaxAge());
            RUSTIC_CROPS.put(Herbs.MOONCAP, Herbs.MOONCAP.getMaxAge());
            RUSTIC_CROPS.put(Herbs.WIND_THISTLE, Herbs.WIND_THISTLE.getMaxAge());
            //Leaves
            RUSTIC_CROPS.put(ModBlocks.APPLE_LEAVES, ModBlocks.APPLE_LEAVES.getMaxAge());
            //Stake Crops
            RUSTIC_CROPS.put(ModBlocks.TOMATO_CROP, ModBlocks.TOMATO_CROP.getMaxAge());
            RUSTIC_CROPS.put(ModBlocks.CHILI_CROP, ModBlocks.CHILI_CROP.getMaxAge());

            HarvestRegistry.registerHandler(new RusticHarvestHandler());
        }
    }

    @Override
    public void registerFurnaceArrayHandlers() {
        if(ConfigHandlerMI.blood_magic.furnaceArray.rustic) {
            BMFurnaceArrayHandler.registerFurnaceArrayHeatable(TileEntityCondenserBase.class, RusticHeatable.class);
        }
    }

    public static class RusticHarvestHandler implements IHarvestHandler {
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
            return (RUSTIC_CROPS.containsKey(state.getBlock()) && RUSTIC_CROPS.get(state.getBlock()) == state.getBlock().getMetaFromState(state)) || state.getBlock() instanceof BlockGrapeLeaves;
        }
    }
}
