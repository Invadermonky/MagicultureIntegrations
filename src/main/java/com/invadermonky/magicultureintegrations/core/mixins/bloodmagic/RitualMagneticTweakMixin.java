package com.invadermonky.magicultureintegrations.core.mixins.bloodmagic;

import WayofTime.bloodmagic.ritual.IMasterRitualStone;
import WayofTime.bloodmagic.ritual.types.RitualMagnetic;
import com.invadermonky.magicultureintegrations.config.tags.ModTags;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RitualMagnetic.class, remap = false)
public class RitualMagneticTweakMixin {
    @Inject(method = "performRitual",
            at = @At(value = "INVOKE",
                    target = "LWayofTime/bloodmagic/util/Utils;swapLocations(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)Z",
                    shift = At.Shift.AFTER
            ))
    private void performRitualMixin(IMasterRitualStone masterRitualStone, CallbackInfo ci,@Local(ordinal = 2) BlockPos pos) {
        World world = masterRitualStone.getWorldObj();
        world.setBlockState(pos, ModTags.RITUAL_MAGNETIC_REPLACEMENTS.getOrDefault(world.provider.getDimension(), Blocks.STONE.getDefaultState()));
    }
}
