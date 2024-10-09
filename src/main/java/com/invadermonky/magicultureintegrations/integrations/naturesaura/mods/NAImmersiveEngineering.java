package com.invadermonky.magicultureintegrations.integrations.naturesaura.mods;

import blusunrize.immersiveengineering.common.blocks.stone.TileEntityAlloySmelter;
import blusunrize.immersiveengineering.common.blocks.stone.TileEntityBlastFurnace;
import blusunrize.immersiveengineering.common.blocks.stone.TileEntityCokeOven;
import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.tile.HeatableUtils;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import de.ellpeck.naturesaura.blocks.tiles.TileEntityFurnaceHeater;

public class NAImmersiveEngineering implements IModIntegration {
    @Override
    public void preInit() {
        if(!ConfigHandlerMI.heatables.immersive_engineering.alloy_smelter.extraneous_firestarter) {
            HeatableUtils.blacklistHeatable(TileEntityFurnaceHeater.class, TileEntityAlloySmelter.class);
        }
        if(!ConfigHandlerMI.heatables.immersive_engineering.blast_furnace.extraneous_firestarter) {
            HeatableUtils.blacklistHeatable(TileEntityFurnaceHeater.class, TileEntityBlastFurnace.class);
        }
        if(!ConfigHandlerMI.heatables.immersive_engineering.coke_oven.extraneous_firestarter) {
            HeatableUtils.blacklistHeatable(TileEntityFurnaceHeater.class, TileEntityCokeOven.class);
        }
    }
}
