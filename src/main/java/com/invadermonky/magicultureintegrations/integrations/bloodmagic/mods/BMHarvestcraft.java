package com.invadermonky.magicultureintegrations.integrations.bloodmagic.mods;

import WayofTime.bloodmagic.ritual.harvest.HarvestRegistry;
import WayofTime.bloodmagic.ritual.harvest.IHarvestHandler;
import com.invadermonky.magicultureintegrations.api.mods.bloodmagic.IBMIntegration;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.pam.harvestcraft.Reference;
import com.pam.harvestcraft.blocks.FruitRegistry;
import com.pam.harvestcraft.blocks.growables.BlockPamFruit;
import gnu.trove.map.hash.THashMap;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class BMHarvestcraft implements IBMIntegration {
    public static THashMap<Block, Integer> PAM_FRUIT;

    @Override
    public void registerCropHandlers() {
        if(ConfigHandlerMI.blood_magic.harvestRitual.harvestcraft) {
            PAM_FRUIT = new THashMap<>(FruitRegistry.fruits.size() + FruitRegistry.logs.size());
            FruitRegistry.fruits.forEach(fruit -> PAM_FRUIT.put(fruit, fruit.getMatureAge()));
            FruitRegistry.logs.forEach((k, log) -> PAM_FRUIT.put(log, log.getMatureAge()));
            Block appleBlock = Block.REGISTRY.getObject(new ResourceLocation(Reference.MODID, "pamapple"));
            if(appleBlock instanceof BlockPamFruit) {
                PAM_FRUIT.put(appleBlock, ((BlockPamFruit) appleBlock).getMatureAge());
            }
            HarvestRegistry.registerHandler(new BMHarvestcraftHarvestHandler());
        }
    }

    public static class BMHarvestcraftHarvestHandler implements IHarvestHandler {
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
            return PAM_FRUIT.containsKey(state.getBlock()) && state.getBlock().getMetaFromState(state) == PAM_FRUIT.get(state.getBlock());
        }
    }
}
