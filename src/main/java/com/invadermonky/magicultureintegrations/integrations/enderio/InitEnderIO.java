package com.invadermonky.magicultureintegrations.integrations.enderio;

import com.invadermonky.magicultureintegrations.api.mods.IIntegrationModule;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.integrations.enderio.mods.EIOAgricraft;
import com.invadermonky.magicultureintegrations.integrations.enderio.mods.EIOAttainedDrops;
import com.invadermonky.magicultureintegrations.util.IntegrationList;
import com.invadermonky.magicultureintegrations.util.ModIds;
import crazypants.enderio.api.farm.IFarmerJoe;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

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
        //TODO: OreBerries needs a custom harvest handler
        if(ModIds.agricraft.isLoaded && ConfigHandlerMI.integrations.ender_io.fixAgricraftHarvestCrash) {
            event.getRegistry().register(new EIOAgricraft());
        }
        if(ModIds.attained_drops.isLoaded && ConfigHandlerMI.integrations.ender_io.attainedDropsHarvest) {
            event.getRegistry().register(new EIOAttainedDrops());
        }
    }
}
