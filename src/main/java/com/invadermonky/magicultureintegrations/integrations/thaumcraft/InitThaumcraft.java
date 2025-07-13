package com.invadermonky.magicultureintegrations.integrations.thaumcraft;

import com.invadermonky.magicultureintegrations.api.mods.IntegrationModule;
import com.invadermonky.magicultureintegrations.config.MIConfigAdditions;
import com.invadermonky.magicultureintegrations.config.MIConfigIntegrations;
import com.invadermonky.magicultureintegrations.integrations.thaumcraft.infusion.InfusionEnchantRevealing;
import com.invadermonky.magicultureintegrations.registry.RegistrarMI;
import thaumcraft.common.tiles.devices.TileBellows;

public class InitThaumcraft extends IntegrationModule {
    public InitThaumcraft() {
        super("Thaumcraft");
    }

    @Override
    public void buildModIntegrations() {
    }

    @Override
    public void preInit() {
        if (MIConfigAdditions.thaumcraft.enableRevealingInfusion) {
            RegistrarMI.registerAddition(new InfusionEnchantRevealing());
        }
    }

    @Override
    public void postInit() {
        MIConfigIntegrations.thaumcraft.bellowsHeatables.registerHeatableBlacklists(TileBellows.class);
    }
}
