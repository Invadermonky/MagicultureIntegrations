package com.invadermonky.magicultureintegrations.api.mods.bewitchment;

import com.bewitchment.common.block.tile.entity.TileEntityWitchesOven;
import com.invadermonky.magicultureintegrations.api.tile.IHeatableTile;

public class BewitchmentHeatable implements IHeatableTile {
    private TileEntityWitchesOven oven;

    public BewitchmentHeatable(TileEntityWitchesOven oven) {
        this.oven = oven;
    }

    @Override
    public boolean canSmelt() {
        return BewitchmentUtils.canOvenSmelt(oven);
    }

    @Override
    public int getBurnTime() {
        return oven.getBurnTime();
    }

    @Override
    public int getBurnTimeMax() {
        return oven.fuelBurnTime;
    }

    @Override
    public int getCookTime() {
        return oven.progress;
    }

    @Override
    public int getCookTimeMax() {
        return 200;
    }

    @Override
    public void setBurnTimeMax(int burnTimeMax) {
        oven.fuelBurnTime = burnTimeMax;
    }

    @Override
    public void boostBurnTime(int boostAmount) {
        oven.burnTime += boostAmount;
        setBurnTimeMax(getBurnTime());
    }

    @Override
    public void boostCookTime(int boostAmount) {
        oven.progress = Math.min(getCookTimeMax() - 1, getCookTime() + boostAmount);
    }

    @Override
    public void updateTile() {
        BewitchmentUtils.updateOven(oven, getBurnTime() > 0);
    }
}
