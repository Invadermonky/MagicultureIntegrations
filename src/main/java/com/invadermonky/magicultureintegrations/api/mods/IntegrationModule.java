package com.invadermonky.magicultureintegrations.api.mods;

import com.invadermonky.magicultureintegrations.api.IProxy;
import com.invadermonky.magicultureintegrations.util.IntegrationList;

public abstract class IntegrationModule implements IProxy {
    protected final String MOD_NAME;
    protected final IntegrationList integrations;

    public IntegrationModule(String modName) {
        this.MOD_NAME = modName;
        this.integrations = new IntegrationList(MOD_NAME);
    }

    public abstract void buildModIntegrations();

    public IntegrationList getModIntegrations() {
        return this.integrations;
    }
}
