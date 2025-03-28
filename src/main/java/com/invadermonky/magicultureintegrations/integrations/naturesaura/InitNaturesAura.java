package com.invadermonky.magicultureintegrations.integrations.naturesaura;

import com.invadermonky.magicultureintegrations.api.mods.IIntegrationModule;
import com.invadermonky.magicultureintegrations.config.MIConfigIntegrations;
import com.invadermonky.magicultureintegrations.config.MIConfigTweaks;
import com.invadermonky.magicultureintegrations.integrations.naturesaura.events.NAEventHandler;
import com.invadermonky.magicultureintegrations.util.IntegrationList;
import de.ellpeck.naturesaura.blocks.tiles.TileEntityFurnaceHeater;
import net.minecraftforge.common.MinecraftForge;
import org.jetbrains.annotations.NotNull;

public class InitNaturesAura implements IIntegrationModule {
    private final IntegrationList integrations = new IntegrationList("Nature's Aura");

    @Override
    public void buildModIntegrations() {}

    @Override
    public @NotNull IntegrationList getModIntegrations() {
        return this.integrations;
    }

    @Override
    public void preInit() {
        if(MIConfigTweaks.natures_aura.loose_birthing_spirit)
            MinecraftForge.EVENT_BUS.register(new NAEventHandler());
    }

    @Override
    public void postInit() {
        MIConfigIntegrations.natures_aura.exteral_heater_integrations.registerHeatableBlacklists(TileEntityFurnaceHeater.class);
    }
}
