package com.invadermonky.magicultureintegrations.integrations.immersiveengineering.mods;

import blusunrize.immersiveengineering.api.tool.ExternalHeaterHandler;
import blusunrize.immersiveengineering.api.tool.ExternalHeaterHandler.HeatableAdapter;
import com.invadermonky.magicultureintegrations.api.mods.futuremc.FutureMCUtils;
import com.invadermonky.magicultureintegrations.api.mods.immersiveengineering.IIEIntegration;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import thedarkcolour.futuremc.tile.TileFurnaceAdvanced;

public class IEFutureMC implements IIEIntegration {
    @Override
    public void registerExternalHeaterHandler() {
        if(ConfigHandlerMI.immersive_engineering.externalHeater.future_mc)
            ExternalHeaterHandler.registerHeatableAdapter(TileFurnaceAdvanced.class, new FutureMCFurnaceAdapter());
    }

    public static class FutureMCFurnaceAdapter extends HeatableAdapter<TileFurnaceAdvanced> {
        @Override
        public int doHeatTick(TileFurnaceAdvanced furnace, int energy, boolean redstone) {
            int energyConsumed = 0;
            boolean canCook = FutureMCUtils.canFurnaceSmelt(furnace);
            if(canCook || redstone) {
                int burnTime = furnace.getField(0);
                if(burnTime < 200) {
                    int heatAttempt = 4;
                    int heatEnergyRatio = Math.max(1, ExternalHeaterHandler.defaultFurnaceEnergyCost);
                    int energyToUse = Math.min(energy, heatAttempt * heatEnergyRatio);
                    int heat = energyToUse / heatEnergyRatio;
                    if(heat > 0) {
                        furnace.setField(0, burnTime + heat);
                        energyConsumed += heat * heatEnergyRatio;
                        if(!furnace.isBurning()) {
                            TileFurnaceAdvanced.setState(true, furnace.getWorld(), furnace.getPos());
                        }
                    }
                }
                if(canCook && furnace.getField(0) >= 200 && furnace.getField(2) < 99) {
                    int energyToUse = ExternalHeaterHandler.defaultFurnaceSpeedupCost;
                    if(energy - energyConsumed > energyToUse) {
                        energyConsumed += energyToUse;
                        furnace.setField(2, Math.min(99, furnace.getField(2) + 1));
                    }
                }
            }
            return energyConsumed;
        }
    }
}
