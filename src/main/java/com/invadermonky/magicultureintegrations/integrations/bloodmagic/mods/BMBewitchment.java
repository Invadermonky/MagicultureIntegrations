package com.invadermonky.magicultureintegrations.integrations.bloodmagic.mods;

import WayofTime.bloodmagic.ritual.harvest.HarvestRegistry;
import com.bewitchment.common.block.tile.entity.TileEntityWitchesOven;
import com.bewitchment.registry.ModObjects;
import com.invadermonky.magicultureintegrations.api.mods.bewitchment.BewitchmentHeatable;
import com.invadermonky.magicultureintegrations.api.mods.bloodmagic.IBMIntegration;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.integrations.bloodmagic.events.BMFurnaceArrayHandler;

public class BMBewitchment implements IBMIntegration {
    @Override
    public void registerCropHandlers() {
        if(ConfigHandlerMI.blood_magic.harvestRitual.bewitchment) {
            HarvestRegistry.registerStandardCrop(ModObjects.crop_aconitum, (ModObjects.crop_aconitum).getMaxAge());
            HarvestRegistry.registerStandardCrop(ModObjects.crop_belladonna, (ModObjects.crop_belladonna).getMaxAge());
            HarvestRegistry.registerStandardCrop(ModObjects.crop_garlic, (ModObjects.crop_garlic).getMaxAge());
            HarvestRegistry.registerStandardCrop(ModObjects.crop_hellebore, (ModObjects.crop_hellebore).getMaxAge());
            HarvestRegistry.registerStandardCrop(ModObjects.crop_mandrake, (ModObjects.crop_mandrake).getMaxAge());
            HarvestRegistry.registerStandardCrop(ModObjects.crop_white_sage, (ModObjects.crop_white_sage).getMaxAge());
            HarvestRegistry.registerStandardCrop(ModObjects.crop_wormwood, (ModObjects.crop_wormwood).getMaxAge());
        }
    }

    @Override
    public void registerFurnaceArrayHandlers() {
        if(ConfigHandlerMI.blood_magic.furnaceArray.bewitchment) {
            BMFurnaceArrayHandler.registerFurnaceArrayHeatable(TileEntityWitchesOven.class, BewitchmentHeatable.class);
        }
    }
}
