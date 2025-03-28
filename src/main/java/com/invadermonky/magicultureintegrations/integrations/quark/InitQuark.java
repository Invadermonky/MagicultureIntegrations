package com.invadermonky.magicultureintegrations.integrations.quark;

import com.invadermonky.magicultureintegrations.api.mods.IIntegrationModule;
import com.invadermonky.magicultureintegrations.config.MIConfigIntegrations;
import com.invadermonky.magicultureintegrations.integrations.quark.events.QuarkEventHandler;
import com.invadermonky.magicultureintegrations.util.IntegrationList;
import net.minecraftforge.common.MinecraftForge;
import org.jetbrains.annotations.Nullable;
import vazkii.quark.world.entity.EntityFoxhound;

public class InitQuark implements IIntegrationModule {
    private final IntegrationList integrations = new IntegrationList("Quark");

    @Override
    public void buildModIntegrations() {

    }

    @Nullable
    @Override
    public IntegrationList getModIntegrations() {
        return this.integrations;
    }

    @Override
    public void preInit() {
        if(MIConfigIntegrations.quark.foxhound_heater)
            MinecraftForge.EVENT_BUS.register(new QuarkEventHandler());
    }

    @Override
    public void postInit() {
        MIConfigIntegrations.quark.foxhound_integrations.registerHeatableBlacklists(EntityFoxhound.class);
    }
}
