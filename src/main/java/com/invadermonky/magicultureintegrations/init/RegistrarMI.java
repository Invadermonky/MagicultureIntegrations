package com.invadermonky.magicultureintegrations.init;

import com.invadermonky.magicultureintegrations.MagicultureIntegrations;
import com.invadermonky.magicultureintegrations.api.mods.IAddition;
import com.invadermonky.magicultureintegrations.integrations.InitIntegrations;
import net.minecraft.block.Block;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber(modid = MagicultureIntegrations.MOD_ID)
public class RegistrarMI {
    public static final List<Item> ITEMS = new ArrayList<>();
    public static final Map<Block,Boolean> BLOCKS = new LinkedHashMap<>();

    public static <T extends Item & IAddition> void registerItem(T toRegister) {
        if(toRegister != null && toRegister.isEnabled())
            ITEMS.add(toRegister);
    }

    public static <T extends Block & IAddition> void registerBlock(T toRegister) {
        registerBlock(toRegister, true);
    }

    public static <T extends Block & IAddition> void registerBlock(T toRegister, boolean doRegister) {
        if(toRegister != null && toRegister.isEnabled()) {
            BLOCKS.put(toRegister, doRegister);
        }
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        ITEMS.forEach(registry::register);
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();
        BLOCKS.forEach((block, doRegister) -> {
            if(doRegister) {
                registry.register(block);
            }
        });
    }



    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        ITEMS.forEach(item -> {
            ModelResourceLocation loc = new ModelResourceLocation(item.delegate.name(), "inventory");
            ModelLoader.setCustomModelResourceLocation(item, 0, loc);
        });

        BLOCKS.forEach((block,doRegister) -> {

        });

        InitIntegrations.integrationModules.forEach(module -> {
            if(module.getAdditions() != null) {
                module.getAdditions().forEach(IAddition::registerCustomRenders);
            }
        });
    }
}
