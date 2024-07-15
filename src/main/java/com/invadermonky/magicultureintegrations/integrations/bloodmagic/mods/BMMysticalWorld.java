package com.invadermonky.magicultureintegrations.integrations.bloodmagic.mods;

import WayofTime.bloodmagic.ritual.harvest.HarvestRegistry;
import com.invadermonky.magicultureintegrations.api.mods.bloodmagic.IBMIntegration;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import epicsquid.mysticalworld.init.ModBlocks;

public class BMMysticalWorld implements IBMIntegration {
    @Override
    public void registerCropHandlers() {
        if(ConfigHandlerMI.blood_magic.harvestRitual.mystical_world) {
            HarvestRegistry.registerStandardCrop(ModBlocks.aubergine, ModBlocks.aubergine.getMaxAge());
        }
    }
}
