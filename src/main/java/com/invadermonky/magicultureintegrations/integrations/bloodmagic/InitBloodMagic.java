package com.invadermonky.magicultureintegrations.integrations.bloodmagic;

import WayofTime.bloodmagic.compat.guideapi.GuideBloodMagic;
import WayofTime.bloodmagic.tile.TileAlchemyArray;
import amerifrance.guideapi.api.IPage;
import amerifrance.guideapi.api.impl.Book;
import amerifrance.guideapi.api.impl.abstraction.CategoryAbstract;
import amerifrance.guideapi.api.impl.abstraction.EntryAbstract;
import amerifrance.guideapi.page.PageText;
import com.invadermonky.magicultureintegrations.api.mods.IAddition;
import com.invadermonky.magicultureintegrations.api.mods.IIntegrationModule;
import com.invadermonky.magicultureintegrations.api.mods.bloodmagic.BloodMagicUtils;
import com.invadermonky.magicultureintegrations.events.CommonEventHandler;
import com.invadermonky.magicultureintegrations.init.RegistrarMI;
import com.invadermonky.magicultureintegrations.integrations.bloodmagic.compat.*;
import com.invadermonky.magicultureintegrations.integrations.bloodmagic.events.BMFurnaceArrayHandler;
import com.invadermonky.magicultureintegrations.integrations.bloodmagic.item.ItemReagent;
import com.invadermonky.magicultureintegrations.integrations.bloodmagic.item.ItemSigilTemperature;
import com.invadermonky.magicultureintegrations.integrations.bloodmagic.item.ItemSigilThirst;
import com.invadermonky.magicultureintegrations.integrations.bloodmagic.ritual.RitualSoothingHearth;
import com.invadermonky.magicultureintegrations.util.IntegrationList;
import com.invadermonky.magicultureintegrations.util.ModIds;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InitBloodMagic implements IIntegrationModule {
    private final IntegrationList integrations = new IntegrationList("Blood Magic");
    private final List<IAddition> additions = new ArrayList<>();

    @Override
    public void buildModIntegrations() {
        integrations.addIntegration(ModIds.agricraft, BMAgricraft.class);
        integrations.addIntegration(ModIds.attained_drops, BMAttainedDrops.class);
        integrations.addIntegration(ModIds.bewitchment, BMBewitchment.class);
        integrations.addIntegration(ModIds.cooking_for_blockheads, BMCookingForBlockheads.class);
        integrations.addIntegration(ModIds.futuremc, BMFutureMC.class);
        integrations.addIntegration(ModIds.harvestcraft, BMHarvestcraft.class);
        integrations.addIntegration(ModIds.immersive_engineering, BMImmersiveEngineering.class);
        integrations.addIntegration(ModIds.mystical_agriculture, BMMysticalAgriculture.class);
        integrations.addIntegration(ModIds.mystical_world, BMMysticalWorld.class);
        integrations.addIntegration(ModIds.quality_tools, BMQualityTools.class);
        integrations.addIntegration(ModIds.roots, BMRoots.class);
        integrations.addIntegration(ModIds.rustic, BMRustic.class);
    }

    @Nullable
    @Override
    public IntegrationList getModIntegrations() {
        return this.integrations;
    }

    @Override
    public void preInit() {
        if(ModIds.simpledifficulty.isLoaded || ModIds.tough_as_nails.isLoaded) {
            RegistrarMI.registerItem(ItemReagent.TEMPERATURE_REAGENT);
            RegistrarMI.registerItem(ItemReagent.THIRST_REAGENT);
            RegistrarMI.registerItem(ItemSigilTemperature.TEMPERATURE_SIGIL);
            RegistrarMI.registerItem(ItemSigilThirst.THIRST_SIGIL);

            additions.add(new RitualSoothingHearth());
        }
    }

    @Override
    public void init() {
        if(!BMFurnaceArrayHandler.furnaceArrayHeatableMap.isEmpty()) {
            CommonEventHandler.registerTileTickSubscriber(TileAlchemyArray.class, new BMFurnaceArrayHandler());
        }
    }

    @Override
    public void postInit() {
        additions.forEach(addition -> {
            if(addition.isEnabled()) {
                addition.registerRecipe();
            }
        });
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
