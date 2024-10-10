package com.invadermonky.magicultureintegrations.core.mixins.bewitchment;

import com.bewitchment.api.registry.OvenRecipe;
import com.bewitchment.common.block.BlockWitchesOven;
import com.bewitchment.common.block.tile.entity.TileEntityWitchesOven;
import com.bewitchment.common.block.tile.entity.util.ModTileEntity;
import com.invadermonky.magicultureintegrations.api.tile.IHeatableTile;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ITickable;
import net.minecraftforge.items.ItemStackHandler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.botania.api.item.IExoflameHeatable;

@Mixin(value = TileEntityWitchesOven.class, remap = false)
public abstract class TileEntityWitchesOvenMixin extends ModTileEntity implements ITickable, IExoflameHeatable, IHeatableTile {
    @Shadow public int burnTime;
    @Shadow public int fuelBurnTime;
    @Shadow public int progress;
    @Shadow private boolean burning;
    @Shadow private OvenRecipe recipe;
    @Shadow @Final private ItemStackHandler inventory_up;
    @Shadow @Final private ItemStackHandler inventory_down;
    @Shadow protected abstract boolean isFurnaceRecipe();

    @Inject(method = "burnFuel", at = @At("HEAD"), cancellable = true)
    private void burnFuelMixin(int time, boolean consume, CallbackInfo ci) {
        if(!ConfigHandlerMI.fixes.fixWitchesOven)
            return;

        this.burnTime = time + 1;
        this.fuelBurnTime = this.burnTime;
        if(consume) {
            ItemStack stack = this.inventory_up.getStackInSlot(0);
            Item item = stack.getItem();
            stack.shrink(1);
            if(stack.isEmpty()) {
                this.inventory_up.setStackInSlot(0, item.getContainerItem(stack));
            }
        }

        if(!this.burning) {
            this.burning = this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).withProperty(BlockWitchesOven.LIT, true));
        }
        ci.cancel();
    }

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
    public int getCookTimeHeatable() {
        return this.progress;
    }

    @Override
    public int getCookTimeMaxHeatable() {
        return 200;
    }

    @Override
    public void setBurnTimeMaxHeatable(int burnTimeMax) {
        this.fuelBurnTime = burnTimeMax;
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
        if(!this.burning)
            this.burning = this.getWorld().setBlockState(this.getPos(), this.getWorld().getBlockState(this.getPos()).withProperty(BlockWitchesOven.LIT, true));
    }
}
