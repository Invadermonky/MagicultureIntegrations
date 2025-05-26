package com.invadermonky.magicultureintegrations.util;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

public class ConfigHelper {
    @Nullable
    public static IBlockState parseBlockState(String registryName, int meta) {
        Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(registryName));
        return block != null ? block.getStateFromMeta(meta) : null;
    }

    @Nullable
    public static IBlockState parseBockState(String registryName) {
        Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(registryName));
        return block != null ? block.getDefaultState() : null;
    }
}
