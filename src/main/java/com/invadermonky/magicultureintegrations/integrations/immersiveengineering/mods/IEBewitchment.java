package com.invadermonky.magicultureintegrations.integrations.immersiveengineering.mods;

import blusunrize.immersiveengineering.api.tool.ExternalHeaterHandler;
import blusunrize.immersiveengineering.api.tool.ExternalHeaterHandler.HeatableAdapter;
import com.bewitchment.common.block.tile.entity.TileEntityWitchesOven;
import com.invadermonky.magicultureintegrations.api.mods.bewitchment.BewitchmentUtils;
import com.invadermonky.magicultureintegrations.api.mods.immersiveengineering.IIEIntegration;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;

public class IEBewitchment implements IIEIntegration {
    @Override
    public void registerExternalHeaterHandler() {
        if(ConfigHandlerMI.immersive_engineering.externalHeater.bewitchment)
            ExternalHeaterHandler.registerHeatableAdapter(TileEntityWitchesOven.class, new BewitchmentFurnaceAdapter());
    }

    public static class BewitchmentFurnaceAdapter extends HeatableAdapter<TileEntityWitchesOven> {
        @Override
        public int doHeatTick(TileEntityWitchesOven oven, int energy, boolean redstone) {
            int energyConsumed = 0;
            boolean canCook = BewitchmentUtils.canOvenSmelt(oven);
            if(canCook || redstone) {
                int burnTime = oven.burnTime;
                if(burnTime < 200) {
                    int heatAttempt = 4;
                    int heatEnergyRatio = Math.max(1, ExternalHeaterHandler.defaultFurnaceEnergyCost);
                    int energyToUse = Math.min(energy, heatAttempt * heatEnergyRatio);
                    int heat = energyToUse / heatEnergyRatio;
                    if(heat > 0) {
                        BewitchmentUtils.addOvenBurnTime(oven, burnTime + heat);
                        if(oven.fuelBurnTime < 200) {
                            oven.fuelBurnTime = 200;
                        }
                        energyConsumed += heat * heatEnergyRatio;
                        BewitchmentUtils.updateOven(oven, true);
                    }
                }
                if(canCook && oven.burnTime >= 200 && oven.progress < 199) {
                    int energyToUse = ExternalHeaterHandler.defaultFurnaceSpeedupCost;
                    if(energy - energyConsumed > energyToUse) {
                        energyConsumed += energyToUse;
                        oven.progress = Math.min(199, oven.progress + 1);
                    }
                }
            }
            return energyConsumed;
        }
    }
}
