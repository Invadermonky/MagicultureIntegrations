package com.invadermonky.magicultureintegrations.integrations.bloodmagic.compat;

import WayofTime.bloodmagic.ritual.harvest.HarvestRegistry;
import WayofTime.bloodmagic.ritual.harvest.IHarvestHandler;
import com.blakebr0.mysticalagriculture.blocks.crop.BlockMysticalCrop;
import com.blakebr0.mysticalagriculture.tileentity.furnace.TileEssenceFurnace;
import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.mods.mysticalagriculture.MysticalAgricultureHeatable;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.integrations.bloodmagic.events.BMFurnaceArrayHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class BMMysticalAgriculture implements IModIntegration, IHarvestHandler {
    @Override
    public void preInit() {
        if (ConfigHandlerMI.blood_magic.furnaceArray.mystical_agriculture) {
            BMFurnaceArrayHandler.registerFurnaceArrayHeatable(TileEssenceFurnace.class, MysticalAgricultureHeatable.class);
        }
    }

    @Override
    public void init() {
        if (ConfigHandlerMI.blood_magic.harvestRitual.mystical_agriculture) {
            HarvestRegistry.registerHandler(this);
        }
    }

    @Override
    public void postInit() {

    }

    @Override
    public boolean harvest(World world, BlockPos pos, IBlockState state, List<ItemStack> drops) {
        NonNullList<ItemStack> currentDrops = NonNullList.create();
        BlockMysticalCrop crop = (BlockMysticalCrop) state.getBlock();
        crop.getDrops(currentDrops, world, pos, state, 0);
        boolean foundSeed = false;
        for(ItemStack drop : currentDrops) {
            if(!drop.isEmpty() && drop.getItem() == crop.getSeed()) {
                drop.shrink(1);
                foundSeed = true;
                break;
            }
        }
        if(foundSeed) {
            world.setBlockState(pos, crop.getDefaultState());
            for(ItemStack drop : currentDrops) {
                if(!drop.isEmpty())
                    drops.add(drop);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean test(World world, BlockPos pos, IBlockState state) {
        return state.getBlock() instanceof BlockMysticalCrop && ((BlockMysticalCrop) state.getBlock()).isMaxAge(state);
    }
}
