package com.invadermonky.magicultureintegrations.proxy;

import com.invadermonky.magicultureintegrations.api.IProxy;
import com.invadermonky.magicultureintegrations.integrations.InitIntegrations;
import com.invadermonky.magicultureintegrations.registry.RegistrarMI;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        InitIntegrations.preInit();
        RegistrarMI.PROXY_ADDITIONS.forEach(IProxy::preInit);
    }

    public void init(FMLInitializationEvent event) {
        InitIntegrations.init();
        RegistrarMI.PROXY_ADDITIONS.forEach(IProxy::init);
    }

    public void postInit(FMLPostInitializationEvent event) {
        InitIntegrations.postInit();
        RegistrarMI.PROXY_ADDITIONS.forEach(IProxy::postInit);
    }
}
