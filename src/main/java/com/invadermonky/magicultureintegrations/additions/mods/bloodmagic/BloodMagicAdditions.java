package com.invadermonky.magicultureintegrations.additions.mods.bloodmagic;

import WayofTime.bloodmagic.compat.guideapi.GuideBloodMagic;
import WayofTime.bloodmagic.compat.guideapi.entry.EntryText;
import amerifrance.guideapi.api.IPage;
import amerifrance.guideapi.api.impl.Book;
import amerifrance.guideapi.api.impl.abstraction.CategoryAbstract;
import amerifrance.guideapi.api.impl.abstraction.EntryAbstract;
import amerifrance.guideapi.api.util.PageHelper;
import amerifrance.guideapi.api.util.TextHelper;
import amerifrance.guideapi.page.PageText;
import com.invadermonky.magicultureintegrations.additions.mods.bloodmagic.item.ItemReagent;
import com.invadermonky.magicultureintegrations.additions.mods.bloodmagic.item.ItemSigilTemperature;
import com.invadermonky.magicultureintegrations.additions.mods.bloodmagic.item.ItemSigilThirst;
import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.init.RegistrarMI;
import com.invadermonky.magicultureintegrations.util.ModIds;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BloodMagicAdditions implements IModIntegration {
    private static final Map<String,Map<ResourceLocation, EntryAbstract>> bookAdditions = new LinkedHashMap<>();

    @Override
    public void buildModules() {}

    @Override
    public void preInit() {
        if(ModIds.simpledifficulty.isLoaded || ModIds.tough_as_nails.isLoaded) {
            RegistrarMI.registerItem(ItemReagent.TEMPERATURE_REAGENT);
            RegistrarMI.registerItem(ItemReagent.THIRST_REAGENT);
            RegistrarMI.registerItem(ItemSigilTemperature.TEMPERATURE_SIGIL);
            RegistrarMI.registerItem(ItemSigilThirst.THIRST_SIGIL);
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void postInit() {
        buildGuideEntries();
    }

    private void buildGuideEntries() {
        bookAdditions.forEach((category, entries) -> {
            entries.forEach((loc, entry) -> {
                for(IPage page : entry.pageList) {
                    ((PageText) page).setUnicodeFlag(true);
                }
            });
        });

        Book guideBook = GuideBloodMagic.GUIDE_BOOK;
        List<CategoryAbstract> categoryList = guideBook.getCategoryList();
        for(CategoryAbstract category : categoryList) {
            Map<ResourceLocation, EntryAbstract> entries = bookAdditions.get(category.name);
            if(entries != null && !entries.isEmpty()) {
                category.addEntries(entries);
            }
        }
    }

    /**
     * Adds an entry to the Blood Magic Sanguine Scientiem guide.
     *
     * @param category valid categories - alchemy, architect, demon, ritual
     * @param guideEntryName The entry name. This will generate the localization key for the book text.
     */
    public static void addGuideEntry(String category, String guideEntryName) {
        String categoryName = "guide.bloodmagic.category." + category;
        String keyBase = "guide.bloodmagic.entry." + category + ".";
        List<IPage> pages = new ArrayList<>(PageHelper.pagesForLongText(TextHelper.localize(keyBase + guideEntryName + ".info"), 370));
        if(!bookAdditions.containsKey(categoryName))
            bookAdditions.put(categoryName, new LinkedHashMap<>());
        bookAdditions.get(categoryName).put(new ResourceLocation(keyBase + guideEntryName), new EntryText(pages, TextHelper.localize(keyBase + guideEntryName), true));
    }
}
