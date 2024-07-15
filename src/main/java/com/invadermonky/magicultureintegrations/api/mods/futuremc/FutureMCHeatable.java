package com.invadermonky.magicultureintegrations.api.mods.futuremc;

import com.invadermonky.magicultureintegrations.api.tile.IHeatableTile;
import thedarkcolour.futuremc.tile.TileFurnaceAdvanced;

public class FutureMCHeatable implements IHeatableTile {
    protected TileFurnaceAdvanced furnace;

    public <T extends TileFurnaceAdvanced> FutureMCHeatable(T furnace) {
        this.furnace = furnace;
    }

    @Override
    public boolean canSmelt() {
        return FutureMCUtils.canFurnaceSmelt(furnace);
    }

    @Override
    public int getBurnTime() {
        return furnace.getField(0);
    }

    @Override
    public int getBurnTimeMax() {
        return furnace.getField(1);
    }

    @Override
    public int getCookTime() {
        return furnace.getField(2);
    }

    @Override
    public int getCookTimeMax() {
        return 100;
    }

    @Override
    public void setBurnTimeMax(int burnTimeMax) {
        furnace.setField(1, burnTimeMax);
    }

    @Override
    public void boostBurnTime(int boostAmount) {
        furnace.setField(0, getBurnTime() + boostAmount);
        setBurnTimeMax(getBurnTime());
    }

    @Override
    public void boostCookTime(int boostAmount) {
        furnace.setField(2, Math.min(getCookTimeMax() - 1, getCookTime() + boostAmount));
    }

    @Override
    public void updateTile() {
        TileFurnaceAdvanced.setState(getBurnTime() > 0, furnace.getWorld(), furnace.getPos());
    }
}
