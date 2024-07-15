package com.invadermonky.magicultureintegrations.api.mods.cookingforblockheads;

import com.invadermonky.magicultureintegrations.api.tile.IHeatableTile;
import net.blay09.mods.cookingforblockheads.ModConfig;
import net.blay09.mods.cookingforblockheads.tile.TileOven;

public class CookingForBlockheadsHeatable implements IHeatableTile {
    protected TileOven oven;

    public CookingForBlockheadsHeatable(TileOven oven) {
        this.oven = oven;
    }

    @Override
    public boolean canSmelt() {
        return CookingForBlockheadsUtils.canOvenCook(oven);
    }

    @Override
    public int getBurnTime() {
        return oven.furnaceBurnTime;
    }

    @Override
    public int getBurnTimeMax() {
        return oven.currentItemBurnTime;
    }

    @Override
    public int getCookTime() {
        return 0;
    }

    @Override
    public int getCookTimeMax() {
        return (int) (200 * ModConfig.general.ovenCookTimeMultiplier);
    }

    @Override
    public void setBurnTimeMax(int burnTimeMax) {
        oven.currentItemBurnTime = burnTimeMax;
    }

    @Override
    public void boostBurnTime(int boostAmount) {
        oven.furnaceBurnTime = getBurnTime() + (int) (boostAmount * ModConfig.general.ovenFuelTimeMultiplier);
        setBurnTimeMax(getBurnTime());
    }

    @Override
    public void boostCookTime(int boostAmount) {
        for(int i = 0; i < oven.slotCookTime.length; i++) {
            oven.slotCookTime[i] = Math.min(getCookTimeMax() - 1, oven.slotCookTime[i] + boostAmount);
        }
    }

    @Override
    public void updateTile() {}
}
