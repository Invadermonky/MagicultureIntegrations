package com.invadermonky.magicultureintegrations.registry;

import com.invadermonky.magicultureintegrations.MagicultureIntegrations;
import com.invadermonky.magicultureintegrations.api.IAddition;
import com.invadermonky.magicultureintegrations.api.IProxy;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.client.event.ModelRegistryEvent;
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
    public static final List<Block> MOD_BLOCKS = new ArrayList<>();
    public static final List<Item> MOD_ITEMS = new ArrayList<>();
    public static final List<IProxy> PROXY_ADDITIONS = new ArrayList<>();

    public static <T extends IAddition> void registerAddition(T toRegister) {
        if (toRegister != null && toRegister.isEnabled()) {
            if (toRegister instanceof Block)
                MOD_BLOCKS.add((Block) toRegister);
            if (toRegister instanceof Item)
                MOD_ITEMS.add((Item) toRegister);
            if (toRegister instanceof IProxy)
                PROXY_ADDITIONS.add((IProxy) toRegister);
        }
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        MOD_ITEMS.forEach(item -> ((IAddition) item).registerItems(registry));
        MOD_BLOCKS.forEach(block -> ((IAddition) block).registerItems(registry));
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();
        MOD_BLOCKS.forEach(block -> ((IAddition) block).registerBlocks(registry));
    }

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        IForgeRegistry<IRecipe> registry = event.getRegistry();
        MOD_ITEMS.forEach(item -> ((IAddition) item).registerRecipes(registry));
        MOD_BLOCKS.forEach(block -> ((IAddition) block).registerRecipes(registry));
        PROXY_ADDITIONS.forEach(block -> ((IAddition) block).registerRecipes(registry));
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        MOD_ITEMS.forEach(item -> ((IAddition) item).registerModels(event));
        MOD_BLOCKS.forEach(block -> ((IAddition) block).registerModels(event));
    }

}
