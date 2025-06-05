package com.invadermonky.magicultureintegrations.core.mixins.industrialforegoing;

import com.buuz135.industrial.proxy.ItemRegistry;
import com.buuz135.industrial.proxy.event.MeatFeederTickHandler;
import com.invadermonky.magicultureintegrations.api.mods.nutrition.NutritionUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = MeatFeederTickHandler.class, remap = false)
public class MeatFeederTickHandlerNutritionMixin {
    @Inject(
            method = "meatTick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/FoodStats;addStats(IF)V",
                    remap = true
            )
    )
    private static void meatTickMixin(ItemStack feeder, EntityPlayer player, CallbackInfoReturnable<Boolean> cir) {
        NutritionUtils.doAutoFeed(player, ItemRegistry.meatFeederItem.getRegistryName());
    }
}
