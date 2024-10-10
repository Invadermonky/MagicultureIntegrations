package com.invadermonky.magicultureintegrations.core.mixins.botania;

import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCake;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import twilightforest.block.BlockTFExperiment115;
import twilightforest.block.TFBlocks;
import vazkii.botania.api.subtile.SubTileGenerating;
import vazkii.botania.common.block.subtile.generating.SubTileKekimurus;

@Mixin(value = SubTileKekimurus.class, remap = false)
public class SubTileKekimurusMixin extends SubTileGenerating {
    @Shadow @Final private static int RANGE;

    //TODO: Change this mixin so it no longer overwrites the entire function. Had some problems when loading the old version
    //  in obfuscated environments.

    @Inject(method = "onUpdate", at = @At("HEAD"), cancellable = true)
    private void onUpdateMixin(CallbackInfo ci) {
        super.onUpdate();
        if (!this.supertile.getWorld().isRemote) {
            int mana = 1800;
            if (this.getMaxMana() - this.mana >= mana && !this.supertile.getWorld().isRemote && this.ticksExisted % 80 == 0) {
                for(int i = 0; i < 11; ++i) {
                    for(int j = 0; j < 11; ++j) {
                        for(int k = 0; k < 11; ++k) {
                            BlockPos pos = this.supertile.getPos().add(i - RANGE, j - RANGE, k - RANGE);
                            IBlockState state = this.supertile.getWorld().getBlockState(pos);
                            Block block = state.getBlock();
                            if (block instanceof BlockCake) {
                                int nextSlicesEaten = state.getValue(BlockCake.BITES) + 1;
                                if (nextSlicesEaten > 6) {
                                    this.supertile.getWorld().setBlockToAir(pos);
                                } else {
                                    this.supertile.getWorld().setBlockState(pos, state.withProperty(BlockCake.BITES, nextSlicesEaten), 3);
                                }

                                this.supertile.getWorld().playEvent(2001, pos, Block.getStateId(state));
                                this.supertile.getWorld().playSound(null, this.supertile.getPos(), SoundEvents.ENTITY_GENERIC_EAT, SoundCategory.BLOCKS, 1.0F, 0.5F + (float)Math.random() * 0.5F);
                                this.mana += mana;
                                this.sync();
                                return;
                            } else if(block == TFBlocks.experiment_115 && ConfigHandlerMI.integrations.botania.kekimurus.experiment115) {
                                int nextSlice = state.getValue(BlockTFExperiment115.NOMS) + 1;
                                boolean eaten = false;
                                if(nextSlice <= 7) {
                                    this.supertile.getWorld().setBlockState(pos, state.withProperty(BlockTFExperiment115.NOMS, nextSlice), 3);
                                    eaten = true;
                                } else if(ConfigHandlerMI.integrations.botania.kekimurus.experiment115Consume) {
                                    this.supertile.getWorld().setBlockToAir(pos);
                                    eaten = true;
                                }
                                if(eaten) {
                                    this.supertile.getWorld().playEvent(2001, pos, Block.getStateId(state));
                                    this.supertile.getWorld().playSound(null, this.supertile.getPos(), SoundEvents.ENTITY_GENERIC_EAT, SoundCategory.BLOCKS, 1.0f, 0.5f + (float) Math.random() * 0.5f);
                                    this.mana += mana;
                                    this.sync();
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
        ci.cancel();
    }

    /*
    @Inject(
            method = "onUpdate",
            at = @At(value = "INVOKE", shift = At.Shift.BEFORE, target = "Lnet/minecraft/world/World;getBlockState(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;", remap = true)
    )
    private void eatExperiment115Mixin(CallbackInfo ci, @Local BlockPos pos) {
        if(!ModIds.twilight_forest.isLoaded || !ConfigHandlerMI.integrations.botania.kekimurus.experiment115)
            return;

        IBlockState state = this.supertile.getWorld().getBlockState(pos);
        Block block = state.getBlock();

        LogHelper.info("Debug, searching: " + pos);

        if(block == TFBlocks.experiment_115) {
            int nextSlice = state.getValue(BlockTFExperiment115.NOMS) + 1;
            int manaToAdd = 1800;
            boolean eaten = false;
            if(nextSlice <= 7) {
                this.supertile.getWorld().setBlockState(pos, state.withProperty(BlockTFExperiment115.NOMS, nextSlice), 3);
                eaten = true;
            } else if(ConfigHandlerMI.integrations.botania.kekimurus.experiment115Consume) {
                this.supertile.getWorld().setBlockToAir(pos);
                eaten = true;
            }
            if(eaten) {
                this.supertile.getWorld().playEvent(2001, pos, Block.getStateId(state));
                this.supertile.getWorld().playSound(null, this.supertile.getPos(), SoundEvents.ENTITY_GENERIC_EAT, SoundCategory.BLOCKS, 1.0f, 0.5f + (float) Math.random() * 0.5f);
                this.mana += manaToAdd;
                this.sync();
            }
        }
    }
     */
}
