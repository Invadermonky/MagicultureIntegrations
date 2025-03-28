package com.invadermonky.magicultureintegrations.core.mixins.botania;

import com.invadermonky.magicultureintegrations.api.block.IKekimurusConsumable;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.botania.api.subtile.SubTileGenerating;
import vazkii.botania.common.block.subtile.generating.SubTileKekimurus;

@Mixin(value = SubTileKekimurus.class, remap = false)
public class SubTileKekimurusMixin extends SubTileGenerating {
    @Inject(
            method = "onUpdate",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/state/IBlockState;getBlock()Lnet/minecraft/block/Block;",
                    remap = true
            ),
            cancellable = true
    )
    private void eatExperiment115Mixin(CallbackInfo ci, @Local BlockPos pos, @Local IBlockState state) {
        if(state.getBlock() instanceof IKekimurusConsumable) {
            World world = this.supertile.getWorld();
            IKekimurusConsumable consumable = (IKekimurusConsumable) state.getBlock();
            if(consumable.canBeConsumed(world, pos, state)) {
                int mana = consumable.getManaGain(world, pos, state);
                consumable.consumeBlock(world, pos, state);
                world.playEvent(Constants.WorldEvents.BREAK_BLOCK_EFFECTS, pos, Block.getStateId(state));
                world.playSound(null, this.supertile.getPos(), SoundEvents.ENTITY_GENERIC_EAT, SoundCategory.BLOCKS, 1.0F, 0.5F + (float)Math.random() * 0.5F);
                this.mana += mana;
                this.sync();
                ci.cancel();
            }
        }
    }
}
