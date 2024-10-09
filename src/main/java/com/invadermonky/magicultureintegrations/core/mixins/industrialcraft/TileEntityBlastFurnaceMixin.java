package com.invadermonky.magicultureintegrations.core.mixins.industrialcraft;

import com.invadermonky.magicultureintegrations.api.tile.IBoostableTile;
import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.MachineRecipeResult;
import ic2.api.upgrade.IUpgradableBlock;
import ic2.core.IHasGui;
import ic2.core.block.TileEntityInventory;
import ic2.core.block.machine.tileentity.TileEntityBlastFurnace;
import ic2.core.gui.dynamic.IGuiValueProvider;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidTank;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Collection;

@Mixin(value = TileEntityBlastFurnace.class, remap = false)
public abstract class TileEntityBlastFurnaceMixin extends TileEntityInventory implements IUpgradableBlock, IHasGui, IGuiValueProvider, IBoostableTile {
    @Shadow protected int progress;
    @Shadow protected int progressNeeded;
    @Shadow public abstract MachineRecipeResult<IRecipeInput, Collection<ItemStack>, ItemStack> getOutput();
    @Shadow public abstract boolean isHot();

    @Shadow @Final public FluidTank fluidTank;

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
        MachineRecipeResult<IRecipeInput, Collection<ItemStack>, ItemStack> result = this.getOutput();
        return result != null && this.isHot() && result.getRecipe().getMetaData().getInteger("fluid") <= this.fluidTank.getFluidAmount();
    }

    @Override
    public int getCookTimeBoostable() {
        return this.progress;
    }

    @Override
    public int getCookTimeMaxBoostable() {
        return this.progressNeeded;
    }

    @Override
    public void boostCookTimeBoostable(int boostAmount) {
        MachineRecipeResult<IRecipeInput, Collection<ItemStack>, ItemStack> result = this.getOutput();
        if(result != null) {
            this.progress = Math.min(getCookTimeMaxBoostable() - 1, getCookTimeBoostable() + boostAmount);
            this.fluidTank.drainInternal(result.getRecipe().getMetaData().getInteger("fluid") * boostAmount, true);
        }
    }

    @Override
    public void updateTileBoostable() {}
}
