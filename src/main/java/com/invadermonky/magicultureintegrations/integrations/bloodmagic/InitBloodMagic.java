package com.invadermonky.magicultureintegrations.integrations.bloodmagic;

import WayofTime.bloodmagic.tile.TileAlchemyArray;
import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.mods.bloodmagic.IBMIntegration;
import com.invadermonky.magicultureintegrations.events.WorldEventHandler;
import com.invadermonky.magicultureintegrations.integrations.bloodmagic.events.BMFurnaceArrayHandler;
import com.invadermonky.magicultureintegrations.integrations.bloodmagic.mods.*;
import com.invadermonky.magicultureintegrations.util.LogHelper;
import com.invadermonky.magicultureintegrations.util.ModIds;

import java.util.ArrayList;

public class InitBloodMagic implements IModIntegration {
    public static ArrayList<IBMIntegration> bmModules = new ArrayList<>();

    @Override
    public void buildModules() {
        loadModModule(ModIds.agricraft, BMAgricraft.class);
        loadModModule(ModIds.attained_drops, BMAttainedDrops.class);
        loadModModule(ModIds.bewitchment, BMBewitchment.class);
        loadModModule(ModIds.cooking_for_blockheads, BMCookingForBlockheads.class);
        loadModModule(ModIds.futuremc, BMFutureMC.class);
        loadModModule(ModIds.harvestcraft, BMHarvestcraft.class);
        loadModModule(ModIds.immersive_engineering, BMImmersiveEngineering.class);
        loadModModule(ModIds.mystical_agriculture, BMMysticalAgriculture.class);
        loadModModule(ModIds.mystical_world, BMMysticalWorld.class);
        loadModModule(ModIds.quality_tools, BMQualityTools.class);
        loadModModule(ModIds.roots, BMRoots.class);
        loadModModule(ModIds.rustic, BMRustic.class);
    }

    @Override
    public void preInit() {}

    @Override
    public void init() {
        bmModules.forEach(module -> {
            module.registerCropHandlers();
            module.registerFurnaceArrayHandlers();
            module.registerMiscellaneous();
        });

        if(!BMFurnaceArrayHandler.furnaceArrayHeatableMap.isEmpty()) {
            WorldEventHandler.registerTickableTile(TileAlchemyArray.class, new BMFurnaceArrayHandler());
        }
    }

    @Override
    public void postInit() {}

    private void loadModModule(ModIds mod, Class<? extends IBMIntegration> moduleClass) {
        final String modName = "Blood Magic";
        try {
            if(mod.isLoaded) {
                bmModules.add(moduleClass.newInstance());
                LogHelper.info("Loaded " + modName + " integration module: " + mod.modId);
            }
        } catch (Exception e) {
            LogHelper.error("Failed to load " + modName + " integration module: " + mod.modId);
        }
    }
}
