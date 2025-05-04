package com.invadermonky.magicultureintegrations.core.mixins.industrialcraft;

import com.invadermonky.magicultureintegrations.api.tile.IBoostableTile;
import ic2.core.IHasGui;
import ic2.core.block.TileEntityInventory;
import ic2.core.block.steam.IMultiBlockController;
import ic2.core.block.steam.TileEntityCokeKiln;
import ic2.core.gui.dynamic.IGuiValueProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = TileEntityCokeKiln.class, remap = false)
public abstract class TileEntityCokeKilnBoostableMixin extends TileEntityInventory implements IMultiBlockController, IHasGui, IGuiValueProvider, IBoostableTile {
    @Shadow
    protected int progress;
    @Shadow
    protected int operationLength;
    @Shadow
    protected boolean isFormed;

    @Shadow
    protected abstract boolean canWork();

    @Override
    public boolean isTrueBoostable() {
        return true;
    }

    @Override
    public IBoostableTile getTrueBoostable() {
        return this;
    }

    @Override
    public boolean canSmeltBoostable() {
        return this.isFormed && this.canWork() && this.getActive();
    }

    @Override
    public int getCookTimeBoostable() {
        return this.progress;
    }

    @Override
    public int getCookTimeMaxBoostable() {
        return this.operationLength;
    }

    @Override
    public void boostCookTimeBoostable(int boostAmount) {
        this.progress = Math.min(getCookTimeMaxBoostable() - 1, getCookTimeBoostable() + boostAmount);
    }

    @Override
    public void updateTileBoostable() {

    }
}
