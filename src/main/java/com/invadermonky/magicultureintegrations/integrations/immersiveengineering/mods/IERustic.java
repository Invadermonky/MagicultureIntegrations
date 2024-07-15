package com.invadermonky.magicultureintegrations.integrations.immersiveengineering.mods;

import blusunrize.immersiveengineering.api.tool.ExternalHeaterHandler;
import com.invadermonky.magicultureintegrations.api.mods.immersiveengineering.IIEIntegration;
import com.invadermonky.magicultureintegrations.api.mods.rustic.RusticUtils;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import rustic.common.tileentity.TileEntityCondenserBase;

public class IERustic implements IIEIntegration {
    @Override
    public void registerExternalHeaterHandler() {
        if(ConfigHandlerMI.immersive_engineering.externalHeater.rustic)
            ExternalHeaterHandler.registerHeatableAdapter(TileEntityCondenserBase.class, new RusticFurnaceAdapter());
    }

    public static class RusticFurnaceAdapter extends ExternalHeaterHandler.HeatableAdapter<TileEntityCondenserBase> {
        @Override
        public int doHeatTick(TileEntityCondenserBase condenser, int energy, boolean redstone) {
            int energyConsumed = 0;
            boolean did = false;
            boolean canCook = RusticUtils.canBrew(condenser);
            if(canCook || redstone) {
                int burnTime = condenser.condenserBurnTime;
                if(burnTime < 200) {
                    int heatAttempt = 4;
                    int heatEnergyRatio = Math.max(1, ExternalHeaterHandler.defaultFurnaceEnergyCost);
                    int energyToUse = Math.min(energy, heatAttempt * heatEnergyRatio);
                    int heat = energyToUse / heatEnergyRatio;
                    if(heat > 0) {
                        condenser.condenserBurnTime = burnTime + heat;
                        if(condenser.currentItemBurnTime < 200) {
                            condenser.currentItemBurnTime = 200;
                        }
                        energyConsumed += heat * heatEnergyRatio;
                        did = true;
                    }
                }
                if(canCook && condenser.condenserBurnTime >= 200 && condenser.brewTime < (condenser.totalBrewTime - 1)) {
                    int energyToUse = ExternalHeaterHandler.defaultFurnaceSpeedupCost;
                    if(energy - energyConsumed > energyToUse) {
                        energyConsumed += energyToUse;
                        condenser.brewTime = Math.min((condenser.totalBrewTime - 1), condenser.brewTime + 1);
                        did = true;
                    }
                }
            }
            if(did) {
                RusticUtils.updateCondenser(condenser);
            }
            return energyConsumed;
        }
    }
}
