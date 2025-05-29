package com.invadermonky.magicultureintegrations.core.mixins.rats;

import com.github.alexthe666.rats.server.entity.EntityRat;
import com.github.alexthe666.rats.server.entity.ai.RatAIHarvestCrops;
import com.invadermonky.magicultureintegrations.api.block.IHarvestableCrop;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.Constants;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(value = RatAIHarvestCrops.class, remap = false)
public abstract class RatAIHarvstCropsMixin extends EntityAIBase {
    @Shadow
    @Final
    private EntityRat entity;
    @Shadow
    private BlockPos targetBlock;

    @Shadow
    public abstract void resetTask();

    @Inject(
            method = "resetTarget",
            at = @At(
                    value = "INVOKE_ASSIGN",
                    target = "Lnet/minecraft/block/state/IBlockState;getBlock()Lnet/minecraft/block/Block;",
                    remap = true
            ),
            cancellable = true
    )
    private void resetTargetMixin(CallbackInfo ci,
                                  @Local(name = "allBlocks") List<BlockPos> allBlocks,
                                  @Local(ordinal = 0) BlockPos blockPos,
                                  @Local(name = "block") IBlockState state) {
        if (state.getBlock() instanceof IHarvestableCrop) {
            IHarvestableCrop.HarvestResult result = ((IHarvestableCrop) state.getBlock()).getHarvestResult(this.entity.world, blockPos);
            if (result == IHarvestableCrop.HarvestResult.HARVEST) {
                this.targetBlock = blockPos;
                ci.cancel();
            }
        }
    }

    @Inject(
            method = "updateTask",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/pathfinding/PathNavigate;tryMoveToXYZ(DDDD)Z",
                    shift = At.Shift.AFTER,
                    remap = true
            ),
            cancellable = true
    )
    private void updateTaskMixin(CallbackInfo ci, @Local(name = "block") IBlockState state) {
        if (state.getBlock() instanceof IHarvestableCrop) {
            IHarvestableCrop crop = (IHarvestableCrop) state.getBlock();
            IHarvestableCrop.HarvestResult result = crop.getHarvestResult(this.entity.world, this.targetBlock);
            if (result == IHarvestableCrop.HarvestResult.CLAIM) {
                this.targetBlock = null;
                this.resetTask();
                ci.cancel();
            } else if (result == IHarvestableCrop.HarvestResult.HARVEST) {
                double distance = this.entity.getDistance(this.targetBlock.getX(), this.targetBlock.getY(), this.targetBlock.getZ());
                if (distance < (double) 1.5f) {
                    NonNullList<ItemStack> drops = crop.harvestCrop(null, this.entity.world, this.targetBlock, false, 0);
                    this.entity.world.playEvent(Constants.WorldEvents.BREAK_BLOCK_EFFECTS, this.targetBlock, Block.getStateId(state));

                    if (!drops.isEmpty() && this.entity.canRatPickupItem(drops.get(0))) {
                        ItemStack copy = drops.get(0).copy();
                        drops.remove(0);
                        if (!this.entity.getHeldItem(EnumHand.MAIN_HAND).isEmpty() && !this.entity.world.isRemote) {
                            this.entity.entityDropItem(this.entity.getHeldItem(EnumHand.MAIN_HAND), 0.0F);
                        }
                        this.entity.setHeldItem(EnumHand.MAIN_HAND, copy);
                        for (ItemStack drop : drops) {
                            this.entity.entityDropItem(drop, 0.0F);
                        }
                        this.entity.fleePos = this.targetBlock;
                    }
                    this.targetBlock = null;
                    this.resetTask();
                    ci.cancel();
                }
            }
        }
    }
}
