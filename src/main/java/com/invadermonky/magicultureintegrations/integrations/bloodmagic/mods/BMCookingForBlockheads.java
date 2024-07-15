package com.invadermonky.magicultureintegrations.integrations.bloodmagic.mods;

import com.invadermonky.magicultureintegrations.api.mods.bloodmagic.IBMIntegration;
import com.invadermonky.magicultureintegrations.api.mods.cookingforblockheads.CookingForBlockheadsHeatable;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.integrations.bloodmagic.events.BMFurnaceArrayHandler;
import net.blay09.mods.cookingforblockheads.tile.TileOven;

public class BMCookingForBlockheads implements IBMIntegration {
    @Override
    public void registerCropHandlers() {}

    @Override
    public void registerFurnaceArrayHandlers() {
        if(ConfigHandlerMI.blood_magic.furnaceArray.cooking_for_blockheads) {
            BMFurnaceArrayHandler.registerFurnaceArrayHeatable(TileOven.class, CookingForBlockheadsHeatable.class);
        }
    }

    @Override
    public void registerMiscellaneous() {}
}
