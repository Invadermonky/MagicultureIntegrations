package com.invadermonky.magicultureintegrations.integrations.quark;

import com.invadermonky.magicultureintegrations.api.mods.IntegrationModule;
import com.invadermonky.magicultureintegrations.config.MIConfigIntegrations;
import com.invadermonky.magicultureintegrations.integrations.quark.events.QuarkEventHandler;
import net.minecraftforge.common.MinecraftForge;
import vazkii.quark.world.entity.EntityFoxhound;

public class InitQuark extends IntegrationModule {
    public InitQuark() {
        super("Quark");
    }

    @Override
    public void buildModIntegrations() {

    }

    @Override
    public void preInit() {
        if (MIConfigIntegrations.quark.foxhound_heater)
            MinecraftForge.EVENT_BUS.register(new QuarkEventHandler());
    }

    @Override
    public void postInit() {
        MIConfigIntegrations.quark.foxhound_integrations.registerHeatableBlacklists(EntityFoxhound.class);
    }
}
