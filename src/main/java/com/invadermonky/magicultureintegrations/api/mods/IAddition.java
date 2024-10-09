package com.invadermonky.magicultureintegrations.api.mods;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IAddition extends IConfigurable {
    /**
     * Any new guidebook entries are registered here.
     */
    default void registerGuideEntry() {}

    /**
     * Register any recipes or compatibilities here..
     */
    default void registerRecipe() {}

    @SideOnly(Side.CLIENT)
    default void registerCustomRenders() {}
}
