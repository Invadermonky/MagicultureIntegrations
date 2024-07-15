package com.invadermonky.magicultureintegrations.api.mods.futuremc;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntity;
import thedarkcolour.futuremc.tile.TileFurnaceAdvanced;

public class FutureMCUtils {
    public static boolean canFurnaceSmelt(TileEntity furnaceTile) {
        if(!(furnaceTile instanceof TileFurnaceAdvanced))
            return false;

        TileFurnaceAdvanced furnace = (TileFurnaceAdvanced) furnaceTile;

        ItemStack input = furnace.getStackInSlot(0);
        if(input.isEmpty()) {
            return false;
        } else {
            ItemStack output = FurnaceRecipes.instance().getSmeltingResult(input);
            if(output.isEmpty()) {
                return false;
            } else if(furnace.getStackInSlot(2).isEmpty()) {
                return true;
            } else if(!furnace.getStackInSlot(2).isItemEqual(output)) {
                return false;
            } else {
                int result = furnace.getStackInSlot(2).getCount() + output.getCount();
                return result <= furnace.getInventoryStackLimit() && result <= output.getMaxStackSize();
            }
        }
    }
}
