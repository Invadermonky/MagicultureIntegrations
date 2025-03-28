package com.invadermonky.magicultureintegrations.integrations.bloodmagic;

import WayofTime.bloodmagic.alchemyArray.AlchemyArrayEffectFurnaceFuel;
import WayofTime.bloodmagic.compat.guideapi.GuideBloodMagic;
import WayofTime.bloodmagic.ritual.harvest.HarvestRegistry;
import amerifrance.guideapi.api.IPage;
import amerifrance.guideapi.api.impl.Book;
import amerifrance.guideapi.api.impl.abstraction.CategoryAbstract;
import amerifrance.guideapi.api.impl.abstraction.EntryAbstract;
import amerifrance.guideapi.page.PageText;
import com.invadermonky.magicultureintegrations.api.mods.IIntegrationModule;
import com.invadermonky.magicultureintegrations.api.mods.bloodmagic.BloodMagicUtils;
import com.invadermonky.magicultureintegrations.config.MIConfigIntegrations;
import com.invadermonky.magicultureintegrations.integrations.bloodmagic.mods.*;
import com.invadermonky.magicultureintegrations.util.IntegrationList;
import com.invadermonky.magicultureintegrations.util.ModIds;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public class InitBloodMagic implements IIntegrationModule {
    private final IntegrationList integrations = new IntegrationList("Blood Magic");

    @Override
    public void buildModIntegrations() {
        this.integrations.addIntegration(ModIds.bewitchment, BMBewitchment.class);
        this.integrations.addIntegration(ModIds.immersive_engineering, BMImmersiveEngineering.class);
        this.integrations.addIntegration(ModIds.mystical_agriculture, BMMysticalAgriculture.class);
        this.integrations.addIntegration(ModIds.mystical_world, BMMysticalWorld.class);
        this.integrations.addIntegration(ModIds.quality_tools, BMQualityTools.class);
        this.integrations.addIntegration(ModIds.roots, BMRoots.class);
        this.integrations.addIntegration(ModIds.rustic, BMRustic.class);
        this.integrations.addIntegration(ModIds.thaumic_additions, BMThaumicAdditions.class);
    }

    @Override
    public @NotNull IntegrationList getModIntegrations() {
        return this.integrations;
    }

    @Override
    public void init() {
        HarvestRegistry.registerHandler(new BMHarvestableCrop());
    }

    @Override
    public void postInit() {
        MIConfigIntegrations.blood_magic.furnace_array.registerHeatableBlacklists(AlchemyArrayEffectFurnaceFuel.class);
        buildGuideEntries();
    }

    private static void buildGuideEntries() {
        BloodMagicUtils.getGuideAdditions().forEach((category, entries) -> {
            entries.forEach((loc, entry) -> {
                for(IPage page : entry.pageList) {
                    ((PageText) page).setUnicodeFlag(true);
                }
            });
        });

        Book guideBook = GuideBloodMagic.GUIDE_BOOK;
        List<CategoryAbstract> categoryList = guideBook.getCategoryList();
        for(CategoryAbstract category : categoryList) {
            Map<ResourceLocation, EntryAbstract> entries = BloodMagicUtils.getGuideAdditions().get(category.name);
            if(entries != null && !entries.isEmpty()) {
                category.addEntries(entries);
            }
        }
    }
}
