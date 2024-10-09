package com.invadermonky.magicultureintegrations.integrations.botania.mods;

import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.tile.HeatableUtils;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import slimeknights.tconstruct.smeltery.tileentity.TileHeatingStructure;
import vazkii.botania.common.block.subtile.functional.SubTileExoflame;

public class BotTinkersConstruct implements IModIntegration {
    @Override
    public void preInit() {
        if(!ConfigHandlerMI.heatables.tinkers_construct.exoflame) {
            HeatableUtils.blacklistHeatable(SubTileExoflame.class, TileHeatingStructure.class);
        }
    }
}
