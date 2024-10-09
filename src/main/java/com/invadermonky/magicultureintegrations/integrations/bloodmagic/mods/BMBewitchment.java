package com.invadermonky.magicultureintegrations.integrations.bloodmagic.mods;

import WayofTime.bloodmagic.alchemyArray.AlchemyArrayEffectFurnaceFuel;
import WayofTime.bloodmagic.ritual.harvest.HarvestRegistry;
import com.bewitchment.common.block.tile.entity.TileEntityWitchesOven;
import com.bewitchment.registry.ModObjects;
import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.tile.HeatableUtils;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;

public class BMBewitchment implements IModIntegration {
    @Override
    public void preInit() {
        if(!ConfigHandlerMI.heatables.bewitchment.furnace_heater_array) {
            HeatableUtils.blacklistHeatable(AlchemyArrayEffectFurnaceFuel.class, TileEntityWitchesOven.class);
        }
    }

    @Override
    public void init() {
        if(ConfigHandlerMI.integrations.blood_magic.harvest_ritual.bewitchment) {
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
