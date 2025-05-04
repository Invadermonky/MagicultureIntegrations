package com.invadermonky.magicultureintegrations.integrations.astralsorcery.block.capability;

import hellfirepvp.astralsorcery.common.item.crystal.CrystalPropertyItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

public class CrystalStackHandler extends ItemStackHandler {
    public CrystalStackHandler() {
        super(1);
    }

    @Override
    public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
        if (!this.isItemValid(slot, stack))
            return stack;
        return super.insertItem(slot, stack, simulate);
    }

    @Override
    public boolean isItemValid(int slot, @NotNull ItemStack stack) {
        if (stack.getItem() instanceof CrystalPropertyItem) {
            return ((CrystalPropertyItem) stack.getItem()).provideCurrentPropertiesOrNull(stack) != null;
        }
        return false;
    }


}
