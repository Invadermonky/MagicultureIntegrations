package com.invadermonky.magicultureintegrations.api.mods.rustic;

import com.invadermonky.magicultureintegrations.api.tile.IHeatableTile;
import rustic.common.tileentity.TileEntityCondenserBase;

public class RusticHeatable implements IHeatableTile {
    protected TileEntityCondenserBase condenser;

    public RusticHeatable(TileEntityCondenserBase condenser) {
        this.condenser = condenser;
    }

    @Override
    public boolean canSmelt() {
        return RusticUtils.canBrew(condenser);
    }

    @Override
    public int getBurnTime() {
        return condenser.condenserBurnTime;
    }

    @Override
    public int getBurnTimeMax() {
        return condenser.currentItemBurnTime;
    }

    @Override
    public int getCookTime() {
        return condenser.brewTime;
    }

    @Override
    public int getCookTimeMax() {
        return condenser.totalBrewTime;
    }

    @Override
    public void setBurnTimeMax(int burnTimeMax) {
        condenser.currentItemBurnTime = burnTimeMax;
    }

    @Override
    public void boostBurnTime(int boostAmount) {
        condenser.condenserBurnTime += boostAmount;
    }

    @Override
    public void boostCookTime(int boostAmount) {
        condenser.brewTime = Math.min(getCookTimeMax() - 1, getCookTime() + boostAmount);
    }

    @Override
    public void updateTile() {
        RusticUtils.updateCondenser(condenser);
    }
}
