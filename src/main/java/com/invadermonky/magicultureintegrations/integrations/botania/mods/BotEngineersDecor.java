package com.invadermonky.magicultureintegrations.integrations.botania.mods;

import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.tile.HeatableUtils;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import vazkii.botania.common.block.subtile.functional.SubTileExoflame;
import wile.engineersdecor.blocks.BlockDecorFurnace;

public class BotEngineersDecor implements IModIntegration {
    @Override
    public void preInit() {
        if(!ConfigHandlerMI.heatables.engineers_decor.exoflame) {
            HeatableUtils.blacklistHeatable(SubTileExoflame.class, BlockDecorFurnace.BTileEntity.class);
        }
    }
}
