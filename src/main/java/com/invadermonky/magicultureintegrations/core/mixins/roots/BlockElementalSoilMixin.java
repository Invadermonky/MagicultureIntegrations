package com.invadermonky.magicultureintegrations.core.mixins.roots;

import com.invadermonky.magicultureintegrations.api.block.IHarvestableCrop;
import epicsquid.roots.block.BlockElementalSoil;
import epicsquid.roots.init.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(value = BlockElementalSoil.class, remap = false)
public abstract class BlockElementalSoilMixin {
    @Shadow protected abstract void handleDrops(World world, BlockPos pos, List<ItemStack> drops);

    @Inject(
            method = "doHarvest(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/state/IBlockState;Lnet/minecraft/block/state/IBlockState;)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void doHarvestMixin(World world, BlockPos pos, IBlockState soil, IBlockState plant, CallbackInfo ci) {
        if(soil.getBlock() == ModBlocks.elemental_soil_water) {
            if(plant.getBlock() instanceof IHarvestableCrop && ((IHarvestableCrop) plant.getBlock()).getHarvestResult(world, pos) == IHarvestableCrop.HarvestResult.HARVEST) {
                IHarvestableCrop harvestable = (IHarvestableCrop) plant.getBlock();
                int speed = soil.getValue(BlockElementalSoil.WATER_SPEED);
                if(speed > 0) {
                    NonNullList<ItemStack> drops = harvestable.harvestCrop(null, world, pos, false, 0);
                    BlockPos harvestPos = harvestable.getHarvestPosition(world, pos);
                    float chance = ForgeEventFactory.fireBlockHarvesting(drops, world, harvestPos, world.getBlockState(harvestPos), 0, 1.0f, false, null);
                    drops.removeIf(drop -> drop.isEmpty() || chance < world.rand.nextFloat());
                    this.handleDrops(world, pos, drops);
                }
                ci.cancel();
            }
        }
    }
}
