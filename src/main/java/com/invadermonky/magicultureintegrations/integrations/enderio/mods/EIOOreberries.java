package com.invadermonky.magicultureintegrations.integrations.enderio.mods;

import com.invadermonky.magicultureintegrations.api.IProxy;
import com.invadermonky.magicultureintegrations.util.ModIds;
import crazypants.enderio.api.farm.IFarmerJoe;
import crazypants.enderio.base.farming.farmers.PlantableFarmer;
import josephcsible.oreberries.proxy.CommonProxy;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EIOOreberries implements IProxy {
    @Override
    public void preInit() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void registerFarmers(RegistryEvent.Register<IFarmerJoe> event) {
        IFarmerJoe farmer = event.getRegistry().getValue(new ResourceLocation(ModIds.enderio.modId, "default"));
        if (farmer instanceof PlantableFarmer) {
            CommonProxy.oreberryBushBlocks.forEach(block -> ((PlantableFarmer) farmer).addHarvestExlude(block));
        }
    }
}
