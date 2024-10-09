package com.invadermonky.magicultureintegrations.core.mixins.bloodmagic;

import WayofTime.bloodmagic.alchemyArray.AlchemyArrayEffectFurnaceFuel;
import WayofTime.bloodmagic.util.DamageSourceBloodMagic;
import com.invadermonky.magicultureintegrations.api.mods.bloodmagic.BloodMagicUtils;
import com.invadermonky.magicultureintegrations.api.tile.HeatableUtils;
import com.invadermonky.magicultureintegrations.api.tile.IHeatableTile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = AlchemyArrayEffectFurnaceFuel.class, remap = false)
public abstract class FurnaceArrayMixin {
    @Inject(method = "update", at = @At("TAIL"))
    private void updateMixin(TileEntity tile, int ticksActive, CallbackInfoReturnable<Boolean> cir) {
        World world = tile.getWorld();
        BlockPos arrayPos = tile.getPos();
        EntityPlayer sacrifice = BloodMagicUtils.getSacrificeTarget(world, arrayPos, 10.00);

        for(EnumFacing facing : EnumFacing.VALUES) {
            TileEntity facingTile = world.getTileEntity(arrayPos.offset(facing));

            if(facingTile instanceof IHeatableTile && !HeatableUtils.isHeatableBlacklisted(AlchemyArrayEffectFurnaceFuel.class, facingTile)) {
                IHeatableTile heatable = (IHeatableTile) facingTile;
                if(heatable.canSmeltHeatable() && heatable.getBurnTimeHeatable() <= 1) {
                    if(sacrifice == null || sacrifice.isDead)
                        return;

                    heatable.boostBurnTimeHeatable(401);
                    heatable.updateTileHeatable();

                    if(!sacrifice.isCreative()) {
                        sacrifice.hurtResistantTime = 0;
                        sacrifice.attackEntityFrom(DamageSourceBloodMagic.INSTANCE, 1.0f);
                    }
                }
            }
        }
    }
}
