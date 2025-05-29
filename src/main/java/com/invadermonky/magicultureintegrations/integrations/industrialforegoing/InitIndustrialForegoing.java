package com.invadermonky.magicultureintegrations.integrations.industrialforegoing;

import com.buuz135.industrial.api.plant.PlantRecollectable;
import com.invadermonky.magicultureintegrations.api.mods.IntegrationModule;
import com.invadermonky.magicultureintegrations.integrations.industrialforegoing.mods.IFHarvestableCrop;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class InitIndustrialForegoing extends IntegrationModule {
    public InitIndustrialForegoing() {
        super("Industrial Foregoing");
    }

    @Override
    public void buildModIntegrations() {

    }

    @Override
    public void preInit() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void registerFarmer(RegistryEvent.Register<PlantRecollectable> event) {
        event.getRegistry().register(new IFHarvestableCrop());
    }
}
