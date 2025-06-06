package com.invadermonky.magicultureintegrations.integrations.bloodmagic.mods;

import WayofTime.bloodmagic.ritual.harvest.HarvestRegistry;
import com.invadermonky.magicultureintegrations.api.IProxy;
import com.invadermonky.magicultureintegrations.config.MIConfigIntegrations;
import epicsquid.roots.init.ModBlocks;

public class BMRoots implements IProxy {
    @Override
    public void init() {
        if (MIConfigIntegrations.blood_magic.ritual_harvest.roots) {
            HarvestRegistry.registerStandardCrop(ModBlocks.cloud_berry, ModBlocks.cloud_berry.getMaxAge());
            HarvestRegistry.registerStandardCrop(ModBlocks.dewgonia, ModBlocks.dewgonia.getMaxAge());
            HarvestRegistry.registerStandardCrop(ModBlocks.infernal_bulb, ModBlocks.infernal_bulb.getMaxAge());
            HarvestRegistry.registerStandardCrop(ModBlocks.moonglow, ModBlocks.moonglow.getMaxAge());
            HarvestRegistry.registerStandardCrop(ModBlocks.pereskia, ModBlocks.pereskia.getMaxAge());
            HarvestRegistry.registerStandardCrop(ModBlocks.spirit_herb, ModBlocks.spirit_herb.getMaxAge());
            HarvestRegistry.registerStandardCrop(ModBlocks.stalicripe, ModBlocks.stalicripe.getMaxAge());
            HarvestRegistry.registerStandardCrop(ModBlocks.wildroot, ModBlocks.wildroot.getMaxAge());
            HarvestRegistry.registerStandardCrop(ModBlocks.wildewheet, ModBlocks.wildewheet.getMaxAge());
        }
    }
}
