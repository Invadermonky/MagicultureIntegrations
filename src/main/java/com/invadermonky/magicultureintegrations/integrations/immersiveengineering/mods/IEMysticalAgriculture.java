package com.invadermonky.magicultureintegrations.integrations.immersiveengineering.mods;

import blusunrize.immersiveengineering.api.tool.ExternalHeaterHandler;
import blusunrize.immersiveengineering.api.tool.ExternalHeaterHandler.HeatableAdapter;
import com.blakebr0.mysticalagriculture.tileentity.furnace.TileEssenceFurnace;
import com.invadermonky.magicultureintegrations.api.mods.immersiveengineering.IIEIntegration;
import com.invadermonky.magicultureintegrations.api.mods.mysticalagriculture.MysticalAgricultureUtils;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;

public class IEMysticalAgriculture implements IIEIntegration {
    @Override
    public void registerExternalHeaterHandler() {
        if(ConfigHandlerMI.immersive_engineering.externalHeater.mystical_agriculture)
            ExternalHeaterHandler.registerHeatableAdapter(TileEssenceFurnace.class, new MysticalAgricultureFurnaceAdapter());
    }

    public static class MysticalAgricultureFurnaceAdapter extends HeatableAdapter<TileEssenceFurnace> {
        @Override
        public int doHeatTick(TileEssenceFurnace furnace, int energy, boolean redstone) {
            int energyConsumed = 0;
            boolean canCook = MysticalAgricultureUtils.canSmelt(furnace);
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
                    }
                }
                if(canCook && furnace.getField(0) >= 200 && furnace.getField(2) < (furnace.getField(3) - 1)) {
                    int energyToUse = ExternalHeaterHandler.defaultFurnaceSpeedupCost;
                    if(energy - energyConsumed > energyToUse) {
                        energyConsumed += energyToUse;
                        furnace.setField(2, Math.min((furnace.getField(3) - 1), furnace.getField(2) + 1));
                    }
                }
            }
            return energyConsumed;
        }
    }
}
