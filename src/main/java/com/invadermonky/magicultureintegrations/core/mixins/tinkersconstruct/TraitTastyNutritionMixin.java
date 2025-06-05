package com.invadermonky.magicultureintegrations.core.mixins.tinkersconstruct;

import com.invadermonky.magicultureintegrations.api.mods.nutrition.NutritionUtils;
import com.invadermonky.magicultureintegrations.util.ModIds;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import slimeknights.tconstruct.tools.traits.TraitTasty;

@Mixin(value = TraitTasty.class, remap = false)
public class TraitTastyNutritionMixin {
    @Inject(
            method = "nom",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/FoodStats;addStats(IF)V",
                    remap = true
            )
    )
    private void nomNutritionMixin(ItemStack tool, EntityPlayer player, CallbackInfo ci) {
        NutritionUtils.doAutoFeed(player, new ResourceLocation(ModIds.tinkers_construct.modId, "tasty"));
    }

}
