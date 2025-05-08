package com.invadermonky.magicultureintegrations.integrations.thaumcraft;

import com.invadermonky.magicultureintegrations.api.mods.IntegrationModule;
import com.invadermonky.magicultureintegrations.config.MIConfigIntegrations;
import thaumcraft.common.tiles.devices.TileBellows;

public class InitThaumcraft extends IntegrationModule {
    public InitThaumcraft() {
        super("Thaumcraft");
    }

    @Override
    public void buildModIntegrations() {
    }

    @Override
    public void postInit() {
        MIConfigIntegrations.thaumcraft.bellowsHeatables.registerHeatableBlacklists(TileBellows.class);
    }
}
