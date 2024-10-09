package com.invadermonky.magicultureintegrations.integrations.botania.mods;

import com.blakebr0.mysticalagriculture.tileentity.furnace.TileEssenceFurnace;
import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.tile.HeatableUtils;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import vazkii.botania.common.block.subtile.functional.SubTileExoflame;

public class BotMysticalAgriculture implements IModIntegration {
    @Override
    public void preInit() {
        if(!ConfigHandlerMI.heatables.mystical_agriculture.exoflame) {
            HeatableUtils.blacklistHeatable(SubTileExoflame.class, TileEssenceFurnace.class);
        }
    }
}
