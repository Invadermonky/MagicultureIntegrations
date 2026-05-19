package com.invadermonky.magicultureintegrations.core.mixins.botania;

import com.invadermonky.magicultureintegrations.api.tile.HeatableUtils;
import com.invadermonky.magicultureintegrations.api.tile.IBoostableTile;
import com.invadermonky.magicultureintegrations.api.tile.IHeatableTile;
import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.tileentity.TileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.botania.common.block.tile.mana.TileBellows;

@Mixin(value = TileBellows.class, remap = false)
public abstract class TileBellowsMixin {
    @Shadow public float movePos;
    @Shadow public float moving;
    @Shadow public abstract void setActive(boolean active);

    @Definition(id = "tile", local = @Local(type = TileEntity.class))
    @Expression("tile instanceof ?")
    @Inject(
            method = "update",
            at = @At(
                    value = "MIXINEXTRAS:EXPRESSION",
                    remap = false
            ),
            remap = true,
            cancellable = true
    )
    private void boostHeatableTileMixin(CallbackInfo ci, @Local(name = "disable") boolean disable, @Local(name = "tile") TileEntity tile, @Local(name = "max") float max, @Local(name = "min") float min, @Local(name = "incr") float incr) {
        boolean did = false;
        if(tile instanceof IHeatableTile && !HeatableUtils.isHeatableBlacklisted(TileBellows.class, tile)) {
            IHeatableTile heatable = (IHeatableTile) tile;
            if(heatable.canSmeltHeatable() && heatable.getBurnTimeHeatable() > 0) {
                heatable.boostCookTimeHeatable(20);
                heatable.boostBurnTimeHeatable(-Math.min(10, heatable.getBurnTimeHeatable()));
                heatable.updateTileHeatable();
                did = true;
            }
        } else if(tile instanceof IBoostableTile && !HeatableUtils.isHeatableBlacklisted(TileBellows.class, tile)) {
            IBoostableTile boostable = (IBoostableTile) tile;
            if(boostable.canSmeltBoostable()) {
                if (!boostable.isTrueBoostable()) {
                    boostable = boostable.getTrueBoostable();
                }
                boostable.boostCookTimeBoostable(20);
                boostable.updateTileBoostable();
                did = true;
            }
        }

        if(did) {
            this.movePos += incr * 3.0F;
            this.moving = incr * 3.0F;
            if (this.movePos >= max) {
                this.movePos = Math.min(max, this.movePos);
                this.moving = 0.0F;
                if (disable) {
                    this.setActive(false);
                }
            }
            ci.cancel();
        }
    }
}
