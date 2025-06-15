package com.invadermonky.magicultureintegrations.core.mixins.actuallyadditions;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import de.ellpeck.actuallyadditions.api.internal.IFarmer;
import de.ellpeck.actuallyadditions.mod.misc.apiimpl.farmer.DefaultFarmerBehavior;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = DefaultFarmerBehavior.class, remap = false)
public abstract class DefaultFarmerBehaviorPlantableMixin {
    @Shadow
    protected abstract IBlockState getPlantablePlantFromStack(ItemStack stack, World world, BlockPos pos);

    @ModifyExpressionValue(
            method = "tryPlantSeed",
            at = @At(
                    value = "INVOKE",
                    target = "Lde/ellpeck/actuallyadditions/mod/misc/apiimpl/farmer/DefaultFarmerBehavior;defaultPlant(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/state/IBlockState;Lde/ellpeck/actuallyadditions/api/internal/IFarmer;I)Z"
            )
    )
    private boolean canBlockSupportPlantMixin(boolean canPlant, @Local(argsOnly = true) ItemStack seed, @Local(argsOnly = true) World world, @Local(argsOnly = true) BlockPos pos, @Local(argsOnly = true) IFarmer farmer) {
        if (!canPlant && seed.getItem() instanceof IPlantable) {
            IBlockState soilState = world.getBlockState(pos.down());
            if (soilState.getBlock().canSustainPlant(soilState, world, pos.down(), EnumFacing.UP, (IPlantable) seed.getItem())) {
                world.setBlockState(pos, this.getPlantablePlantFromStack(seed, world, pos));
                farmer.extractEnergy(350);
                return true;
            }
        }
        return canPlant;
    }
}
