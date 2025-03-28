package com.invadermonky.magicultureintegrations.core.mixins.bloodmagic;

import WayofTime.bloodmagic.routing.IFluidFilter;
import WayofTime.bloodmagic.routing.RoutingFluidFilter;
import com.llamalad7.mixinextras.sugar.Local;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = RoutingFluidFilter.class, remap = false)
public class RoutingFluidFilterMixin {
    @Inject(method = "transferThroughInputFilter", at = @At(value = "RETURN", ordinal = 0), cancellable = true)
    private void transferThroughInputFilterMixin(IFluidFilter outputFilter, int maxTransfer, CallbackInfoReturnable<Integer> cir, @Local(ordinal = 2) int drained) {
        cir.setReturnValue(drained);
        cir.cancel();
    }
}
