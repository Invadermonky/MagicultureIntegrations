package com.invadermonky.magicultureintegrations.integrations.bloodmagic.compat;

import WayofTime.bloodmagic.ritual.harvest.HarvestRegistry;
import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import epicsquid.mysticalworld.init.ModBlocks;

public class BMMysticalWorld implements IModIntegration {
    @Override
    public void preInit() {

    }

    @Override
    public void init() {
        if(ConfigHandlerMI.blood_magic.harvestRitual.mystical_world) {
            HarvestRegistry.registerStandardCrop(ModBlocks.aubergine, ModBlocks.aubergine.getMaxAge());
        }
    }

    @Override
    public void postInit() {

    }
}
