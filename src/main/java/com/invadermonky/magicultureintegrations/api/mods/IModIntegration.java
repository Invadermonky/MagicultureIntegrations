package com.invadermonky.magicultureintegrations.api.mods;

public interface IModIntegration {
    /**
     *
     */
    void buildModules();

    /**
     *
     */
    void preInit();

    /**
     *
     */
    void init();

    /**
     *
     */
    void postInit();
}
