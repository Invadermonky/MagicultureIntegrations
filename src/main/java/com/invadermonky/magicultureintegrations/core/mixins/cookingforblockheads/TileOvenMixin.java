package com.invadermonky.magicultureintegrations.core.mixins.cookingforblockheads;

import blusunrize.immersiveengineering.api.tool.ExternalHeaterHandler;
import com.invadermonky.magicultureintegrations.api.tile.IHeatableTile;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import net.blay09.mods.cookingforblockheads.ModConfig;
import net.blay09.mods.cookingforblockheads.api.capability.IKitchenSmeltingProvider;
import net.blay09.mods.cookingforblockheads.block.BlockOven;
import net.blay09.mods.cookingforblockheads.block.ModBlocks;
import net.blay09.mods.cookingforblockheads.network.VanillaPacketHandler;
import net.blay09.mods.cookingforblockheads.tile.DoorAnimator;
import net.blay09.mods.cookingforblockheads.tile.EnergyStorageModifiable;
import net.blay09.mods.cookingforblockheads.tile.TileOven;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.wrapper.RangedWrapper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;
import java.util.IntSummaryStatistics;

@Mixin(value = TileOven.class, remap = false)
public abstract class TileOvenMixin extends TileEntity implements ITickable, IKitchenSmeltingProvider, ExternalHeaterHandler.IExternalHeatable, IHeatableTile {
    @Shadow public int furnaceBurnTime;
    @Shadow public int currentItemBurnTime;
    @Shadow public int[] slotCookTime;
    @Shadow private boolean hasPowerUpgrade;
    @Shadow private boolean isDirty;
    @Shadow private boolean isFirstTick;
    @Shadow @Final private DoorAnimator doorAnimator;
    @Shadow private EnergyStorageModifiable energyStorage;
    @Shadow private EnumFacing facing;
    @Shadow @Final private RangedWrapper itemHandlerProcessing;
    @Shadow @Final private RangedWrapper itemHandlerInput;
    @Shadow @Final private RangedWrapper itemHandlerOutput;
    @Shadow @Final private RangedWrapper itemHandlerFuel;
    @Shadow protected abstract boolean shouldConsumeFuel();

    @Inject(method = "update", at = @At("HEAD"), cancellable = true)
    private void updateMixin(CallbackInfo ci) {
        if(!ConfigHandlerMI.fixes.fixCfbOven)
            return;

        if (this.isFirstTick) {
            IBlockState state = this.world.getBlockState(this.pos);
            if (state.getBlock() == ModBlocks.oven) {
                this.facing = state.getValue(BlockOven.FACING);
                this.isFirstTick = false;
            }
        }

        this.doorAnimator.update();
        if (this.isDirty) {
            VanillaPacketHandler.sendTileEntityUpdate(this);
            this.isDirty = false;
        }

        boolean hasChanged = false;
        int burnPotential = 200 - this.furnaceBurnTime;
        if (this.hasPowerUpgrade && burnPotential > 0 && this.shouldConsumeFuel()) {
            this.furnaceBurnTime += this.energyStorage.extractEnergy(burnPotential, false);
        }

        if (this.furnaceBurnTime > 0) {
            --this.furnaceBurnTime;
        }

        if (!this.world.isRemote) {
            int firstEmptySlot;
            if (this.furnaceBurnTime == 0 && this.shouldConsumeFuel()) {
                for(firstEmptySlot = 0; firstEmptySlot < this.itemHandlerFuel.getSlots(); ++firstEmptySlot) {
                    ItemStack fuelItem = this.itemHandlerFuel.getStackInSlot(firstEmptySlot);
                    if (!fuelItem.isEmpty()) {
                        this.currentItemBurnTime = this.furnaceBurnTime = (int)Math.max(1.0F, (float)TileOven.getItemBurnTime(fuelItem) * ModConfig.general.ovenFuelTimeMultiplier);
                        if (this.furnaceBurnTime != 0) {
                            Item item = fuelItem.getItem();
                            fuelItem.shrink(1);
                            if (fuelItem.isEmpty()) {
                                this.itemHandlerFuel.setStackInSlot(firstEmptySlot, item.getContainerItem(fuelItem));
                            }

                            hasChanged = true;
                        }
                        break;
                    }
                }
            }

            firstEmptySlot = -1;
            int firstTransferSlot = -1;

            int i;
            ItemStack itemStack;
            for(i = 0; i < this.itemHandlerProcessing.getSlots(); ++i) {
                itemStack = this.itemHandlerProcessing.getStackInSlot(i);
                if (!itemStack.isEmpty()) {
                    if (this.slotCookTime[i] != -1) {
                        if (this.furnaceBurnTime > 0) {
                            this.slotCookTime[i]++;
                        }

                        if ((float)this.slotCookTime[i] >= 200.0F * ModConfig.general.ovenCookTimeMultiplier) {
                            ItemStack resultStack = TileOven.getSmeltingResult(itemStack);
                            if (!resultStack.isEmpty()) {
                                this.itemHandlerProcessing.setStackInSlot(i, resultStack.copy());
                                this.slotCookTime[i] = -1;
                                if (firstTransferSlot == -1) {
                                    firstTransferSlot = i;
                                }
                            }
                        }
                    } else if (firstTransferSlot == -1) {
                        firstTransferSlot = i;
                    }
                } else if (firstEmptySlot == -1) {
                    firstEmptySlot = i;
                }
            }

            if (firstTransferSlot != -1) {
                ItemStack transferStack = this.itemHandlerProcessing.getStackInSlot(firstTransferSlot);
                transferStack = ItemHandlerHelper.insertItemStacked(this.itemHandlerOutput, transferStack, false);
                this.itemHandlerProcessing.setStackInSlot(firstTransferSlot, transferStack);
                if (transferStack.isEmpty()) {
                    this.slotCookTime[firstTransferSlot] = 0;
                }

                hasChanged = true;
            }

            if (firstEmptySlot != -1) {
                for(i = 0; i < this.itemHandlerInput.getSlots(); ++i) {
                    itemStack = this.itemHandlerInput.getStackInSlot(i);
                    if (!itemStack.isEmpty()) {
                        this.itemHandlerProcessing.setStackInSlot(firstEmptySlot, itemStack.splitStack(1));
                        if (itemStack.getCount() <= 0) {
                            this.itemHandlerInput.setStackInSlot(i, ItemStack.EMPTY);
                        }
                        break;
                    }
                }
            }
        }

        if (hasChanged) {
            this.markDirty();
        }
        ci.cancel();
    }

    @Override
    public boolean canSmeltHeatable() {
        return this.shouldConsumeFuel();
    }

    @Override
    public int getBurnTimeHeatable() {
        return this.furnaceBurnTime;
    }

    @Override
    public int getBurnTimeMaxHeatable() {
        return this.currentItemBurnTime;
    }

    @Override
    public int getCookTimeHeatable() {
        IntSummaryStatistics stat = Arrays.stream(this.slotCookTime).summaryStatistics();
        return stat.getMax();
    }

    @Override
    public int getCookTimeMaxHeatable() {
        return (int) (200F * ModConfig.general.ovenCookTimeMultiplier);
    }

    @Override
    public void setBurnTimeMaxHeatable(int burnTimeMax) {
        this.currentItemBurnTime = burnTimeMax;
    }

    @Override
    public void boostBurnTimeHeatable(int boostAmount) {
        this.furnaceBurnTime = getBurnTimeHeatable() + (int) (boostAmount * ModConfig.general.ovenFuelTimeMultiplier);
        setBurnTimeMaxHeatable(this.furnaceBurnTime);
    }

    @Override
    public void boostCookTimeHeatable(int boostAmount) {
        for(int i = 0; i < this.slotCookTime.length; i++) {
            if(this.slotCookTime[i] != -1)
                this.slotCookTime[i] = Math.min(getCookTimeMaxHeatable(), this.slotCookTime[i] + boostAmount);
        }
    }

    @Override
    public void updateTileHeatable() {
        this.markDirty();
    }
}
