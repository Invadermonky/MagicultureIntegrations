package com.invadermonky.magicultureintegrations.api.mods.immersiveengineering;

import blusunrize.immersiveengineering.api.tool.ExternalHeaterHandler;
import com.invadermonky.magicultureintegrations.api.tile.IBoostableTile;
import com.invadermonky.magicultureintegrations.api.tile.IHeatableTile;

public class ImmersiveEngineeringUtils {
    public static int doHeatableHeatTick(IHeatableTile heatable, int energyAvailable, boolean redstone) {
        int energyConsumed = 0;
        boolean canCook = heatable.canSmeltHeatable();
        if (canCook || redstone) {
            int burnTime = heatable.getBurnTimeHeatable();
            int energyToUse;
            boolean burning = burnTime > 0;
            if (burnTime < 200) {
                energyToUse = 4;
                int heatEnergyRatio = Math.max(1, ExternalHeaterHandler.defaultFurnaceEnergyCost);
                energyToUse = Math.min(energyAvailable, energyToUse * heatEnergyRatio);
                int heat = energyToUse / heatEnergyRatio;
                if (heat > 0) {
                    heatable.boostBurnTimeHeatable(heat);
                    energyConsumed += heat * heatEnergyRatio;
                    if (!burning) {
                        heatable.updateTileHeatable();
                    }
                }
            }

            if (canCook && heatable.getBurnTimeHeatable() >= 200 && heatable.getCookTimeHeatable() < (heatable.getCookTimeMaxHeatable() - 1)) {
                energyToUse = ExternalHeaterHandler.defaultFurnaceSpeedupCost;
                if (energyAvailable - energyConsumed > energyToUse) {
                    energyConsumed += energyToUse;
                    heatable.boostCookTimeHeatable(1);
                    heatable.updateTileHeatable();
                }
            }
        }
        return energyConsumed;
    }

    public static int doBoostableTick(IBoostableTile boostable, int energyAvailable, boolean redstone) {
        int energyConsumed = 0;
        boolean canCook = boostable.canSmeltBoostable();
        if (canCook || redstone) {
            int energyToUse;
            if (canCook && boostable.getCookTimeBoostable() < (boostable.getCookTimeMaxBoostable() - 1)) {
                energyToUse = ExternalHeaterHandler.defaultFurnaceSpeedupCost;
                if (energyAvailable - energyConsumed > energyToUse) {
                    energyConsumed += energyToUse;
                    boostable.boostCookTimeBoostable(2);
                    boostable.updateTileBoostable();
                }
            }
        }
        return energyConsumed;
    }
}
