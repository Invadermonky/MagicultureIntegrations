package com.invadermonky.magicultureintegrations.integrations.botania;

import com.invadermonky.magicultureintegrations.api.mods.IntegrationModule;
import com.invadermonky.magicultureintegrations.config.MIConfigIntegrations;
import com.invadermonky.magicultureintegrations.integrations.botania.block.BlockSpecialFlowerMI;
import com.invadermonky.magicultureintegrations.integrations.botania.item.ItemLensFlux;
import com.invadermonky.magicultureintegrations.registry.RegistrarMI;
import com.invadermonky.magicultureintegrations.util.ModIds;
import vazkii.botania.common.block.subtile.functional.SubTileExoflame;

public class InitBotania extends IntegrationModule {
    public InitBotania() {
        super("Botania");
    }

    @Override
    public void buildModIntegrations() {
    }

    @Override
    public void preInit() {
        if (ModIds.thaumcraft.isLoaded) {
            RegistrarMI.registerAddition(ItemLensFlux.FLUX_LENS);
            RegistrarMI.registerAddition(BlockSpecialFlowerMI.BLOCK_SPECIAL_FLOWER);
        }
    }

    @Override
    public void postInit() {
        if (MIConfigIntegrations.botania.exoflame_mixin) {
            MIConfigIntegrations.botania.exoflame.registerHeatableBlacklists(SubTileExoflame.class);
        }
    }
}
