package com.invadermonky.magicultureintegrations.integrations.immersiveengineering.mods;

import blusunrize.immersiveengineering.api.tool.ExternalHeaterHandler;
import blusunrize.immersiveengineering.api.tool.ExternalHeaterHandler.HeatableAdapter;
import com.blakebr0.mysticalagriculture.tileentity.furnace.TileEssenceFurnace;
import com.invadermonky.magicultureintegrations.api.IProxy;
import com.invadermonky.magicultureintegrations.api.mods.immersiveengineering.ImmersiveEngineeringUtils;
import com.invadermonky.magicultureintegrations.api.tile.IHeatableTile;
import com.invadermonky.magicultureintegrations.config.MIConfigIntegrations;

public class IEMysticalAgriculture implements IProxy {
    @Override
    public void preInit() {
        if(MIConfigIntegrations.immersive_engineering.external_heater_integrations.ma_essence_furnaces) {
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
            return furnace instanceof IHeatableTile ? ImmersiveEngineeringUtils.doHeatableHeatTick((IHeatableTile) furnace, energy, redstone) : 0;
        }
    }
}
