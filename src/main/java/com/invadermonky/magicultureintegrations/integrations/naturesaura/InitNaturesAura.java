package com.invadermonky.magicultureintegrations.integrations.naturesaura;

import com.invadermonky.magicultureintegrations.api.mods.IntegrationModule;
import com.invadermonky.magicultureintegrations.config.MIConfigIntegrations;
import com.invadermonky.magicultureintegrations.config.MIConfigTweaks;
import com.invadermonky.magicultureintegrations.integrations.naturesaura.events.NAEventHandler;
import de.ellpeck.naturesaura.blocks.tiles.TileEntityFurnaceHeater;
import net.minecraftforge.common.MinecraftForge;

public class InitNaturesAura extends IntegrationModule {
    public InitNaturesAura() {
        super("Nature's Aura");
    }

    @Override
    public void buildModIntegrations() {
    }

    @Override
    public void preInit() {
        if (MIConfigTweaks.natures_aura.birthingSpiritTweak)
            MinecraftForge.EVENT_BUS.register(new NAEventHandler());
    }

    @Override
    public void postInit() {
        MIConfigIntegrations.natures_aura.extraneousHeaterHeatables.registerHeatableBlacklists(TileEntityFurnaceHeater.class);
    }
}
