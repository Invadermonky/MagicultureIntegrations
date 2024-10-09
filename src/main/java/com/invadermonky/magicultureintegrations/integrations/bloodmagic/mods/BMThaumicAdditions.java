package com.invadermonky.magicultureintegrations.integrations.bloodmagic.mods;

import WayofTime.bloodmagic.alchemyArray.AlchemyArrayEffectFurnaceFuel;
import WayofTime.bloodmagic.ritual.harvest.HarvestRegistry;
import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.tile.HeatableUtils;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import org.zeith.thaumicadditions.init.BlocksTAR;
import org.zeith.thaumicadditions.tiles.TileAbstractSmelter;

public class BMThaumicAdditions implements IModIntegration {
    @Override
    public void preInit() {
        if(!ConfigHandlerMI.heatables.thaumic_additions.furnace_heater_array) {
            HeatableUtils.blacklistHeatable(AlchemyArrayEffectFurnaceFuel.class, TileAbstractSmelter.class);
        }
    }

    @Override
    public void init() {
        if(ConfigHandlerMI.integrations.blood_magic.harvest_ritual.thaumic_additions) {
            BlocksTAR.VIS_CROPS.forEach((aspect, crop) -> {
                HarvestRegistry.registerStandardCrop(crop, crop.getMaxAge());
            });
            HarvestRegistry.registerStandardCrop(BlocksTAR.VOID_CROP, BlocksTAR.VOID_CROP.getMaxAge());
        }
    }
}
