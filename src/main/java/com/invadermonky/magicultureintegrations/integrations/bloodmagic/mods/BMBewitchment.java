package com.invadermonky.magicultureintegrations.integrations.bloodmagic.mods;

import WayofTime.bloodmagic.ritual.harvest.HarvestRegistry;
import com.bewitchment.registry.ModObjects;
import com.invadermonky.magicultureintegrations.api.IProxy;
import com.invadermonky.magicultureintegrations.config.MIConfigIntegrations;

public class BMBewitchment implements IProxy {
    @Override
    public void init() {
        if(MIConfigIntegrations.blood_magic.ritual_harvest.bewitchment) {
            HarvestRegistry.registerStandardCrop(ModObjects.crop_aconitum, (ModObjects.crop_aconitum).getMaxAge());
            HarvestRegistry.registerStandardCrop(ModObjects.crop_belladonna, (ModObjects.crop_belladonna).getMaxAge());
            HarvestRegistry.registerStandardCrop(ModObjects.crop_garlic, (ModObjects.crop_garlic).getMaxAge());
            HarvestRegistry.registerStandardCrop(ModObjects.crop_hellebore, (ModObjects.crop_hellebore).getMaxAge());
            HarvestRegistry.registerStandardCrop(ModObjects.crop_mandrake, (ModObjects.crop_mandrake).getMaxAge());
            HarvestRegistry.registerStandardCrop(ModObjects.crop_white_sage, (ModObjects.crop_white_sage).getMaxAge());
            HarvestRegistry.registerStandardCrop(ModObjects.crop_wormwood, (ModObjects.crop_wormwood).getMaxAge());
        }
    }
}
