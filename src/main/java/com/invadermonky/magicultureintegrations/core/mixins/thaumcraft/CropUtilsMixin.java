package com.invadermonky.magicultureintegrations.core.mixins.thaumcraft;

import com.infinityraider.agricraft.api.v1.crop.IAgriCrop;
import com.infinityraider.agricraft.tiles.TileEntityCrop;
import com.infinityraider.infinitylib.utility.WorldHelper;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import thaumcraft.common.lib.utils.CropUtils;

@Mixin(value = CropUtils.class, remap = false)
public class CropUtilsMixin {

    @Inject(
            method = "isGrownCrop",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;getBlockState(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;",
                    ordinal = 1
            ),
            cancellable = true
    )
    private static void isGrownCrop(World world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if(!ConfigHandlerMI.integrations.thaumcraft.agricraftHarvestSupport)
            return;

        if(WorldHelper.getTile(world, pos, TileEntityCrop.class).filter(IAgriCrop::canBeHarvested).isPresent()) {
            cir.setReturnValue(true);
        }
    }
}
