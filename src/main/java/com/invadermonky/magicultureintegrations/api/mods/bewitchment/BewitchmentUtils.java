package com.invadermonky.magicultureintegrations.api.mods.bewitchment;

import com.bewitchment.Util;
import com.bewitchment.api.registry.OvenRecipe;
import com.bewitchment.common.block.tile.entity.TileEntityWitchesOven;
import com.invadermonky.magicultureintegrations.util.ReflectionHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.items.ItemStackHandler;

public class BewitchmentUtils {
    public static boolean canOvenSmelt(TileEntityWitchesOven oven) {
        try {
            ItemStackHandler inventory_up = (ItemStackHandler) ReflectionHelper.getFieldObject(oven, "inventory_up");
            ItemStackHandler inventory_down = (ItemStackHandler) ReflectionHelper.getFieldObject(oven, "inventory_down");
            OvenRecipe recipe = (OvenRecipe) ReflectionHelper.getFieldObject(oven, "recipe");

            if (inventory_up.getStackInSlot(2).isEmpty()) {
                return false;
            } else {
                if(recipe == null) {
                    return false;
                } else if(recipe.isValid(inventory_up, inventory_down)) {
                    return true;
                } else {
                    ItemStack stack = FurnaceRecipes.instance().getSmeltingResult(inventory_up.getStackInSlot(2));
                    return !stack.isEmpty() && Util.canMerge(inventory_down.getStackInSlot(0), stack);
                }
            }
        } catch(Exception e) {
            return false;
        }
    }
}
