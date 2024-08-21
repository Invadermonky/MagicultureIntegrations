package com.invadermonky.magicultureintegrations.api.mods.immersiveengineering;

import blusunrize.immersiveengineering.api.tool.ExternalHeaterHandler;
import com.invadermonky.magicultureintegrations.api.tile.IHeatableTile;

public class ImmersiveEngineeringUtils {
    public static int doHeatableHeatTick(IHeatableTile heatable, int energyAvailable, boolean redstone) {
        int energyConsumed = 0;
        boolean canCook = heatable.canSmelt();
        if(canCook || redstone) {
            int burnTime = heatable.getBurnTime();
            int energyToUse;
            boolean burning = burnTime > 0;
            if(burnTime < 200) {
                energyToUse = 4;
                int heatEnergyRatio = Math.max(1, ExternalHeaterHandler.defaultFurnaceEnergyCost);
                energyToUse = Math.min(energyAvailable, energyToUse * heatEnergyRatio);
                int heat = energyToUse / heatEnergyRatio;
                if(heat > 0) {
                    heatable.boostBurnTime(heat);
                    energyConsumed += heat * heatEnergyRatio;
                    if(!burning) {
                        heatable.updateTile();
                    }
                }
            }

            if(canCook && heatable.getBurnTime() >= 200 && heatable.getCookTime() < (heatable.getCookTimeMax() - 1)) {
                energyToUse = ExternalHeaterHandler.defaultFurnaceSpeedupCost;
                if(energyAvailable - energyConsumed > energyToUse) {
                    energyConsumed += energyToUse;
                    heatable.boostCookTime(1);
                    heatable.updateTile();
                }
            }
        }
        return energyConsumed;
    }
}
