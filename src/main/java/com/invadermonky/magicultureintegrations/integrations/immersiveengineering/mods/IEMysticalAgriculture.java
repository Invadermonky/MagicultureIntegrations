package com.invadermonky.magicultureintegrations.integrations.immersiveengineering.mods;

import blusunrize.immersiveengineering.api.tool.ExternalHeaterHandler;
import blusunrize.immersiveengineering.api.tool.ExternalHeaterHandler.HeatableAdapter;
import com.blakebr0.mysticalagriculture.tileentity.furnace.TileEssenceFurnace;
import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.mods.immersiveengineering.ImmersiveEngineeringUtils;
import com.invadermonky.magicultureintegrations.api.mods.mysticalagriculture.MysticalAgricultureHeatable;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;

public class IEMysticalAgriculture implements IModIntegration {
    @Override
    public void preInit() {
        if(ConfigHandlerMI.immersive_engineering.externalHeater.mystical_agriculture) {
            ExternalHeaterHandler.registerHeatableAdapter(TileEssenceFurnace.class, new MysticalAgricultureFurnaceAdapter());
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void postInit() {

    }

    public static class MysticalAgricultureFurnaceAdapter extends HeatableAdapter<TileEssenceFurnace> {
        @Override
        public int doHeatTick(TileEssenceFurnace furnace, int energy, boolean redstone) {
            return ImmersiveEngineeringUtils.doHeatableHeatTick(new MysticalAgricultureHeatable(furnace), energy, redstone);
        }
    }
}
