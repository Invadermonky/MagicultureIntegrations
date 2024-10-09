package com.invadermonky.magicultureintegrations.integrations.naturesaura.mods;

import com.blakebr0.mysticalagriculture.tileentity.furnace.TileEssenceFurnace;
import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.tile.HeatableUtils;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import de.ellpeck.naturesaura.blocks.tiles.TileEntityFurnaceHeater;

public class NAMysticalAgriculture implements IModIntegration {
    @Override
    public void preInit() {
        if(!ConfigHandlerMI.heatables.mystical_agriculture.extraneous_firestarter) {
            HeatableUtils.blacklistHeatable(TileEntityFurnaceHeater.class, TileEssenceFurnace.class);
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void postInit() {

    }
}
