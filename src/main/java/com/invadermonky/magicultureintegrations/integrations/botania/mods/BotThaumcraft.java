package com.invadermonky.magicultureintegrations.integrations.botania.mods;

import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.tile.HeatableUtils;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import thaumcraft.common.tiles.essentia.TileSmelter;
import vazkii.botania.common.block.subtile.functional.SubTileExoflame;

public class BotThaumcraft implements IModIntegration {
    @Override
    public void preInit() {
        if(!ConfigHandlerMI.heatables.thaumcraft.exoflame) {
            HeatableUtils.blacklistHeatable(SubTileExoflame.class, TileSmelter.class);
        }
    }
}
