package com.invadermonky.magicultureintegrations.core.mixins.botania;

import com.invadermonky.magicultureintegrations.api.mods.nutrition.NutritionUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.relic.ItemInfiniteFruit;

@Mixin(value = ItemInfiniteFruit.class, remap = false)
public class ItemInfiniteFruitNutritionMixin {
    @Inject(
            method = "onUsingTick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/FoodStats;addStats(IF)V"
            ),
            remap = true
    )
    private void onUsingTickNutritionMixin(ItemStack stack, EntityLivingBase living, int count, CallbackInfo ci) {
        if (living instanceof EntityPlayer) {
            NutritionUtils.doAutoFeed((EntityPlayer) living, ModItems.infiniteFruit.getRegistryName());
        }
    }
}
