package com.invadermonky.magicultureintegrations.api.mods.mysticalagriculture;

import com.blakebr0.mysticalagriculture.tileentity.furnace.TileEssenceFurnace;
import com.invadermonky.magicultureintegrations.api.tile.IHeatableTile;

public class MysticalAgricultureHeatable implements IHeatableTile {
    protected TileEssenceFurnace furnace;

    public MysticalAgricultureHeatable(TileEssenceFurnace furnace) {
        this.furnace = furnace;
    }
    @Override
    public boolean canSmelt() {
        return MysticalAgricultureUtils.canSmelt(furnace);
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
        return furnace.getField(3);
    }

    @Override
    public void setBurnTimeMax(int burnTimeMax) {
        furnace.setField(1, burnTimeMax);
    }

    @Override
    public void boostBurnTime(int boostAmount) {
        furnace.setField(0, furnace.getField(0) + boostAmount);
    }

    @Override
    public void boostCookTime(int boostAmount) {
        furnace.setField(2, Math.min(getCookTimeMax() - 1, getCookTime() + boostAmount));
    }

    @Override
    public void updateTile() {

    }
}
