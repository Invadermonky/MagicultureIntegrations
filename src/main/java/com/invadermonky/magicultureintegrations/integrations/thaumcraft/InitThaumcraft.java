package com.invadermonky.magicultureintegrations.integrations.thaumcraft;

import com.invadermonky.magicultureintegrations.api.mods.IAddition;
import com.invadermonky.magicultureintegrations.api.mods.IIntegrationModule;
import com.invadermonky.magicultureintegrations.events.CommonEventHandler;
import com.invadermonky.magicultureintegrations.init.RegistrarMI;
import com.invadermonky.magicultureintegrations.integrations.thaumcraft.compat.*;
import com.invadermonky.magicultureintegrations.integrations.thaumcraft.events.TCBellowsHandler;
import com.invadermonky.magicultureintegrations.integrations.thaumcraft.item.ItemThaumicRegulator;
import com.invadermonky.magicultureintegrations.util.IntegrationList;
import com.invadermonky.magicultureintegrations.util.ModIds;
import thaumcraft.common.tiles.devices.TileBellows;

import java.util.ArrayList;
import java.util.List;

public class InitThaumcraft implements IIntegrationModule {
    private final IntegrationList integrations = new IntegrationList("Thaumcraft");
    private final List<IAddition> additions = new ArrayList<>();

    @Override
    public void buildModIntegrations() {
        integrations.addIntegration(ModIds.bewitchment, TCBewitchment.class);
        integrations.addIntegration(ModIds.cooking_for_blockheads, TCCookingForBlockheads.class);
        integrations.addIntegration(ModIds.futuremc, TCFutureMC.class);
        integrations.addIntegration(ModIds.mystical_agriculture, TCMysticalAgriculture.class);
        integrations.addIntegration(ModIds.rustic, TCRustic.class);

        if(ModIds.simpledifficulty.isLoaded || ModIds.tough_as_nails.isLoaded) {
            additions.add(ItemThaumicRegulator.THAUMIC_REGULATOR);
        }
    }

    @Override
    public IntegrationList getModIntegrations() {
        return this.integrations;
    }

    @Override
    public void preInit() {
        if(ModIds.simpledifficulty.isLoaded || ModIds.tough_as_nails.isLoaded) {
            RegistrarMI.registerItem(ItemThaumicRegulator.THAUMIC_REGULATOR);
        }
    }

    @Override
    public void init() {
        if(!TCBellowsHandler.tcHeatableMap.isEmpty()) {
            CommonEventHandler.registerTileTickSubscriber(TileBellows.class, new TCBellowsHandler());
        }
    }

    @Override
    public void postInit() {
        additions.forEach(addition -> {
            if(addition.isEnabled())
                addition.registerRecipe();
        });
    }
}
