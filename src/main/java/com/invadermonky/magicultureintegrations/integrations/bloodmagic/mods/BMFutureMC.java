package com.invadermonky.magicultureintegrations.integrations.bloodmagic.mods;

import com.invadermonky.magicultureintegrations.api.mods.bloodmagic.IBMIntegration;
import com.invadermonky.magicultureintegrations.api.mods.futuremc.FutureMCHeatable;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.integrations.bloodmagic.events.BMFurnaceArrayHandler;
import thedarkcolour.futuremc.tile.TileFurnaceAdvanced;

public class BMFutureMC implements IBMIntegration {
    @Override
    public void registerCropHandlers() {}

    @Override
    public void registerFurnaceArrayHandlers() {
        if(ConfigHandlerMI.blood_magic.furnaceArray.future_mc) {
            BMFurnaceArrayHandler.registerFurnaceArrayHeatable(TileFurnaceAdvanced.class, FutureMCHeatable.class);
        }
    }

    @Override
    public void registerMiscellaneous() {}
}
