package com.invadermonky.magicultureintegrations.core.mixins.botania;

import com.invadermonky.magicultureintegrations.api.tile.HeatableUtils;
import com.invadermonky.magicultureintegrations.api.tile.IBoostableTile;
import com.invadermonky.magicultureintegrations.api.tile.IHeatableTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.botania.api.item.IExoflameHeatable;
import vazkii.botania.api.subtile.SubTileFunctional;
import vazkii.botania.common.block.subtile.functional.SubTileExoflame;

@Mixin(value = SubTileExoflame.class, remap = false)
public abstract class SubTileExoflameMixin extends SubTileFunctional {
    @Inject(method = "onUpdate", at = @At("HEAD"), cancellable = true)
    private void onUpdateMixin(CallbackInfo ci) {
        super.onUpdate();
        if (!this.supertile.getWorld().isRemote) {
            boolean did = false;

            for (BlockPos pos : BlockPos.getAllInBox(this.getPos().add(-5, -2, -5), this.getPos().add(5, 2, 5))) {
                TileEntity tile = this.supertile.getWorld().getTileEntity(pos);
                Block block = this.supertile.getWorld().getBlockState(pos).getBlock();
                if (tile != null) {
                    if (!(tile instanceof TileEntityFurnace) || block != Blocks.FURNACE && block != Blocks.LIT_FURNACE) {
                        if (tile instanceof IExoflameHeatable) {
                            IExoflameHeatable heatable = (IExoflameHeatable) tile;
                            if (heatable.canSmelt() && this.mana > 2) {
                                if (heatable.getBurnTime() < 2) {
                                    heatable.boostBurnTime();
                                    this.mana = Math.max(0, this.mana - 300);
                                }

                                if (this.ticksExisted % 2 == 0) {
                                    heatable.boostCookTime();
                                }

                                if (this.mana <= 0) {
                                    break;
                                }
                            }
                        } else if (tile instanceof IHeatableTile && !HeatableUtils.isHeatableBlacklisted(SubTileExoflame.class, tile)) {
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
                                did = true;
                            }

                            if(this.mana <= 0) {
                                break;
                            }
                        } else if(tile instanceof IBoostableTile && ((IBoostableTile) tile).isTrueBoostable() && !HeatableUtils.isHeatableBlacklisted(SubTileExoflame.class, tile)) {
                            IBoostableTile boostable = (IBoostableTile) tile;
                            if(boostable.canSmeltBoostable() && this.mana > 2) {
                                if(this.ticksExisted % 2 == 0) {
                                    boostable.boostCookTimeBoostable(1);
                                }
                                this.mana = Math.max(0, this.mana - 2);
                                did = true;
                            }
                            if(did) {
                                boostable.updateTileBoostable();
                            }

                            if(this.mana <= 0) {
                                break;
                            }
                        }
                    } else {
                        TileEntityFurnace furnace = (TileEntityFurnace) tile;
                        boolean canSmelt = SubTileExoflame.canFurnaceSmelt(furnace);
                        if (canSmelt && this.mana > 2) {
                            if (furnace.getField(0) < 2) {
                                if (furnace.getField(0) == 0) {
                                    BlockFurnace.setState(true, this.supertile.getWorld(), pos);
                                }

                                furnace.setField(0, 200);
                                this.mana = Math.max(0, this.mana - 300);
                            }

                            if (this.ticksExisted % 2 == 0) {
                                furnace.setField(2, Math.min(199, furnace.getField(2) + 1));
                            }

                            did = true;
                            if (this.mana <= 0) {
                                break;
                            }
                        }
                    }
                }
            }

            if (did) {
                this.sync();
            }
        }
        ci.cancel();
    }
}
