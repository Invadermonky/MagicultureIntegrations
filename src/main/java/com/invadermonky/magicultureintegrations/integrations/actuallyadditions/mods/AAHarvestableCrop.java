package com.invadermonky.magicultureintegrations.integrations.actuallyadditions.mods;

import com.invadermonky.magicultureintegrations.api.block.IHarvestableCrop;
import de.ellpeck.actuallyadditions.api.farmer.FarmerResult;
import de.ellpeck.actuallyadditions.api.farmer.IFarmerBehavior;
import de.ellpeck.actuallyadditions.api.internal.IFarmer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AAHarvestableCrop implements IFarmerBehavior {
    @Override
    public FarmerResult tryPlantSeed(ItemStack itemStack, World world, BlockPos blockPos, IFarmer iFarmer) {
        return FarmerResult.FAIL;
    }

    @Override
    public FarmerResult tryHarvestPlant(World world, BlockPos blockPos, IFarmer iFarmer) {
        int use = 250;
        IBlockState state = world.getBlockState(blockPos);
        if (iFarmer.getEnergy() >= use && state.getBlock() instanceof IHarvestableCrop) {
            IHarvestableCrop crop = (IHarvestableCrop) state.getBlock();
            if (crop.getHarvestResult(world, blockPos) == IHarvestableCrop.HarvestResult.HARVEST) {
                iFarmer.addToOutput(crop.harvestCrop(null, world, blockPos, false, 0));
                return FarmerResult.SUCCESS;
            }
        }
        return FarmerResult.FAIL;
    }

    @Override
    public int getPriority() {
        return 5;
    }
}
