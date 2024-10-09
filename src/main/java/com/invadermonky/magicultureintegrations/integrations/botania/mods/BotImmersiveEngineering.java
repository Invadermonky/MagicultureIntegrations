package com.invadermonky.magicultureintegrations.integrations.botania.mods;

import blusunrize.immersiveengineering.common.blocks.stone.TileEntityAlloySmelter;
import blusunrize.immersiveengineering.common.blocks.stone.TileEntityBlastFurnace;
import blusunrize.immersiveengineering.common.blocks.stone.TileEntityCokeOven;
import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.tile.HeatableUtils;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import vazkii.botania.common.block.subtile.functional.SubTileExoflame;

public class BotImmersiveEngineering implements IModIntegration {
    @Override
    public void preInit() {
        if(!ConfigHandlerMI.heatables.immersive_engineering.alloy_smelter.exoflame) {
            HeatableUtils.blacklistHeatable(SubTileExoflame.class, TileEntityAlloySmelter.class);
        }
        if(!ConfigHandlerMI.heatables.immersive_engineering.blast_furnace.exoflame) {
            HeatableUtils.blacklistHeatable(SubTileExoflame.class, TileEntityBlastFurnace.class);
        }
        if(!ConfigHandlerMI.heatables.immersive_engineering.coke_oven.exoflame) {
            HeatableUtils.blacklistHeatable(SubTileExoflame.class, TileEntityCokeOven.class);
        }
    }
}
