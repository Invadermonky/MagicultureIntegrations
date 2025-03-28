package com.invadermonky.magicultureintegrations.core.mixins.cookingforblockheads;

import com.invadermonky.magicultureintegrations.api.tile.IHeatableTile;
import net.blay09.mods.cookingforblockheads.ModConfig;
import net.blay09.mods.cookingforblockheads.api.capability.IKitchenSmeltingProvider;
import net.blay09.mods.cookingforblockheads.tile.TileOven;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Arrays;
import java.util.IntSummaryStatistics;

@Mixin(value = TileOven.class, remap = false)
public abstract class TileOvenHeatableMixin extends TileEntity implements ITickable, IKitchenSmeltingProvider, IHeatableTile {
    @Shadow public int furnaceBurnTime;
    @Shadow public int currentItemBurnTime;
    @Shadow public int[] slotCookTime;
    @Shadow protected abstract boolean shouldConsumeFuel();

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
        if(this.getBurnTimeMaxHeatable() < this.furnaceBurnTime) {
            setBurnTimeMaxHeatable(this.furnaceBurnTime);
        }
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
