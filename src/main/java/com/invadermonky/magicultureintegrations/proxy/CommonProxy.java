package com.invadermonky.magicultureintegrations.proxy;

import com.invadermonky.magicultureintegrations.integrations.InitIntegrations;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        InitIntegrations.preInit();
    }
    public void init(FMLInitializationEvent event) {
        InitIntegrations.init();
    }
    public void postInit(FMLPostInitializationEvent event) {
        InitIntegrations.postInit();
    }
}
