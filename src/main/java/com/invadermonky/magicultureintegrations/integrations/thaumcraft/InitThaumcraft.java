package com.invadermonky.magicultureintegrations.integrations.thaumcraft;

import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.mods.thaumcraft.ITCIntegration;
import com.invadermonky.magicultureintegrations.events.WorldEventHandler;
import com.invadermonky.magicultureintegrations.integrations.thaumcraft.events.TCBellowsHandler;
import com.invadermonky.magicultureintegrations.integrations.thaumcraft.mods.*;
import com.invadermonky.magicultureintegrations.util.LogHelper;
import com.invadermonky.magicultureintegrations.util.ModIds;
import thaumcraft.common.tiles.devices.TileBellows;

import java.util.ArrayList;

public class InitThaumcraft implements IModIntegration {
    public static ArrayList<ITCIntegration> tcModules = new ArrayList<>();

    @Override
    public void buildModules() {
        addModModule(ModIds.bewitchment, TCBewitchment.class);
        addModModule(ModIds.cooking_for_blockheads, TCCookingForBlockheads.class);
        addModModule(ModIds.futuremc, TCFutureMC.class);
        addModModule(ModIds.mystical_agriculture, TCMysticalAgriculture.class);
        addModModule(ModIds.rustic, TCRustic.class);
    }

    @Override
    public void preInit() {}

    @Override
    public void init() {
        tcModules.forEach(ITCIntegration::registerBellowsHandler);

        if(!TCBellowsHandler.tcHeatableMap.isEmpty()) {
            WorldEventHandler.registerTickableTile(TileBellows.class, new TCBellowsHandler());
        }
    }

    @Override
    public void postInit() {}

    private void addModModule(ModIds mod, Class<? extends ITCIntegration> moduleClass) {
        final String modName = "Thaumcraft";
        try {
            if(mod.isLoaded) {
                tcModules.add(moduleClass.newInstance());
                LogHelper.info("Loaded " + modName + " integration module: " + mod.modId);
            }
        } catch (Exception e) {
            LogHelper.error("Failed to load " + modName + " integration module: " + mod.modId);
        }
    }
}
