package com.invadermonky.magicultureintegrations.integrations.naturesaura.mods;

import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.tile.HeatableUtils;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import de.ellpeck.naturesaura.blocks.tiles.TileEntityFurnaceHeater;
import net.blay09.mods.cookingforblockheads.tile.TileOven;

public class NACookingForBlockheads implements IModIntegration {
    @Override
    public void preInit() {
        if(!ConfigHandlerMI.heatables.cooking_for_blockheads.extraneous_firestarter) {
            HeatableUtils.blacklistHeatable(TileEntityFurnaceHeater.class, TileOven.class);
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void postInit() {

    }
}
