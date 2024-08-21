package com.invadermonky.magicultureintegrations.util;

import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class IntegrationList {
    private final List<IModIntegration> modIntegrations;
    private final String moduleName;

    public IntegrationList(String moduleName) {
        this.modIntegrations = new ArrayList<>();
        this.moduleName = moduleName;
    }

    public void addIntegration(ModIds owner, Class<? extends IModIntegration> integrationClass, ModIds... dependencies) {
        try {
            if(owner.isLoaded) {
                boolean toLoad = true;
                for(ModIds mod : dependencies) {
                    if(!mod.isLoaded) {
                        toLoad = false;
                        break;
                    }
                }
                if(toLoad) {
                    modIntegrations.add(integrationClass.newInstance());
                    LogHelper.info("Loaded " + moduleName + " integration module: " + owner.modId);
                }
            }
        } catch (Exception e) {
            LogHelper.error("Failed to load " + moduleName + " integration module: " + owner.modId);
        }
    }

    public List<IModIntegration> getModIntegrations() {
        return this.modIntegrations;
    }

    public void forEach(Consumer<? super IModIntegration> action) {
        modIntegrations.forEach(action);
    }
}
