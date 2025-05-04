package com.invadermonky.magicultureintegrations.api;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

public interface IAddition extends IConfigurable {
    default void registerBlocks(IForgeRegistry<Block> registry) {
    }

    default void registerItems(IForgeRegistry<Item> registry) {
    }

    default void registerModels(ModelRegistryEvent event) {
    }

    default void registerRecipes(IForgeRegistry<IRecipe> registry) {
    }
}
