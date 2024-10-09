package com.invadermonky.magicultureintegrations.integrations.naturesaura.mods;

import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.tile.HeatableUtils;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import slimeknights.tconstruct.smeltery.tileentity.TileHeatingStructure;
import vazkii.botania.common.block.subtile.functional.SubTileExoflame;

public class NATinkersConstruct implements IModIntegration {
    @Override
    public void preInit() {
        if(!ConfigHandlerMI.heatables.tinkers_construct.extraneous_firestarter) {
            HeatableUtils.blacklistHeatable(SubTileExoflame.class, TileHeatingStructure.class);
        }
    }
}
