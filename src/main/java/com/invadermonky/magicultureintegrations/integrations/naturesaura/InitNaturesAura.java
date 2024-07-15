package com.invadermonky.magicultureintegrations.integrations.naturesaura;

import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.mods.naturesaura.INAIntegration;
import com.invadermonky.magicultureintegrations.events.ClientEventHandler;
import com.invadermonky.magicultureintegrations.events.WorldEventHandler;
import com.invadermonky.magicultureintegrations.integrations.naturesaura.events.NAExtendedConfigHandler;
import com.invadermonky.magicultureintegrations.integrations.naturesaura.events.NAExternalHeaterHandler;
import com.invadermonky.magicultureintegrations.integrations.naturesaura.mods.*;
import com.invadermonky.magicultureintegrations.util.LogHelper;
import com.invadermonky.magicultureintegrations.util.ModIds;
import de.ellpeck.naturesaura.blocks.tiles.TileEntityFurnaceHeater;

import java.util.ArrayList;

public class InitNaturesAura implements IModIntegration {
    public static ArrayList<INAIntegration> naModules = new ArrayList<>();

    @Override
    public void buildModules() {
        loadModModule(ModIds.bewitchment, NABewitchment.class);
        loadModModule(ModIds.cooking_for_blockheads, NACookingForBlockheads.class);
        loadModModule(ModIds.futuremc, NAFutureMC.class);
        loadModModule(ModIds.mystical_agriculture, NAMysticalAgriculture.class);
        loadModModule(ModIds.rustic, NARustic.class);
    }

    @Override
    public void preInit() {}

    @Override
    public void init() {
        naModules.forEach(INAIntegration::registerExtraneousHeaterHandler);

        if(!NAExternalHeaterHandler.naHeaterHeatableMap.isEmpty()) {
            WorldEventHandler.registerTickableTile(TileEntityFurnaceHeater.class, new NAExternalHeaterHandler());
        }
        ClientEventHandler.addConfigChangedEventModule(new NAExtendedConfigHandler());
    }

    @Override
    public void postInit() {}

    private void loadModModule(ModIds mod, Class<? extends INAIntegration> moduleClass) {
        final String modName = "Nature's Aura";
        try {
            if(mod.isLoaded) {
                naModules.add(moduleClass.newInstance());
                LogHelper.info("Loaded " + modName + " integration module: " + mod.modId);
            }
        } catch (Exception e) {
            LogHelper.error("Failed to load " + modName + " integration module: " + mod.modId);
        }
    }
}
