package com.invadermonky.magicultureintegrations.integrations.bloodmagic.mods;

import WayofTime.bloodmagic.alchemyArray.AlchemyArrayEffectFurnaceFuel;
import com.invadermonky.magicultureintegrations.api.IProxy;
import com.invadermonky.magicultureintegrations.api.tile.HeatableUtils;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import net.blay09.mods.cookingforblockheads.tile.TileOven;

public class BMCookingForBlockheads implements IProxy {
    @Override
    public void preInit() {
        if(!ConfigHandlerMI.heatables.cooking_for_blockheads.furnace_heater_array) {
            HeatableUtils.blacklistHeatable(AlchemyArrayEffectFurnaceFuel.class, TileOven.class);
        }
    }
}
