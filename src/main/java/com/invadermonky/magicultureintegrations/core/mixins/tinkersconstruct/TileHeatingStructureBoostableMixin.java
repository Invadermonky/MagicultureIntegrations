package com.invadermonky.magicultureintegrations.core.mixins.tinkersconstruct;

import com.invadermonky.magicultureintegrations.api.tile.IBoostableTile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import slimeknights.tconstruct.smeltery.multiblock.MultiblockDetection;
import slimeknights.tconstruct.smeltery.tileentity.TileHeatingStructure;
import slimeknights.tconstruct.smeltery.tileentity.TileMultiblock;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.IntSummaryStatistics;

@Mixin(value = TileHeatingStructure.class, remap = false)
public abstract class TileHeatingStructureBoostableMixin<T extends MultiblockDetection> extends TileMultiblock<T> implements IBoostableTile {
    @Shadow protected int[] itemTemperatures;
    @Shadow protected int[] itemTempRequired;
    @Shadow public abstract boolean hasFuel();

    public TileHeatingStructureBoostableMixin(String name, int inventorySize) {
        super(name, inventorySize);
    }

    @Override
    public boolean isTrueBoostable() {
        return true;
    }

    @Nullable
    @Override
    public IBoostableTile getTrueBoostable() {
        return this;
    }

    @Override
    public boolean canSmeltBoostable() {
        return getCookTimeMaxBoostable() > 0 && this.hasFuel();
    }

    @Override
    public int getCookTimeBoostable() {
        IntSummaryStatistics stats = Arrays.stream(this.itemTemperatures).summaryStatistics();
        return stats.getMax();
    }

    @Override
    public int getCookTimeMaxBoostable() {
        IntSummaryStatistics stats = Arrays.stream(this.itemTempRequired).summaryStatistics();
        return stats.getMax();
    }

    @Override
    public void boostCookTimeBoostable(int boostAmount) {
        for(int i = 0; i < this.itemTempRequired.length; i++) {
            this.itemTemperatures[i] = Math.min(this.itemTempRequired[i], this.itemTemperatures[i] + boostAmount);
        }
    }

    @Override
    public void updateTileBoostable() {

    }
}
