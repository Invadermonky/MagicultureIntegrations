package com.invadermonky.magicultureintegrations.integrations.immersiveengineering;

import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.mods.immersiveengineering.IIEIntegration;
import com.invadermonky.magicultureintegrations.integrations.immersiveengineering.mods.IEBewitchment;
import com.invadermonky.magicultureintegrations.integrations.immersiveengineering.mods.IEFutureMC;
import com.invadermonky.magicultureintegrations.integrations.immersiveengineering.mods.IEMysticalAgriculture;
import com.invadermonky.magicultureintegrations.integrations.immersiveengineering.mods.IERustic;
import com.invadermonky.magicultureintegrations.util.LogHelper;
import com.invadermonky.magicultureintegrations.util.ModIds;

import java.util.ArrayList;

public class InitImmersiveEngineering implements IModIntegration {
    public static ArrayList<IIEIntegration> ieModules = new ArrayList<>();

    @Override
    public void buildModules() {
        registerModModule(ModIds.bewitchment, IEBewitchment.class);
        registerModModule(ModIds.futuremc, IEFutureMC.class);
        registerModModule(ModIds.mystical_agriculture, IEMysticalAgriculture.class);
        registerModModule(ModIds.rustic, IERustic.class);
    }

    @Override
    public void preInit() {}

    @Override
    public void init() {
        ieModules.forEach(IIEIntegration::registerExternalHeaterHandler);
    }

    @Override
    public void postInit() {}

    private void registerModModule(ModIds mod, Class<? extends IIEIntegration> moduleClass) {
        final String modName = "Immersive Engineering";
        try {
            if(mod.isLoaded) {
                ieModules.add(moduleClass.newInstance());
                LogHelper.info("Loaded " + modName + " integration module: " + mod.modId);
            }
        } catch (Exception e) {
            LogHelper.error("Failed to load " + modName + " integration module: " + mod.modId);
        }
    }
}
