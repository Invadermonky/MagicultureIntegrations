package com.invadermonky.magicultureintegrations.core.mixins.botania;

import com.invadermonky.magicultureintegrations.api.tile.HeatableUtils;
import com.invadermonky.magicultureintegrations.api.tile.IBoostableTile;
import com.invadermonky.magicultureintegrations.api.tile.IHeatableTile;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalBooleanRef;
import net.minecraft.tileentity.TileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.botania.api.item.IExoflameHeatable;
import vazkii.botania.api.subtile.SubTileFunctional;
import vazkii.botania.common.block.subtile.functional.SubTileExoflame;

@Mixin(value = SubTileExoflame.class, remap = false)
public abstract class SubTileExoflameMixin extends SubTileFunctional {
    @Inject(
            method = "onUpdate",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/state/IBlockState;getBlock()Lnet/minecraft/block/Block;",
                    remap = true
            ),
            cancellable = true
    )
    private void onUpdateMixinFixed(CallbackInfo ci, @Local(ordinal = 0) LocalBooleanRef localRef, @Local TileEntity tile) {
        if (tile == null || tile instanceof IExoflameHeatable)
            return;

        if (tile instanceof IHeatableTile && !HeatableUtils.isHeatableBlacklisted(SubTileExoflame.class, tile)) {
            IHeatableTile heatable = (IHeatableTile) tile;
            if (heatable.canSmeltHeatable() && this.mana > 2) {
                if (heatable.getBurnTimeHeatable() <= 2) {
                    heatable.boostBurnTimeHeatable(200);
                    this.mana = Math.max(0, this.mana - 300);
                }

                if (this.ticksExisted % 2 == 0) {
                    heatable.boostCookTimeHeatable(1);
                }
                heatable.updateTileHeatable();
                localRef.set(true);
            }

            if (this.mana <= 0) {
                if (localRef.get())
                    this.sync();
                ci.cancel();
            }
        } else if (tile instanceof IBoostableTile && !HeatableUtils.isHeatableBlacklisted(SubTileExoflame.class, tile)) {
            IBoostableTile boostable = (IBoostableTile) tile;
            if (boostable.canSmeltBoostable() && this.mana > 2) {
                if (this.ticksExisted % 2 == 0) {
                    boostable.boostCookTimeBoostable(1);
                }
                this.mana = Math.max(0, this.mana - 2);
                boostable.updateTileBoostable();
                localRef.set(true);
            }

            if (this.mana <= 0) {
                if (localRef.get())
                    this.sync();
                ci.cancel();
            }
        }
    }
}
