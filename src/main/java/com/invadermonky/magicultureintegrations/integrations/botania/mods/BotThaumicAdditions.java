package com.invadermonky.magicultureintegrations.integrations.botania.mods;

import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.tile.HeatableUtils;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import org.zeith.thaumicadditions.tiles.TileAbstractSmelter;
import vazkii.botania.common.block.subtile.functional.SubTileExoflame;

public class BotThaumicAdditions implements IModIntegration {
    @Override
    public void preInit() {
        if(!ConfigHandlerMI.heatables.thaumic_additions.exoflame) {
            HeatableUtils.blacklistHeatable(SubTileExoflame.class, TileAbstractSmelter.class);
        }
    }
}
