package com.invadermonky.magicultureintegrations.api.mods.cookingforblockheads;

import net.blay09.mods.cookingforblockheads.tile.TileOven;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.wrapper.RangedWrapper;

import javax.annotation.Nullable;
import java.lang.reflect.Field;

public class CookingForBlockheadsUtils {
    public static boolean canOvenCook(TileOven oven) {
        RangedWrapper processingHandler = getProcessingHandler(oven);
        if(processingHandler != null) {
            for (int i = 0; i < processingHandler.getSlots(); i++) {
                ItemStack cookingStack = processingHandler.getStackInSlot(i);
                if (!cookingStack.isEmpty() && oven.slotCookTime[i] != -1) {
                    return true;
                }
            }
        }
        return false;
    }

    @Nullable
    public static RangedWrapper getProcessingHandler(TileOven oven) {
        try {
            Field processingHandlerField = oven.getClass().getDeclaredField("itemHandlerProcessing");
            processingHandlerField.setAccessible(true);
            return (RangedWrapper) processingHandlerField.get(oven);
        } catch(Exception e) {
            return null;
        }
    }
}
