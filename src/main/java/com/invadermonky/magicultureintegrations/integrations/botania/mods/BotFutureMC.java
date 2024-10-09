package com.invadermonky.magicultureintegrations.integrations.botania.mods;

import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.tile.HeatableUtils;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import thedarkcolour.futuremc.tile.TileFurnaceAdvanced;
import vazkii.botania.common.block.subtile.functional.SubTileExoflame;

public class BotFutureMC implements IModIntegration {
    @Override
    public void preInit() {
        if(!ConfigHandlerMI.heatables.future_mc.exoflame) {
            HeatableUtils.blacklistHeatable(SubTileExoflame.class, TileFurnaceAdvanced.class);
        }
    }
}
