package com.invadermonky.magicultureintegrations.api.mods.bewitchment;

import com.bewitchment.common.block.BlockWitchesOven;
import com.bewitchment.common.block.tile.entity.TileEntityWitchesOven;
import com.invadermonky.magicultureintegrations.api.tile.IHeatableTile;
import com.invadermonky.magicultureintegrations.util.ReflectionHelper;

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
        oven.burnTime = getBurnTime() + boostAmount;
        oven.fuelBurnTime = oven.burnTime;
    }

    @Override
    public void boostCookTime(int boostAmount) {
        oven.progress = Math.min(getCookTimeMax() - 1, getCookTime() + boostAmount);
    }

    @Override
    public void updateTile() {
        try {
            boolean isBurning = (boolean) ReflectionHelper.getFieldObject(oven, "burning");
            if (!isBurning) {
                ReflectionHelper.setField(oven, "burning", oven.getWorld().setBlockState(oven.getPos(), oven.getWorld().getBlockState(oven.getPos()).withProperty(BlockWitchesOven.LIT, true)));
            }
        } catch (Exception ignored) {}
    }
}
