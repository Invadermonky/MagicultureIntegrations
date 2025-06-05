package com.invadermonky.magicultureintegrations.core.mixins.travelersbackpack;

import com.invadermonky.magicultureintegrations.api.mods.nutrition.NutritionUtils;
import com.tiviacz.travelersbackpack.common.BackpackAbilities;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = BackpackAbilities.class, remap = false)
public class BackpackAbilitiesNutritionMixin {
    @Inject(
            method = "itemSunflower",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/EntityPlayer;getFoodStats()Lnet/minecraft/util/FoodStats;",
                    remap = true
            )
    )
    private void itemSunflowerNutritionMixin(EntityPlayer player, World world, ItemStack stack, CallbackInfo ci) {
        NutritionUtils.doAutoFeed(player, stack.getItem().getRegistryName());
    }
}
