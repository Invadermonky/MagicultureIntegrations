package com.invadermonky.magicultureintegrations.proxy;

import com.invadermonky.magicultureintegrations.api.IProxy;
import com.invadermonky.magicultureintegrations.integrations.InitIntegrations;
import com.invadermonky.magicultureintegrations.registry.RegistrarMI;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        InitIntegrations.preInitClient();
        RegistrarMI.PROXY_ADDITIONS.forEach(IProxy::preInitClient);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        InitIntegrations.initClient();
        RegistrarMI.PROXY_ADDITIONS.forEach(IProxy::initClient);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
        InitIntegrations.postInitClient();
        RegistrarMI.PROXY_ADDITIONS.forEach(IProxy::postInitClient);
    }
}
