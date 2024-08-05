package com.invadermonky.magicultureintegrations.api.mods.bewitchment;

import com.bewitchment.Util;
import com.bewitchment.api.registry.OvenRecipe;
import com.bewitchment.common.block.tile.entity.TileEntityWitchesOven;
import com.invadermonky.magicultureintegrations.util.ReflectionHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.items.ItemStackHandler;

import java.lang.reflect.Method;

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

    public static boolean addOvenBurnTime(TileEntityWitchesOven oven, int burnTime) {
        try {
            Method burnFuelMethod = oven.getClass().getDeclaredMethod("burnFuel", int.class, boolean.class);
            burnFuelMethod.setAccessible(true);
            burnFuelMethod.invoke(oven, burnTime, false);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void updateOven(TileEntityWitchesOven oven, boolean active) {
        //TODO: Fix this not updating on servers.
        NBTTagCompound tagCompound = new NBTTagCompound();
        oven.writeToNBT(tagCompound);
        if(tagCompound.getBoolean("burning") != active) {
            tagCompound.setBoolean("burning", active);
            oven.readFromNBT(tagCompound);
            IBlockState state = oven.getWorld().getBlockState(oven.getPos());
            oven.getWorld().notifyBlockUpdate(oven.getPos(), state, state, 3);
        }
    }

}
