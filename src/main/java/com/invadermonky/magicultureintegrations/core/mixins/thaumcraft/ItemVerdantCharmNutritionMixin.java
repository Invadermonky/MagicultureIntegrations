package com.invadermonky.magicultureintegrations.core.mixins.thaumcraft;

import com.invadermonky.magicultureintegrations.api.mods.nutrition.NutritionUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.common.items.baubles.ItemVerdantCharm;

@Mixin(value = ItemVerdantCharm.class, remap = false)
public class ItemVerdantCharmNutritionMixin {
    @Inject(
            method = "onWornTick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/FoodStats;addStats(IF)V",
                    remap = true
            )
    )
    private void onWornTickMixin(ItemStack itemstack, EntityLivingBase player, CallbackInfo ci) {
        if (player instanceof EntityPlayer) {
            NutritionUtils.doAutoFeed((EntityPlayer) player, ItemsTC.charmVerdant.getRegistryName());
        }
    }
}
