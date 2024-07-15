package com.invadermonky.magicultureintegrations.api.mods.mysticalagriculture;

import com.blakebr0.mysticalagriculture.tileentity.furnace.TileEssenceFurnace;
import com.invadermonky.magicultureintegrations.util.ReflectionHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.NonNullList;

public class MysticalAgricultureUtils {
    @SuppressWarnings("unchecked")
    public static boolean canSmelt(TileEssenceFurnace furnace) {
        try {
            NonNullList<ItemStack> furnaceItemStacks = (NonNullList<ItemStack>) ReflectionHelper.getFieldObject(furnace, "furnaceItemStacks");
            ItemStack input = furnaceItemStacks.get(0);
            if(input.isEmpty()) {
                return false;
            } else {
                ItemStack output = FurnaceRecipes.instance().getSmeltingResult(input);
                if(output.isEmpty()) {
                    return false;
                } else if(furnaceItemStacks.get(2).isEmpty()) {
                    return true;
                } else if(!furnaceItemStacks.get(2).isItemEqual(output)) {
                    return false;
                } else {
                    int result = furnaceItemStacks.get(2).getCount() + output.getCount();
                    return result <= furnaceItemStacks.get(2).getMaxStackSize();
                }
            }
        } catch (Exception e) {
            return false;
        }
    }
}
