package com.invadermonky.magicultureintegrations.core.mixins.cookingforblockheads;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import net.blay09.mods.cookingforblockheads.api.capability.IKitchenSmeltingProvider;
import net.blay09.mods.cookingforblockheads.tile.TileOven;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraftforge.items.wrapper.RangedWrapper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = TileOven.class, remap = false)
public abstract class TileOvenFixMixin extends TileEntity implements ITickable, IKitchenSmeltingProvider {
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
     * <p>The {@link TileOvenFixMixin#updateCaptureFuelItem(CallbackInfo, ItemStack)} mixin captures and copies the
     * ItemStack before the shrink.</p>
     */
    @Inject(method = "update", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;shrink(I)V"))
    private void updateCaptureFuelItem(CallbackInfo ci, @Local(ordinal = 0) ItemStack fuelItem, @Share("fuelStackCopy") LocalRef<ItemStack> localRef) {
        localRef.set(fuelItem.copy());
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
     * <p>The {@link TileOvenFixMixin#updateRedirectFuelConsumption(RangedWrapper, int, ItemStack)} redirects the
     * {@link RangedWrapper#setStackInSlot(int, ItemStack)} to use the captured stack copy.</p>
     */
    @Redirect(
            method = "update",
            at = @At(value = "INVOKE", target = "Lnet/minecraftforge/items/wrapper/RangedWrapper;setStackInSlot(ILnet/minecraft/item/ItemStack;)V", ordinal = 0)
    )
    private void updateRedirectFuelConsumption(RangedWrapper instance, int i, ItemStack itemStack, @Share("fuelStackCopy") LocalRef<ItemStack> localRef) {
        ItemStack copy = localRef.get();
        if(copy.getItem().hasContainerItem(copy)) {
            instance.setStackInSlot(i, copy.getItem().getContainerItem(copy));
        }
    }
}
