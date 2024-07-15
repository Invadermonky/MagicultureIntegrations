package com.invadermonky.magicultureintegrations.api.mods.rustic;

import com.invadermonky.magicultureintegrations.util.ReflectionHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.items.ItemStackHandler;
import rustic.common.crafting.ICondenserRecipe;
import rustic.common.tileentity.TileEntityCondenserBase;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RusticUtils {
    public static boolean canBrew(TileEntityCondenserBase condenser) {
        try {
            Method canBrewMethod = condenser.getClass().getDeclaredMethod("canBrew");
            canBrewMethod.setAccessible(true);
            boolean isBrewing = (boolean) canBrewMethod.invoke(condenser);

            return isBrewing && hasValidRecipe(condenser);
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean hasValidRecipe(TileEntityCondenserBase condenser) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        ItemStackHandler internalStackHandler = (ItemStackHandler) ReflectionHelper.getFieldObject(condenser, "internalStackHandler");
        ICondenserRecipe currentRecipe = (ICondenserRecipe) ReflectionHelper.getFieldObject(condenser, "currentRecipe");
        boolean hasContentChanged = (boolean) ReflectionHelper.getFieldObject(condenser,"hasContentChanged");

        if (hasContentChanged) {
            Method refreshCurrentRecipeMethod = condenser.getClass().getDeclaredMethod("refreshCurrentRecipe");
            refreshCurrentRecipeMethod.setAccessible(true);
            refreshCurrentRecipeMethod.invoke(condenser);
            currentRecipe = (ICondenserRecipe) ReflectionHelper.getFieldObject(condenser, "currentRecipe");
        }

        if (currentRecipe == null) {
            return false;
        } else {
            return condenser.getAmount() >= currentRecipe.getFluid().amount && internalStackHandler.insertItem(0, currentRecipe.getResult(), true).isEmpty();
        }
    }

    public static void updateCondenser(TileEntityCondenserBase condenser) {
        NBTTagCompound tagCompound = new NBTTagCompound();
        condenser.writeToNBT(tagCompound);
        tagCompound.setInteger("BurnTime", (short)condenser.condenserBurnTime);
        tagCompound.setInteger("BrewTime", (short)condenser.brewTime);
        tagCompound.setInteger("BrewTimeTotal", (short)condenser.totalBrewTime);
        tagCompound.setInteger("ItemBurnTime", condenser.currentItemBurnTime);
        condenser.readFromNBT(tagCompound);
        IBlockState state = condenser.getWorld().getBlockState(condenser.getPos());
        condenser.getWorld().notifyBlockUpdate(condenser.getPos(), state, state, 3);
    }
}
