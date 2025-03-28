package com.invadermonky.magicultureintegrations.integrations.enderio;

import com.invadermonky.magicultureintegrations.api.mods.IIntegrationModule;
import com.invadermonky.magicultureintegrations.integrations.enderio.mods.EIOHarvestableCrop;
import com.invadermonky.magicultureintegrations.util.IntegrationList;
import com.invadermonky.magicultureintegrations.util.ModIds;
import crazypants.enderio.api.farm.IFarmerJoe;
import crazypants.enderio.base.farming.farmers.PlantableFarmer;
import josephcsible.oreberries.proxy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import shadows.attained.init.ModRegistry;

public class InitEnderIO implements IIntegrationModule {
    private final IntegrationList integrations = new IntegrationList("Ender IO");

    @Override
    public void buildModIntegrations() {}

    @Override
    public IntegrationList getModIntegrations() {
        return this.integrations;
    }

    @Override
    public void preInit() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void registerFarmers(RegistryEvent.Register<IFarmerJoe> event) {
        event.getRegistry().register(new EIOHarvestableCrop());

        IFarmerJoe farmer = event.getRegistry().getValue(new ResourceLocation(ModIds.enderio.modId, "default"));

        if(ModIds.attained_drops.isLoaded) {
            this.addPlantableFarmerExclusion(farmer, ModRegistry.PLANT);
        }
        if(ModIds.oreberries.isLoaded) {
            CommonProxy.oreberryBushBlocks.forEach(block -> this.addPlantableFarmerExclusion(farmer, block));
        }
    }

    private void addPlantableFarmerExclusion(IFarmerJoe farmer, Block block) {
        if(farmer instanceof PlantableFarmer) {
            ((PlantableFarmer) farmer).addHarvestExlude(block);
        }
    }
}
