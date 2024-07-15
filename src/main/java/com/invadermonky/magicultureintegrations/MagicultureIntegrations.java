package com.invadermonky.magicultureintegrations;

import com.invadermonky.magicultureintegrations.proxy.CommonProxy;
import com.invadermonky.magicultureintegrations.util.LogHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
        modid = MagicultureIntegrations.MOD_ID,
        name = MagicultureIntegrations.MOD_NAME,
        version = MagicultureIntegrations.MOD_VERSION,
        acceptedMinecraftVersions = MagicultureIntegrations.MC_VERSION
)
public class MagicultureIntegrations {
    public static final String MOD_ID = "magicultureintegrations";
    public static final String MOD_NAME = "Magiculture Integrations";
    public static final String MOD_VERSION = "1.12.2-1.0.0";
    public static final String MC_VERSION = "[1.12.2]";

    public static final String ProxyClientClass = "com.invadermonky." + MOD_ID + ".proxy.ClientProxy";
    public static final String ProxyServerClass = "com.invadermonky." + MOD_ID + ".proxy.CommonProxy";

    @Mod.Instance(MOD_ID)
    public static MagicultureIntegrations INSTANCE;

    @SidedProxy(clientSide = ProxyClientClass, serverSide = ProxyServerClass)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LogHelper.info("Starting " + MOD_NAME);
        proxy.preInit(event);
        LogHelper.debug("Finished preInit phase.");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
        LogHelper.debug("Finished init phase.");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
        LogHelper.debug("Finished postInit phase.");
    }
}
