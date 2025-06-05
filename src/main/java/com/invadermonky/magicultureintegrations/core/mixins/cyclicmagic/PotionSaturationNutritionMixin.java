package com.invadermonky.magicultureintegrations.core.mixins.cyclicmagic;

import com.invadermonky.magicultureintegrations.api.mods.nutrition.NutritionUtils;
import com.lothrazar.cyclicmagic.potion.PotionEffectRegistry;
import com.lothrazar.cyclicmagic.potion.effect.PotionSaturation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = PotionSaturation.class, remap = false)
public class PotionSaturationNutritionMixin {
    @Inject(
            method = "performEffect",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/FoodStats;addStats(IF)V"
            ),
            remap = true
    )
    private void performEffectNutritionMixin(EntityLivingBase entity, int amplifier, CallbackInfo ci) {
        if (entity instanceof EntityPlayer) {
            NutritionUtils.doAutoFeed((EntityPlayer) entity, PotionEffectRegistry.SATURATION.getRegistryName());
        }
    }

    @Inject(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/FoodStats;addStats(IF)V"
            )
    )
    private void tickNutritionMixin(EntityLivingBase entity, CallbackInfo ci) {
        if (entity instanceof EntityPlayer) {
            NutritionUtils.doAutoFeed((EntityPlayer) entity, PotionEffectRegistry.SATURATION.getRegistryName());
        }
    }
}
