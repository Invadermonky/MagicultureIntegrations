package com.invadermonky.magicultureintegrations.integrations.naturesaura.mods;

import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.tile.HeatableUtils;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import de.ellpeck.naturesaura.blocks.tiles.TileEntityFurnaceHeater;
import wile.engineersdecor.blocks.BlockDecorFurnace;

public class NAEngineersDecor implements IModIntegration {
    @Override
    public void preInit() {
        if(!ConfigHandlerMI.heatables.engineers_decor.extraneous_firestarter) {
            HeatableUtils.blacklistHeatable(TileEntityFurnaceHeater.class, BlockDecorFurnace.BTileEntity.class);
        }
    }
}
