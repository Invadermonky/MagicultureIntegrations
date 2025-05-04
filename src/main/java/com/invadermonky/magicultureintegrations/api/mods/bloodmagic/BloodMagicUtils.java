package com.invadermonky.magicultureintegrations.api.mods.bloodmagic;

import WayofTime.bloodmagic.compat.guideapi.entry.EntryText;
import amerifrance.guideapi.api.IPage;
import amerifrance.guideapi.api.impl.abstraction.EntryAbstract;
import amerifrance.guideapi.api.util.PageHelper;
import amerifrance.guideapi.api.util.TextHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BloodMagicUtils {
    private static final Map<String, Map<ResourceLocation, EntryAbstract>> guideAdditions = new LinkedHashMap<>();

    @Nullable
    public static EntityPlayer getSacrificeTarget(World world, BlockPos pos, double radius) {
        AxisAlignedBB bb = (new AxisAlignedBB(pos)).grow(radius);
        List<EntityPlayer> playerList = world.getEntitiesWithinAABB(EntityPlayer.class, bb);
        for (EntityPlayer player : playerList) {
            if (!player.isDead) {
                return player;
            }
        }
        return null;
    }

    /**
     * Adds an entry to the Blood Magic Sanguine Scientiem guide.
     *
     * @param category       valid categories - alchemy, architect, demon, ritual
     * @param guideEntryName The entry name. This will generate the localization key for the book text.
     */
    public static void addGuideEntry(String category, String guideEntryName) {
        String categoryName = "guide.bloodmagic.category." + category;
        String keyBase = "guide.bloodmagic.entry." + category + ".";
        List<IPage> pages = new ArrayList<>(PageHelper.pagesForLongText(TextHelper.localize(keyBase + guideEntryName + ".info"), 370));
        if (!guideAdditions.containsKey(categoryName))
            guideAdditions.put(categoryName, new LinkedHashMap<>());
        guideAdditions.get(categoryName).put(new ResourceLocation(keyBase + guideEntryName), new EntryText(pages, TextHelper.localize(keyBase + guideEntryName), true));
    }

    public static Map<String, Map<ResourceLocation, EntryAbstract>> getGuideAdditions() {
        return guideAdditions;
    }
}
