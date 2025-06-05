package com.invadermonky.magicultureintegrations.core.mixins.minecraft;

import com.invadermonky.magicultureintegrations.api.mods.nutrition.NutritionUtils;
import com.invadermonky.magicultureintegrations.util.ModIds;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Potion.class)
public class PotionNutritionMixin {
    @Inject(
            method = "performEffect",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/FoodStats;addStats(IF)V"
            )
    )
    private void performEffectNutritionMixin(EntityLivingBase entityLivingBaseIn, int amplifier, CallbackInfo ci) {
        if (ModIds.nutrition.isLoaded && entityLivingBaseIn instanceof EntityPlayer) {
            NutritionUtils.doAutoFeed((EntityPlayer) entityLivingBaseIn, MobEffects.SATURATION.getRegistryName());
        }
    }
}
