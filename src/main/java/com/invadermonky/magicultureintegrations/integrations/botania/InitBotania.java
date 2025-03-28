package com.invadermonky.magicultureintegrations.integrations.botania;

import com.invadermonky.magicultureintegrations.api.mods.IIntegrationModule;
import com.invadermonky.magicultureintegrations.config.MIConfigIntegrations;
import com.invadermonky.magicultureintegrations.integrations.botania.block.BlockSpecialFlowerMI;
import com.invadermonky.magicultureintegrations.integrations.botania.item.ItemLensFlux;
import com.invadermonky.magicultureintegrations.registry.RegistrarMI;
import com.invadermonky.magicultureintegrations.util.IntegrationList;
import com.invadermonky.magicultureintegrations.util.ModIds;
import org.jetbrains.annotations.NotNull;
import vazkii.botania.common.block.subtile.functional.SubTileExoflame;

public class InitBotania implements IIntegrationModule {
    private final IntegrationList integrations = new IntegrationList("Botania");

    @Override
    public void buildModIntegrations() {}

    @Override
    public @NotNull IntegrationList getModIntegrations() {
        return this.integrations;
    }

    @Override
    public void preInit() {
        if(ModIds.thaumcraft.isLoaded) {
            RegistrarMI.registerAddition(ItemLensFlux.FLUX_LENS);
            RegistrarMI.registerAddition(BlockSpecialFlowerMI.BLOCK_SPECIAL_FLOWER);
        }
    }

    @Override
    public void postInit() {
        if(MIConfigIntegrations.botania.exoflame_mixin) {
            MIConfigIntegrations.botania.exoflame.registerHeatableBlacklists(SubTileExoflame.class);
        }
    }
}
