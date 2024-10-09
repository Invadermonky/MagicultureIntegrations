package com.invadermonky.magicultureintegrations.api.mods;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

public interface IModIntegration {
    default void preInit() {}
    default void init() {}
    default void postInit() {}

    @SideOnly(Side.CLIENT)
    default void preInitClient() {}
    @SideOnly(Side.CLIENT)
    default void initClient() {}
    @SideOnly(Side.CLIENT)
    default void postInitClient() {}

    default void onBlockRegister(IForgeRegistry<Block> registry) {}
    default void onItemRegister(IForgeRegistry<Item> registry) {}
    default void onModelRegister(ModelRegistryEvent event) {}
    default void onRecipeRegister(IForgeRegistry<IRecipe> registry) {}
}
