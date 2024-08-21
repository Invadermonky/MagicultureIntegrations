package com.invadermonky.magicultureintegrations.integrations.bloodmagic.compat;

import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.mods.cookingforblockheads.CookingForBlockheadsHeatable;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.integrations.bloodmagic.events.BMFurnaceArrayHandler;
import net.blay09.mods.cookingforblockheads.tile.TileOven;

public class BMCookingForBlockheads implements IModIntegration {
    @Override
    public void preInit() {
        if(ConfigHandlerMI.blood_magic.furnaceArray.cooking_for_blockheads) {
            BMFurnaceArrayHandler.registerFurnaceArrayHeatable(TileOven.class, CookingForBlockheadsHeatable.class);
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void postInit() {

    }
}
