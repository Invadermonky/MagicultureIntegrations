package com.invadermonky.magicultureintegrations.core.mixins.engineersdecor;

import com.invadermonky.magicultureintegrations.api.tile.IHeatableTile;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraftforge.energy.IEnergyStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import wile.engineersdecor.blocks.BlockDecorFurnace;

@Mixin(value = BlockDecorFurnace.BTileEntity.class, remap = false)
public abstract class DecorFurnaceHeatableMixin extends TileEntity implements ITickable, ISidedInventory, IEnergyStorage, IHeatableTile {
    @Shadow public abstract int getField(int id);
    @Shadow public abstract void setField(int id, int value);
    @Shadow public abstract void markDirty();
    @Shadow protected abstract boolean canSmelt();

    @Override
    public boolean canSmeltHeatable() {
        return this.canSmelt();
    }

    @Override
    public int getBurnTimeHeatable() {
        return this.getField(0);
    }

    @Override
    public int getBurnTimeMaxHeatable() {
        return this.getField(1);
    }

    @Override
    public int getCookTimeHeatable() {
        return this.getField(2);
    }

    @Override
    public int getCookTimeMaxHeatable() {
        return this.getField(3);
    }

    @Override
    public void setBurnTimeMaxHeatable(int burnTimeMax) {
        this.setField(1, burnTimeMax);
    }

    @Override
    public void boostBurnTimeHeatable(int boostAmount) {
        this.setField(0, getBurnTimeHeatable() + boostAmount);
        setBurnTimeMaxHeatable(getBurnTimeHeatable());
    }

    @Override
    public void boostCookTimeHeatable(int boostAmount) {
        this.setField(2, Math.min(getCookTimeMaxHeatable() - 1, getCookTimeHeatable() + boostAmount));
    }

    @Override
    public void updateTileHeatable() {
        this.markDirty();
    }
}
