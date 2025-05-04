package com.invadermonky.magicultureintegrations.core.mixins.bewitchment;

import com.bewitchment.api.registry.OvenRecipe;
import com.bewitchment.common.block.BlockWitchesOven;
import com.bewitchment.common.block.tile.entity.TileEntityWitchesOven;
import com.bewitchment.common.block.tile.entity.util.ModTileEntity;
import com.invadermonky.magicultureintegrations.api.tile.IHeatableTile;
import net.minecraftforge.items.ItemStackHandler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = TileEntityWitchesOven.class, remap = false)
public abstract class TileEntityWitchesOvenHeatableMixin extends ModTileEntity implements IHeatableTile {
    @Shadow
    public int burnTime;
    @Shadow
    public int fuelBurnTime;
    @Shadow
    public int progress;
    @Shadow
    private boolean burning;
    @Shadow
    private OvenRecipe recipe;
    @Shadow
    @Final
    private ItemStackHandler inventory_up;
    @Shadow
    @Final
    private ItemStackHandler inventory_down;

    @Shadow
    protected abstract boolean isFurnaceRecipe();


    @Override
    public boolean canSmeltHeatable() {
        return !this.inventory_up.getStackInSlot(2).isEmpty() && (this.recipe != null && this.recipe.isValid(this.inventory_up, this.inventory_down) || this.isFurnaceRecipe());
    }

    @Override
    public int getBurnTimeHeatable() {
        return this.burnTime;
    }

    @Override
    public int getBurnTimeMaxHeatable() {
        return this.fuelBurnTime;
    }

    @Override
    public void setBurnTimeMaxHeatable(int burnTimeMax) {
        this.fuelBurnTime = burnTimeMax;
    }

    @Override
    public int getCookTimeHeatable() {
        return this.progress;
    }

    @Override
    public int getCookTimeMaxHeatable() {
        return 200;
    }

    @Override
    public void boostBurnTimeHeatable(int boostAmount) {
        this.burnTime += boostAmount;
        this.fuelBurnTime = Math.max(this.burnTime, this.fuelBurnTime);
    }

    @Override
    public void boostCookTimeHeatable(int boostAmount) {
        this.progress = Math.min(getCookTimeMaxHeatable() - 1, getCookTimeHeatable() + boostAmount);
    }

    @Override
    public void updateTileHeatable() {
        if (!this.burning)
            this.burning = this.getWorld().setBlockState(this.getPos(), this.getWorld().getBlockState(this.getPos()).withProperty(BlockWitchesOven.LIT, true));
    }
}
