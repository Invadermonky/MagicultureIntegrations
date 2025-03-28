package com.invadermonky.magicultureintegrations.core.mixins.rustic;

import com.invadermonky.magicultureintegrations.api.tile.IHeatableTile;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraftforge.fluids.capability.TileFluidHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import rustic.common.crafting.ICondenserRecipe;
import rustic.common.tileentity.TileEntityCondenserBase;

@Mixin(value = TileEntityCondenserBase.class, remap = false)
public abstract class TileEntityCondenserBaseHeatableMixin extends TileFluidHandler implements ITickable, IHeatableTile {
    @Shadow public int condenserBurnTime;
    @Shadow public int currentItemBurnTime;
    @Shadow public int brewTime;
    @Shadow public int totalBrewTime;
    @Shadow protected boolean hasContentChanged;
    @Shadow protected ICondenserRecipe currentRecipe;
    @Shadow protected ItemStackHandler internalStackHandler;
    @Shadow protected abstract boolean canBrew();
    @Shadow protected abstract void refreshCurrentRecipe();
    @Shadow public abstract int getAmount();
    @Shadow public abstract NBTTagCompound writeToNBT(NBTTagCompound tag);
    @Shadow public abstract void readFromNBT(NBTTagCompound tag);

    @Override
    public boolean canSmeltHeatable() {
        boolean isBrewing = this.canBrew();
        boolean validRecipe;
        if(this.hasContentChanged) {
            this.refreshCurrentRecipe();
        }
        if(this.currentRecipe == null) {
            return false;
        } else {
            validRecipe = this.getAmount() >= this.currentRecipe.getFluid().amount && this.internalStackHandler.insertItem(0, currentRecipe.getResult(), true).isEmpty();
        }
        return isBrewing && validRecipe;
    }

    @Override
    public int getBurnTimeHeatable() {
        return this.condenserBurnTime;
    }

    @Override
    public int getBurnTimeMaxHeatable() {
        return this.currentItemBurnTime;
    }

    @Override
    public int getCookTimeHeatable() {
        return this.brewTime;
    }

    @Override
    public int getCookTimeMaxHeatable() {
        return this.totalBrewTime;
    }

    @Override
    public void setBurnTimeMaxHeatable(int burnTimeMax) {
        this.currentItemBurnTime = burnTimeMax;
    }

    @Override
    public void boostBurnTimeHeatable(int boostAmount) {
        this.condenserBurnTime += boostAmount;
        setBurnTimeMaxHeatable(this.condenserBurnTime);
    }

    @Override
    public void boostCookTimeHeatable(int boostAmount) {
        this.brewTime = Math.min(getCookTimeMaxHeatable() - 1, getCookTimeHeatable() + boostAmount);
    }

    @Override
    public void updateTileHeatable() {
        NBTTagCompound tag = new NBTTagCompound();
        this.writeToNBT(tag);
        this.readFromNBT(tag);
        IBlockState state = this.getWorld().getBlockState(this.getPos());
        this.getWorld().notifyBlockUpdate(this.getPos(), state, state, 3);
    }
}
