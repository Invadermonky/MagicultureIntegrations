package com.invadermonky.magicultureintegrations.integrations.enderio.mods;

import com.invadermonky.magicultureintegrations.api.IProxy;
import com.invadermonky.magicultureintegrations.util.ModIds;
import crazypants.enderio.api.farm.IFarmerJoe;
import crazypants.enderio.base.farming.farmers.PlantableFarmer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import shadows.attained.init.ModRegistry;

public class EIOAttainedDrops implements IProxy {
    @Override
    public void preInit() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void registerFarmers(RegistryEvent.Register<IFarmerJoe> event) {
        IFarmerJoe farmer = event.getRegistry().getValue(new ResourceLocation(ModIds.enderio.modId, "default"));
        if (farmer instanceof PlantableFarmer) {
            ((PlantableFarmer) farmer).addHarvestExlude(ModRegistry.PLANT);
        }
    }
}
