package com.invadermonky.magicultureintegrations.core.mixins.industrialcraft;

import com.invadermonky.magicultureintegrations.api.mods.nutrition.NutritionUtils;
import com.invadermonky.magicultureintegrations.integrations.nutrition.InitNutrition;
import ic2.core.item.armor.ItemArmorQuantumSuit;
import ic2.core.ref.ItemName;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Tuple;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(value = ItemArmorQuantumSuit.class, remap = false)
public class ItemArmorQuantumSuitNutritionMixin {

    @Inject(
            method = "onArmorTick",
            at = @At(
                    value = "INVOKE_ASSIGN",
                    target = "Lic2/core/item/ItemTinCan;onEaten(Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/item/ItemStack;)Lnet/minecraft/util/ActionResult;"
            )
    )
    private void onArmorTickMixin(World world, EntityPlayer player, ItemStack stack, CallbackInfo ci) {
        if (!world.isRemote) {
            Tuple<Float, List<String>> canStats = InitNutrition.getInstance().getCustomFoodStats(ItemName.filled_tin_can.getItemStack());
            if (canStats != null) {
                NutritionUtils.applyNutrition(player, canStats.getFirst(), canStats.getSecond().toArray(new String[0]));
            } else {
                NutritionUtils.doAutoFeed(player, ItemName.quantum_helmet.getItemStack().getItem().getRegistryName());
            }
        }
    }
}
