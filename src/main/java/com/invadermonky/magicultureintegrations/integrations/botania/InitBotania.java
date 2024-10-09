package com.invadermonky.magicultureintegrations.integrations.botania;

import com.invadermonky.magicultureintegrations.api.mods.IAddition;
import com.invadermonky.magicultureintegrations.api.mods.IIntegrationModule;
import com.invadermonky.magicultureintegrations.integrations.botania.block.BlockSpecialFlowerMI;
import com.invadermonky.magicultureintegrations.integrations.botania.item.ItemLensFlux;
import com.invadermonky.magicultureintegrations.integrations.botania.mods.*;
import com.invadermonky.magicultureintegrations.registry.RegistrarMI;
import com.invadermonky.magicultureintegrations.util.IntegrationList;
import com.invadermonky.magicultureintegrations.util.ModIds;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class InitBotania implements IIntegrationModule {
    private final IntegrationList integrations = new IntegrationList("Botania");
    private final List<IAddition> additions = new ArrayList<>();

    @Override
    public void buildModIntegrations() {
        integrations.addIntegration(ModIds.cooking_for_blockheads, BotCookingForBlockheads.class);
        integrations.addIntegration(ModIds.engineers_decor, BotEngineersDecor.class);
        integrations.addIntegration(ModIds.futuremc, BotFutureMC.class);
        integrations.addIntegration(ModIds.immersive_engineering, BotImmersiveEngineering.class);
        integrations.addIntegration(ModIds.industrial_craft, BotIndustrialCraft.class);
        integrations.addIntegration(ModIds.mystical_agriculture, BotMysticalAgriculture.class);
        integrations.addIntegration(ModIds.rustic, BotRustic.class);
        integrations.addIntegration(ModIds.thaumcraft, BotThaumcraft.class);
        integrations.addIntegration(ModIds.thaumadditions, BotThaumicAdditions.class);
        integrations.addIntegration(ModIds.tinkers_construct, BotTinkersConstruct.class);

        if(ModIds.thaumcraft.isLoaded) {
            additions.add(ItemLensFlux.FLUX_LENS);
            additions.add(BlockSpecialFlowerMI.BLOCK_SPECIAL_FLOWER);
        }
    }

    @Nullable
    @Override
    public IntegrationList getModIntegrations() {
        return this.integrations;
    }

    @Nullable
    @Override
    public List<IAddition> getAdditions() {
        return this.additions;
    }

    @Override
    public void preInit() {
        if(ModIds.thaumcraft.isLoaded) {
            RegistrarMI.registerItem(ItemLensFlux.FLUX_LENS);
            RegistrarMI.registerBlock(BlockSpecialFlowerMI.BLOCK_SPECIAL_FLOWER, false);
        }
    }

    @Override
    public void postInit() {
        additions.forEach(addition -> {
            if(addition.isEnabled()) {
                addition.registerRecipe();
            }
        });
    }

    @Override
    public void registerCustomRenders() {
        IIntegrationModule.super.registerCustomRenders();
    }
}
