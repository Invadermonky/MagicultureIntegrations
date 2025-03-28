package com.invadermonky.magicultureintegrations.core.mixins.industrialcraft;

import com.invadermonky.magicultureintegrations.api.tile.IBoostableTile;
import ic2.api.energy.tile.IHeatSource;
import ic2.api.recipe.IFermenterRecipeManager;
import ic2.api.recipe.Recipes;
import ic2.api.upgrade.IUpgradableBlock;
import ic2.core.IHasGui;
import ic2.core.block.TileEntityInventory;
import ic2.core.block.generator.tileentity.TileEntityBaseGenerator;
import ic2.core.block.machine.tileentity.TileEntityFermenter;
import ic2.core.gui.dynamic.IGuiValueProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.FluidTank;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = TileEntityFermenter.class, remap = false)
public abstract class TileEntityFermenterBoostableMixin extends TileEntityInventory implements IHasGui, IGuiValueProvider, IUpgradableBlock, IBoostableTile {
    /*
        Broken at the moment. The canSmeltBoostable function has to check the facing heat source for whether it has fuel
        and is running. Not including this check results in the "booster" blocks/entities boosting the process even when
        the Fermenter is not getting supplied heat from another source, in essence, it is actually processing the recipe
        instead of just boosting it.

        The IHeatSource interface does not contain an "active" state for the generator so it would need to be pulled from
        the actual tile entity. The TileEntityBaseGenerator does not contain this method either. Each unique machine has
        its own set of custom unique functions that control the "active" state of the generator. Having to check for each
        type of tile entity would be difficult to code and very bad for performance.
     */

    @Shadow @Final private FluidTank inputTank;
    @Shadow @Final private FluidTank outputTank;
    @Shadow private int heatBuffer;

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
        if(this.inputTank.getFluid() != null) {
            IFermenterRecipeManager.FermentationProperty fp = Recipes.fermenter.getFermentationInformation(this.inputTank.getFluid().getFluid());
            EnumFacing dir = this.getFacing();
            TileEntity te = this.getWorld().getTileEntity(this.pos.offset(dir));
            //Would have to check for each type of heat generator...
            boolean canPullHeat = te instanceof IHeatSource && te instanceof TileEntityBaseGenerator && ((TileEntityBaseGenerator) te).fuel > 0;
            return fp != null && canPullHeat && this.inputTank.getFluidAmount() >= fp.inputAmount && fp.outputAmount <= this.outputTank.getCapacity() - this.outputTank.getFluidAmount();
        }
        return false;
    }

    @Override
    public int getCookTimeBoostable() {
        return this.heatBuffer;
    }

    @Override
    public int getCookTimeMaxBoostable() {
        IFermenterRecipeManager.FermentationProperty fp = Recipes.fermenter.getFermentationInformation(this.inputTank.getFluid().getFluid());
        return fp.heat;
    }

    @Override
    public void boostCookTimeBoostable(int boostAmount) {
        this.heatBuffer = Math.min(getCookTimeMaxBoostable() - 1, getCookTimeBoostable() + (boostAmount * 2));
    }

    @Override
    public void updateTileBoostable() {
        this.markDirty();
    }
}
