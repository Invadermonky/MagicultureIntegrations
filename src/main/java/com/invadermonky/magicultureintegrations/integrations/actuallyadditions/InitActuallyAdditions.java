package com.invadermonky.magicultureintegrations.integrations.actuallyadditions;

import com.invadermonky.magicultureintegrations.api.mods.IntegrationModule;
import com.invadermonky.magicultureintegrations.integrations.actuallyadditions.mods.AAHarvestableCrop;
import de.ellpeck.actuallyadditions.api.ActuallyAdditionsAPI;

public class InitActuallyAdditions extends IntegrationModule {
    public InitActuallyAdditions() {
        super("Actually Additions");
    }

    @Override
    public void buildModIntegrations() {

    }

    @Override
    public void init() {
        ActuallyAdditionsAPI.addFarmerBehavior(new AAHarvestableCrop());
    }
}
