package com.invadermonky.magicultureintegrations.integrations.bloodmagic.mods;

import WayofTime.bloodmagic.ritual.harvest.HarvestRegistry;
import com.invadermonky.magicultureintegrations.api.IProxy;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import epicsquid.mysticalworld.init.ModBlocks;

public class BMMysticalWorld implements IProxy {
    @Override
    public void init() {
        if(ConfigHandlerMI.integrations.blood_magic.harvest_ritual.mystical_world) {
            HarvestRegistry.registerStandardCrop(ModBlocks.aubergine, ModBlocks.aubergine.getMaxAge());
        }
    }
}
