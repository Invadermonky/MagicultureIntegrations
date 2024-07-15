package com.invadermonky.magicultureintegrations.integrations.botania;

import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.mods.botania.IBotIntegration;
import com.invadermonky.magicultureintegrations.events.WorldEventHandler;
import com.invadermonky.magicultureintegrations.integrations.botania.events.BotExoflameHandler;
import com.invadermonky.magicultureintegrations.integrations.botania.mods.BotCookingForBlockheads;
import com.invadermonky.magicultureintegrations.integrations.botania.mods.BotFutureMC;
import com.invadermonky.magicultureintegrations.integrations.botania.mods.BotMysticalAgriculture;
import com.invadermonky.magicultureintegrations.integrations.botania.mods.BotRustic;
import com.invadermonky.magicultureintegrations.util.LogHelper;
import com.invadermonky.magicultureintegrations.util.ModIds;
import vazkii.botania.common.block.tile.TileFloatingSpecialFlower;
import vazkii.botania.common.block.tile.TileSpecialFlower;

import java.util.ArrayList;

public class InitBotania implements IModIntegration {
    public static ArrayList<IBotIntegration> botModules = new ArrayList<>();

    @Override
    public void buildModules() {
        loadModModule(ModIds.cooking_for_blockheads, BotCookingForBlockheads.class);
        loadModModule(ModIds.futuremc, BotFutureMC.class);
        loadModModule(ModIds.mystical_agriculture, BotMysticalAgriculture.class);
        loadModModule(ModIds.rustic, BotRustic.class);
    }

    @Override
    public void preInit() {}

    @Override
    public void init() {
        botModules.forEach(IBotIntegration::registerExoflameHandler);
        if(BotExoflameHandler.exoflameHeatableMap.size() > 0) {
            WorldEventHandler.tileTickMap.put(TileSpecialFlower.class, new BotExoflameHandler());
            WorldEventHandler.tileTickMap.put(TileFloatingSpecialFlower.class, new BotExoflameHandler());
        }
    }

    @Override
    public void postInit() {}

    private void loadModModule(ModIds mod, Class<? extends IBotIntegration> moduleClass) {
        final String modName = "Botania";
        try {
            if(mod.isLoaded) {
                botModules.add(moduleClass.newInstance());
                LogHelper.info("Loaded " + modName + " integration module: " + mod.modId);
            }
        } catch (Exception e) {
            LogHelper.error("Failed to load " + modName + " integration module: " + mod.modId);
        }
    }
}
