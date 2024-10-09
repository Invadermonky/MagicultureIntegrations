package com.invadermonky.magicultureintegrations.integrations.naturesaura.mods;

import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.tile.HeatableUtils;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import de.ellpeck.naturesaura.blocks.tiles.TileEntityFurnaceHeater;
import ic2.core.block.machine.tileentity.TileEntityBlastFurnace;
import ic2.core.block.machine.tileentity.TileEntityFermenter;
import ic2.core.block.steam.TileEntityCokeKiln;

public class NAIndustrialCraft implements IModIntegration {
    @Override
    public void preInit() {
        if(!ConfigHandlerMI.heatables.industrial_craft.blast_furnace.extraneous_firestarter) {
            HeatableUtils.blacklistHeatable(TileEntityFurnaceHeater.class, TileEntityBlastFurnace.class);
        }
        if(!ConfigHandlerMI.heatables.industrial_craft.coke_kiln.extraneous_firestarter) {
            HeatableUtils.blacklistHeatable(TileEntityFurnaceHeater.class, TileEntityCokeKiln.class);
        }
        if(!ConfigHandlerMI.heatables.industrial_craft.fermenter.extraneous_firestarter) {
            HeatableUtils.blacklistHeatable(TileEntityFurnaceHeater.class, TileEntityFermenter.class);
        }

    }
}
