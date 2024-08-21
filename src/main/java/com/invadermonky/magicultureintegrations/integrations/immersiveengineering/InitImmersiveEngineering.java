package com.invadermonky.magicultureintegrations.integrations.immersiveengineering;

import com.invadermonky.magicultureintegrations.api.mods.IIntegrationModule;
import com.invadermonky.magicultureintegrations.integrations.immersiveengineering.mods.IEBewitchment;
import com.invadermonky.magicultureintegrations.integrations.immersiveengineering.mods.IEFutureMC;
import com.invadermonky.magicultureintegrations.integrations.immersiveengineering.mods.IEMysticalAgriculture;
import com.invadermonky.magicultureintegrations.integrations.immersiveengineering.mods.IERustic;
import com.invadermonky.magicultureintegrations.util.IntegrationList;
import com.invadermonky.magicultureintegrations.util.ModIds;
import org.jetbrains.annotations.Nullable;

public class InitImmersiveEngineering implements IIntegrationModule {
    private final IntegrationList integrations = new IntegrationList("Immersive Engineering");

    @Override
    public void buildModIntegrations() {
        integrations.addIntegration(ModIds.bewitchment, IEBewitchment.class);
        integrations.addIntegration(ModIds.futuremc, IEFutureMC.class);
        integrations.addIntegration(ModIds.mystical_agriculture, IEMysticalAgriculture.class);
        integrations.addIntegration(ModIds.rustic, IERustic.class);
    }

    @Nullable
    @Override
    public IntegrationList getModIntegrations() {
        return this.integrations;
    }

    @Override
    public void preInit() {}

    @Override
    public void init() {}

    @Override
    public void postInit() {}
}
