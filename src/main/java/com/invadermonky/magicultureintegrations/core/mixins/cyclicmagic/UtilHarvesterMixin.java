package com.invadermonky.magicultureintegrations.core.mixins.cyclicmagic;

import com.invadermonky.magicultureintegrations.api.block.IHarvestableCrop;
import com.lothrazar.cyclicmagic.util.UtilHarvester;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = UtilHarvester.class, remap = false)
public class UtilHarvesterMixin {
    @Inject(method = "harvestSingle", at = @At("HEAD"), cancellable = true)
    private static void harvestSingleMixin(World world, BlockPos posCurrent, CallbackInfoReturnable<NonNullList<ItemStack>> cir) {
        Block block = world.getBlockState(posCurrent).getBlock();
        if (block instanceof IHarvestableCrop && ((IHarvestableCrop) block).getHarvestResult(world, posCurrent) == IHarvestableCrop.HarvestResult.HARVEST) {
            cir.setReturnValue(((IHarvestableCrop) block).harvestCrop(null, world, posCurrent, false, 0));
        }
    }
}
