package com.invadermonky.magicultureintegrations.core.mixins.cookingforblockheads;

import blusunrize.immersiveengineering.api.tool.ExternalHeaterHandler;
import com.invadermonky.magicultureintegrations.api.tile.IHeatableTile;
import com.llamalad7.mixinextras.sugar.Local;
import net.blay09.mods.cookingforblockheads.ModConfig;
import net.blay09.mods.cookingforblockheads.api.capability.IKitchenSmeltingProvider;
import net.blay09.mods.cookingforblockheads.tile.TileOven;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraftforge.items.wrapper.RangedWrapper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;
import java.util.IntSummaryStatistics;

@Mixin(value = TileOven.class, remap = false)
public abstract class TileOvenMixin extends TileEntity implements ITickable, IKitchenSmeltingProvider, ExternalHeaterHandler.IExternalHeatable, IHeatableTile {
    private ItemStack fuelStack;
    @Shadow public int furnaceBurnTime;
    @Shadow public int currentItemBurnTime;
    @Shadow public int[] slotCookTime;
    @Shadow protected abstract boolean shouldConsumeFuel();

    @Shadow public abstract void update();

    /**
     * @author Invadermonky
     * @reason Fixing Cooking For Blockheads Oven consuming fuel container items.<br>
     *
     * <p>Cooking for Blockheads Oven pulls items from the {@link RangedWrapper#getStackInSlot(int)} and modifies
     * them directtly, which the javadocs specificallly say not to do.</p>
     *
     * <p>When the oven consumes a fuel item, it first shrinks the ItemStack by 1, then, if the new stack count is
     * 0, attempts to pull the container item via {@link Item#getContainerItem(ItemStack)}. The order of these
     * operations means that if the item count is 1 prior to the shrink, it will always be trying to get the
     * container item from an empty ItemStack.</p>
     *
     * <p>The {@link TileOvenMixin#updateCaptureFuelItem(CallbackInfo, ItemStack)} mixin captures and copies the
     * ItemStack before the shrink.</p>
     */
    @Inject(method = "update", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;shrink(I)V"))
    private void updateCaptureFuelItem(CallbackInfo ci, @Local(ordinal = 0) ItemStack fuelItem) {
        this.fuelStack = fuelItem.copy();
    }

    /**
     * @author Invadermonky
     * @reason Fixing Cooking For Blockheads Oven consuming fuel container items.<br>
     *
     * <p>Cooking for Blockheads Oven pulls items from the {@link RangedWrapper#getStackInSlot(int)} and modifies
     * them directtly, which the javadocs specificallly say not to do.</p>
     *
     * <p>When the oven consumes a fuel item, it first shrinks the ItemStack by 1, then, if the new stack count is
     * 0, attempts to pull the container item via {@link Item#getContainerItem(ItemStack)}. The order of these
     * operations means that if the item count is 1 prior to the shrink, it will always be trying to get the
     * container item from an empty ItemStack.</p>
     *
     * <p>The {@link TileOvenMixin#updateRedirectFuelConsumption(RangedWrapper, int, ItemStack)} redirects the
     * {@link RangedWrapper#setStackInSlot(int, ItemStack)} to use the captured stack copy.</p>
     */
    @Redirect(
            method = "update",
            at = @At(value = "INVOKE", target = "Lnet/minecraftforge/items/wrapper/RangedWrapper;setStackInSlot(ILnet/minecraft/item/ItemStack;)V", ordinal = 0)
    )
    private void updateRedirectFuelConsumption(RangedWrapper instance, int i, ItemStack itemStack) {
        instance.setStackInSlot(i, this.fuelStack.getItem().getContainerItem(this.fuelStack));
    }

    @Override
    public boolean canSmeltHeatable() {
        return this.shouldConsumeFuel();
    }

    @Override
    public int getBurnTimeHeatable() {
        return this.furnaceBurnTime;
    }

    @Override
    public int getBurnTimeMaxHeatable() {
        return this.currentItemBurnTime;
    }

    @Override
    public int getCookTimeHeatable() {
        IntSummaryStatistics stat = Arrays.stream(this.slotCookTime).summaryStatistics();
        return stat.getMax();
    }

    @Override
    public int getCookTimeMaxHeatable() {
        return (int) (200F * ModConfig.general.ovenCookTimeMultiplier);
    }

    @Override
    public void setBurnTimeMaxHeatable(int burnTimeMax) {
        this.currentItemBurnTime = burnTimeMax;
    }

    @Override
    public void boostBurnTimeHeatable(int boostAmount) {
        this.furnaceBurnTime = getBurnTimeHeatable() + (int) (boostAmount * ModConfig.general.ovenFuelTimeMultiplier);
        if(this.getBurnTimeMaxHeatable() < this.furnaceBurnTime) {
            setBurnTimeMaxHeatable(this.furnaceBurnTime);
        }
    }

    @Override
    public void boostCookTimeHeatable(int boostAmount) {
        for(int i = 0; i < this.slotCookTime.length; i++) {
            if(this.slotCookTime[i] != -1)
                this.slotCookTime[i] = Math.min(getCookTimeMaxHeatable(), this.slotCookTime[i] + boostAmount);
        }
    }

    @Override
    public void updateTileHeatable() {
        this.markDirty();
    }
}
