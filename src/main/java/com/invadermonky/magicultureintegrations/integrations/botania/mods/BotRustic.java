package com.invadermonky.magicultureintegrations.integrations.botania.mods;

import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.tile.HeatableUtils;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import rustic.common.tileentity.TileEntityCondenserBase;
import vazkii.botania.common.block.subtile.functional.SubTileExoflame;

public class BotRustic implements IModIntegration {
    @Override
    public void preInit() {
        if(!ConfigHandlerMI.heatables.rustic.exoflame) {
            HeatableUtils.blacklistHeatable(SubTileExoflame.class, TileEntityCondenserBase.class);
        }
    }
}
