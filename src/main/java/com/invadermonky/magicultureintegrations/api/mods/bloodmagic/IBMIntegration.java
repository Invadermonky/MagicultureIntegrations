package com.invadermonky.magicultureintegrations.api.mods.bloodmagic;

public interface IBMIntegration {
    default void registerCropHandlers() {}
    default void registerFurnaceArrayHandlers() {}
    default void registerMiscellaneous() {}
}
