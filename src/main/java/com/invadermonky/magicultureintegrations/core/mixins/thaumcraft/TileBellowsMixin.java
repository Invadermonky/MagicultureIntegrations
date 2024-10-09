package com.invadermonky.magicultureintegrations.core.mixins.thaumcraft;

import com.invadermonky.magicultureintegrations.api.tile.HeatableUtils;
import com.invadermonky.magicultureintegrations.api.tile.IBoostableTile;
import com.invadermonky.magicultureintegrations.api.tile.IHeatableTile;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import thaumcraft.common.lib.utils.BlockStateUtils;
import thaumcraft.common.tiles.TileThaumcraft;
import thaumcraft.common.tiles.devices.TileBellows;

@Mixin(value = TileBellows.class, remap = false)
public abstract class TileBellowsMixin extends TileThaumcraft implements ITickable {

    @Inject(method = "update", at = @At("TAIL"))
    private void updateMixin(CallbackInfo ci) {
        if(!this.world.isRemote && BlockStateUtils.isEnabled(this.getBlockMetadata())) {
            TileEntity tile = world.getTileEntity(this.pos.offset(BlockStateUtils.getFacing(this.getBlockMetadata())));

            if(tile instanceof IHeatableTile && !HeatableUtils.isHeatableBlacklisted(TileBellows.class, tile)) {
                IHeatableTile heatable = (IHeatableTile) tile;
                if(heatable.getBurnTimeHeatable() > 0 && heatable.getCookTimeHeatable() > 0) {
                    heatable.boostCookTimeHeatable(1);
                }
            } else if(tile instanceof IBoostableTile && !HeatableUtils.isHeatableBlacklisted(TileBellows.class, tile)) {
                IBoostableTile boostable = ((IBoostableTile) tile).getTrueBoostable();
                if(boostable.getCookTimeBoostable() > 0) {
                    boostable.boostCookTimeBoostable(1);
                }
            }
        }
    }
}
