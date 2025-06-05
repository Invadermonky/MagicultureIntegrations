package com.invadermonky.magicultureintegrations.core.mixins.bloodmagic.rituals;

import WayofTime.bloodmagic.ritual.IMasterRitualStone;
import WayofTime.bloodmagic.ritual.types.RitualFullStomach;
import com.invadermonky.magicultureintegrations.api.mods.nutrition.NutritionUtils;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RitualFullStomach.class, remap = false)
public class RitualFullStomachNutritionMixin {
    @Inject(
            method = "performRitual",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/FoodStats;addStats(Lnet/minecraft/item/ItemFood;Lnet/minecraft/item/ItemStack;)V",
                    remap = true
            )
    )
    private void performRitualNutritionMixin(IMasterRitualStone masterRitualStone, CallbackInfo ci, @Local(name = "player") EntityPlayer player, @Local(name = "stack") ItemStack stack) {
        NutritionUtils.applyNutrition(player, stack);
    }
}
