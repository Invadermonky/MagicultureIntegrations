package com.invadermonky.magicultureintegrations.api.mods;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IAddition extends IConfigurable {
    /**
     * Register any recipes or compatibilities here. Guidebook entries are also initialized in this method.
     */
    void registerRecipe();

    @SideOnly(Side.CLIENT)
    default void registerCustomRenders() {}
}
