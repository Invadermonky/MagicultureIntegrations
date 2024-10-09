package com.invadermonky.magicultureintegrations.integrations.botania.mods;

import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.tile.HeatableUtils;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import ic2.core.block.machine.tileentity.TileEntityBlastFurnace;
import ic2.core.block.machine.tileentity.TileEntityFermenter;
import ic2.core.block.steam.TileEntityCokeKiln;
import vazkii.botania.common.block.subtile.functional.SubTileExoflame;

public class BotIndustrialCraft implements IModIntegration {
    @Override
    public void preInit() {
        if(!ConfigHandlerMI.heatables.industrial_craft.blast_furnace.exoflame) {
            HeatableUtils.blacklistHeatable(SubTileExoflame.class, TileEntityBlastFurnace.class);
        }
        if(!ConfigHandlerMI.heatables.industrial_craft.coke_kiln.exoflame) {
            HeatableUtils.blacklistHeatable(SubTileExoflame.class, TileEntityCokeKiln.class);
        }
        if(!ConfigHandlerMI.heatables.industrial_craft.fermenter.exoflame) {
            HeatableUtils.blacklistHeatable(SubTileExoflame.class, TileEntityFermenter.class);
        }
    }
}
