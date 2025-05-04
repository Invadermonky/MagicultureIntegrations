package com.invadermonky.magicultureintegrations.integrations.bloodmagic.mods;

import WayofTime.bloodmagic.ritual.harvest.HarvestRegistry;
import com.invadermonky.magicultureintegrations.api.IProxy;
import com.invadermonky.magicultureintegrations.config.MIConfigIntegrations;
import org.zeith.thaumicadditions.init.BlocksTAR;

public class BMThaumicAdditions implements IProxy {
    @Override
    public void init() {
        if (MIConfigIntegrations.blood_magic.ritual_harvest.thaumic_additions) {
            BlocksTAR.VIS_CROPS.forEach((aspect, crop) -> HarvestRegistry.registerStandardCrop(crop, crop.getMaxAge()));
            HarvestRegistry.registerStandardCrop(BlocksTAR.VOID_CROP, BlocksTAR.VOID_CROP.getMaxAge());
        }
    }
}
