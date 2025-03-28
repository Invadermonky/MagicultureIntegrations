package com.invadermonky.magicultureintegrations.core.mixins.bewitchment;

import com.bewitchment.common.block.tile.entity.TileEntityWitchesOven;
import com.bewitchment.common.block.tile.entity.util.ModTileEntity;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = TileEntityWitchesOven.class, remap = false)
public abstract class TileEntityWitchesOvenFixMixin extends ModTileEntity {
    @Shadow @Final private ItemStackHandler inventory_up;

    @Inject(
            method = "burnFuel",
            at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraftforge/items/ItemStackHandler;extractItem(IIZ)Lnet/minecraft/item/ItemStack;")
    )
    private void burnFuelMixin(int time, boolean consume, CallbackInfo ci, @Local(name = "stack") ItemStack stack) {
        if(stack.getItem() != Items.LAVA_BUCKET && stack.getItem().hasContainerItem(stack)) {
            this.inventory_up.insertItem(0, stack.getItem().getContainerItem(stack), false);
        }
    }
}
