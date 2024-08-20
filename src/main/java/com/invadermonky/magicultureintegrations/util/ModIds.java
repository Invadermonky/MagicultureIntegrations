package com.invadermonky.magicultureintegrations.util;

import WayofTime.bloodmagic.BloodMagic;
import baubles.common.Baubles;
import blusunrize.immersiveengineering.ImmersiveEngineering;
import com.bewitchment.Bewitchment;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.charles445.simpledifficulty.SimpleDifficulty;
import com.pam.harvestcraft.Reference;
import com.tmtravlr.qualitytools.QualityToolsMod;
import de.ellpeck.naturesaura.NaturesAura;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.roots.Roots;
import net.blay09.mods.cookingforblockheads.CookingForBlockheads;
import rustic.core.Rustic;
import shadows.attained.AttainedDrops2;
import teamroots.embers.Embers;
import thaumcraft.Thaumcraft;
import thedarkcolour.futuremc.FutureMC;
import toughasnails.core.ToughAsNails;
import vazkii.botania.common.lib.LibMisc;
import vazkii.patchouli.common.base.Patchouli;

import javax.annotation.Nullable;

public enum ModIds {
    agricraft(ConstIds.agricraft),
    attained_drops(ConstIds.attained_drops),
    baubles(ConstIds.baubles),
    bewitchment(ConstIds.bewitchment),
    bloodmagic(ConstIds.bloodmagic),
    botania(ConstIds.botania),
    cooking_for_blockheads(ConstIds.cooking_for_blockheads),
    embers(ConstIds.embers),
    futuremc(ConstIds.futuremc),
    harvestcraft(ConstIds.harvestcraft),
    immersive_engineering(ConstIds.immersive_engineering),
    mystical_agriculture(ConstIds.mystical_agriculture),
    mystical_world(ConstIds.mystical_world),
    natures_aura(ConstIds.natures_aura),
    patchouli(ConstIds.patchouli),
    quality_tools(ConstIds.quality_tools),
    roots(ConstIds.roots),
    rustic(ConstIds.rustic),
    simpledifficulty(ConstIds.simpledifficulty),
    thaumcraft(ConstIds.thaumcraft),
    tough_as_nails(ConstIds.toughasnails),
    ;

    public final String modId;
    public final String version;
    public final boolean isLoaded;

    ModIds(String modId) {
        this(modId, null);
    }

    ModIds(String modId, @Nullable String version) {
        this.modId = modId;
        this.version = version;
        this.isLoaded = ModHelper.isModLoaded(modId, version);
    }

    ModIds(String modId, @Nullable String version, boolean isMinVersion, boolean isMaxVersion) {
        this.modId = modId;
        this.version = version;
        this.isLoaded = ModHelper.isModLoaded(modId, version, isMinVersion, isMaxVersion);
    }

    @Override
    public String toString() {
        return this.modId;
    }


    public static class ConstIds {
        public static final String agricraft = "agricraft";
        public static final String attained_drops = AttainedDrops2.MODID;
        public static final String baubles = Baubles.MODID;
        public static final String bewitchment = Bewitchment.MODID;
        public static final String bloodmagic = BloodMagic.MODID;
        public static final String botania = LibMisc.MOD_ID;
        public static final String cooking_for_blockheads = CookingForBlockheads.MOD_ID;
        public static final String embers = Embers.MODID;
        public static final String futuremc = FutureMC.ID;
        public static final String harvestcraft = Reference.MODID;
        public static final String immersive_engineering = ImmersiveEngineering.MODID;
        public static final String mystical_agriculture = MysticalAgriculture.MOD_ID;
        public static final String mystical_world = MysticalWorld.MODID;
        public static final String natures_aura = NaturesAura.MOD_ID;
        public static final String patchouli = Patchouli.MOD_ID;
        public static final String quality_tools = QualityToolsMod.MOD_ID;
        public static final String roots = Roots.MODID;
        public static final String rustic = Rustic.MODID;
        public static final String simpledifficulty = SimpleDifficulty.MODID;
        public static final String thaumcraft = Thaumcraft.MODID;
        public static final String toughasnails = ToughAsNails.MOD_ID;
    }

    public static class ConstVersions {
    }
}
