package com.invadermonky.magicultureintegrations.integrations.thaumcraft.recipes;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.items.IGoggles;
import thaumcraft.common.lib.crafting.InfusionEnchantmentRecipe;
import thaumcraft.common.lib.enchantment.EnumInfusionEnchantment;

import java.util.List;

public class InfusionEnchantmentRecipeRevealing extends InfusionEnchantmentRecipe {
    public InfusionEnchantmentRecipeRevealing(EnumInfusionEnchantment ench, AspectList as, Object... components) {
        super(ench, as, components);
    }

    public InfusionEnchantmentRecipeRevealing(InfusionEnchantmentRecipe recipe, ItemStack in) {
        super(recipe, in);
    }

    @Override
    public boolean matches(List<ItemStack> input, ItemStack central, World world, EntityPlayer player) {
        return !(central.getItem() instanceof IGoggles) && super.matches(input, central, world, player);
    }
}
