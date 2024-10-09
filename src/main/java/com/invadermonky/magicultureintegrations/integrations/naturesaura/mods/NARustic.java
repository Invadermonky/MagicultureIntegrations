package com.invadermonky.magicultureintegrations.integrations.naturesaura.mods;

import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.tile.HeatableUtils;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import de.ellpeck.naturesaura.blocks.tiles.TileEntityFurnaceHeater;
import rustic.common.tileentity.TileEntityCondenserBase;

public class NARustic implements IModIntegration {
    @Override
    public void preInit() {
        if(!ConfigHandlerMI.heatables.rustic.extraneous_firestarter) {
            HeatableUtils.blacklistHeatable(TileEntityFurnaceHeater.class, TileEntityCondenserBase.class);
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void postInit() {

    }
}
