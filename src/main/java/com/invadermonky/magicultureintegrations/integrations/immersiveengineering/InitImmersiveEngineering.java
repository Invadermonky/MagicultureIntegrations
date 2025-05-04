package com.invadermonky.magicultureintegrations.integrations.immersiveengineering;

import com.invadermonky.magicultureintegrations.api.mods.IntegrationModule;
import com.invadermonky.magicultureintegrations.integrations.immersiveengineering.mods.*;
import com.invadermonky.magicultureintegrations.util.ModIds;

public class InitImmersiveEngineering extends IntegrationModule {
    public InitImmersiveEngineering() {
        super("Immersive Engineering");
    }

    @Override
    public void buildModIntegrations() {
        integrations.addIntegration(ModIds.bewitchment, IEBewitchment.class);
        integrations.addIntegration(ModIds.engineers_decor, IEEngineersDecor.class);
        integrations.addIntegration(ModIds.futuremc, IEFutureMC.class);
        integrations.addIntegration(ModIds.industrial_craft, IEIndustrialCraft.class);
        integrations.addIntegration(ModIds.mystical_agriculture, IEMysticalAgriculture.class);
        integrations.addIntegration(ModIds.rustic, IERustic.class);
        integrations.addIntegration(ModIds.thaumcraft, IEThaumcraft.class);
        integrations.addIntegration(ModIds.thaumic_additions, IEThaumicAdditions.class);
        integrations.addIntegration(ModIds.tinkers_construct, IETinkersConstruct.class);
    }
}
