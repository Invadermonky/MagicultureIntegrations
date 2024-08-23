package com.invadermonky.magicultureintegrations.integrations.bloodmagic.compat;

import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.mods.futuremc.FutureMCHeatable;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.integrations.bloodmagic.events.BMFurnaceArrayHandler;
import thedarkcolour.futuremc.tile.TileFurnaceAdvanced;

public class BMFutureMC implements IModIntegration {
    @Override
    public void preInit() {
        if(ConfigHandlerMI.blood_magic.furnace_array.future_mc) {
            BMFurnaceArrayHandler.registerFurnaceArrayHeatable(TileFurnaceAdvanced.class, FutureMCHeatable.class);
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void postInit() {

    }
}
