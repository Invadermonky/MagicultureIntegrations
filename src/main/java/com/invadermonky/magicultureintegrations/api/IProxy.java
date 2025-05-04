package com.invadermonky.magicultureintegrations.api;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IProxy {
    default void preInit() {
    }

    @SideOnly(Side.CLIENT)
    default void preInitClient() {
    }

    @SideOnly(Side.CLIENT)
    default void initClient() {
    }

    default void init() {
    }

    default void postInit() {
    }

    @SideOnly(Side.CLIENT)
    default void postInitClient() {
    }

}
