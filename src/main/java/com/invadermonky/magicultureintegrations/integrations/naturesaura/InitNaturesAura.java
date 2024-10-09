package com.invadermonky.magicultureintegrations.integrations.naturesaura;

import com.invadermonky.magicultureintegrations.api.mods.IIntegrationModule;
import com.invadermonky.magicultureintegrations.events.CommonEventHandler;
import com.invadermonky.magicultureintegrations.integrations.naturesaura.events.NACommonEventHandler;
import com.invadermonky.magicultureintegrations.integrations.naturesaura.mods.*;
import com.invadermonky.magicultureintegrations.util.IntegrationList;
import com.invadermonky.magicultureintegrations.util.ModIds;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import org.jetbrains.annotations.Nullable;

public class InitNaturesAura implements IIntegrationModule {
    private final IntegrationList integrations = new IntegrationList("Nature's Aura");

    @Override
    public void buildModIntegrations() {
        integrations.addIntegration(ModIds.bewitchment, NABewitchment.class);
        integrations.addIntegration(ModIds.cooking_for_blockheads, NACookingForBlockheads.class);
        integrations.addIntegration(ModIds.engineers_decor, NAEngineersDecor.class);
        integrations.addIntegration(ModIds.futuremc, NAFutureMC.class);
        integrations.addIntegration(ModIds.immersive_engineering, NAImmersiveEngineering.class);
        integrations.addIntegration(ModIds.industrial_craft, NAIndustrialCraft.class);
        integrations.addIntegration(ModIds.mystical_agriculture, NAMysticalAgriculture.class);
        integrations.addIntegration(ModIds.rustic, NARustic.class);
        integrations.addIntegration(ModIds.thaumcraft, NAThaumcraft.class);
        integrations.addIntegration(ModIds.thaumadditions, NAThaumicAdditions.class);
        integrations.addIntegration(ModIds.tinkers_construct, NATinkersConstruct.class);
    }

    @Nullable
    @Override
    public IntegrationList getModIntegrations() {
        return this.integrations;
    }

    @Override
    public void init() {
        CommonEventHandler.registerEventSubscriber(BabyEntitySpawnEvent.class, new NACommonEventHandler());
    }
}
