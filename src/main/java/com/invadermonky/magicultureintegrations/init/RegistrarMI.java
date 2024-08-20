package com.invadermonky.magicultureintegrations.init;

import com.invadermonky.magicultureintegrations.MagicultureIntegrations;
import com.invadermonky.magicultureintegrations.api.mods.IModAddition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = MagicultureIntegrations.MOD_ID)
public class RegistrarMI {
    public static final List<Item> ITEMS = new ArrayList<>();

    public static <T extends Item & IModAddition> void registerItem(T toRegister) {
        if(toRegister != null && toRegister.isEnabled())
            ITEMS.add(toRegister);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        ITEMS.forEach(registry::register);
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        ITEMS.forEach(item -> {
            ModelResourceLocation loc = new ModelResourceLocation(item.delegate.name(), "inventory");
            ModelLoader.setCustomModelResourceLocation(item, 0, loc);
        });
    }
}
