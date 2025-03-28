package com.invadermonky.magicultureintegrations.integrations.thaumcraft;

import com.invadermonky.magicultureintegrations.api.mods.IIntegrationModule;
import com.invadermonky.magicultureintegrations.config.MIConfigIntegrations;
import com.invadermonky.magicultureintegrations.util.IntegrationList;
import thaumcraft.common.tiles.devices.TileBellows;

public class InitThaumcraft implements IIntegrationModule {
    private final IntegrationList integrations = new IntegrationList("Thaumcraft");

    @Override
    public void buildModIntegrations() {}

    @Override
    public IntegrationList getModIntegrations() {
        return this.integrations;
    }

    @Override
    public void postInit() {
        MIConfigIntegrations.thaumcraft.bellows_integrations.registerHeatableBlacklists(TileBellows.class);
    }
}
