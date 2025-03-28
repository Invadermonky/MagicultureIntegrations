package com.invadermonky.magicultureintegrations.integrations.bloodmagic.mods;

import WayofTime.bloodmagic.ritual.harvest.HarvestRegistry;
import com.invadermonky.magicultureintegrations.api.IProxy;
import com.invadermonky.magicultureintegrations.config.MIConfigIntegrations;
import epicsquid.mysticalworld.init.ModBlocks;

public class BMMysticalWorld implements IProxy {
    @Override
    public void init() {
        if(MIConfigIntegrations.blood_magic.ritual_harvest.mystical_world) {
            HarvestRegistry.registerStandardCrop(ModBlocks.aubergine, ModBlocks.aubergine.getMaxAge());
        }
    }
}
