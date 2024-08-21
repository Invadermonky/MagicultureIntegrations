package com.invadermonky.magicultureintegrations.integrations.botania;

import com.invadermonky.magicultureintegrations.api.mods.IIntegrationModule;
import com.invadermonky.magicultureintegrations.events.CommonEventHandler;
import com.invadermonky.magicultureintegrations.init.RegistrarMI;
import com.invadermonky.magicultureintegrations.integrations.botania.events.BotExoflameHandler;
import com.invadermonky.magicultureintegrations.integrations.botania.item.ItemTemperatureRing;
import com.invadermonky.magicultureintegrations.integrations.botania.mods.BotCookingForBlockheads;
import com.invadermonky.magicultureintegrations.integrations.botania.mods.BotFutureMC;
import com.invadermonky.magicultureintegrations.integrations.botania.mods.BotMysticalAgriculture;
import com.invadermonky.magicultureintegrations.integrations.botania.mods.BotRustic;
import com.invadermonky.magicultureintegrations.util.IntegrationList;
import com.invadermonky.magicultureintegrations.util.ModIds;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.common.block.tile.TileFloatingSpecialFlower;
import vazkii.botania.common.block.tile.TileSpecialFlower;

public class InitBotania implements IIntegrationModule {
    private final IntegrationList integrations = new IntegrationList("Botania");

    @Override
    public void buildModIntegrations() {
        integrations.addIntegration(ModIds.cooking_for_blockheads, BotCookingForBlockheads.class);
        integrations.addIntegration(ModIds.futuremc, BotFutureMC.class);
        integrations.addIntegration(ModIds.mystical_agriculture, BotMysticalAgriculture.class);
        integrations.addIntegration(ModIds.rustic, BotRustic.class);
    }

    @Nullable
    @Override
    public IntegrationList getModIntegrations() {
        return this.integrations;
    }

    @Override
    public void preInit() {
        if(ModIds.simpledifficulty.isLoaded || ModIds.tough_as_nails.isLoaded) {
            RegistrarMI.registerItem(ItemTemperatureRing.TEMPERATURE_RING);
        }
    }

    @Override
    public void init() {
        if(!BotExoflameHandler.exoflameHeatableMap.isEmpty()) {
            CommonEventHandler.registerTileTickSubscriber(TileSpecialFlower.class, new BotExoflameHandler());
            CommonEventHandler.registerTileTickSubscriber(TileFloatingSpecialFlower.class, new BotExoflameHandler());
        }
    }

    @Override
    public void postInit() {}
}
