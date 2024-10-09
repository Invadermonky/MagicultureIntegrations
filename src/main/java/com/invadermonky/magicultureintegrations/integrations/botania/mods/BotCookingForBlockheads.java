package com.invadermonky.magicultureintegrations.integrations.botania.mods;

import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.tile.HeatableUtils;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import net.blay09.mods.cookingforblockheads.tile.TileOven;
import vazkii.botania.common.block.subtile.functional.SubTileExoflame;

public class BotCookingForBlockheads implements IModIntegration {
    @Override
    public void preInit() {
        if(!ConfigHandlerMI.heatables.cooking_for_blockheads.exoflame) {
            HeatableUtils.blacklistHeatable(SubTileExoflame.class, TileOven.class);
        }
    }
}
