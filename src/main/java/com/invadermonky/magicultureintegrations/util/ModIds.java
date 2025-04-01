package com.invadermonky.magicultureintegrations.util;

import WayofTime.bloodmagic.BloodMagic;
import baubles.common.Baubles;
import blusunrize.immersiveengineering.ImmersiveEngineering;
import com.bewitchment.Bewitchment;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.charles445.simpledifficulty.SimpleDifficulty;
import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.pam.harvestcraft.Reference;
import com.tmtravlr.qualitytools.QualityToolsMod;
import crazypants.enderio.base.EnderIO;
import de.ellpeck.naturesaura.NaturesAura;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.roots.Roots;
import hellfirepvp.astralsorcery.AstralSorcery;
import ic2.core.IC2;
import josephcsible.oreberries.OreberriesMod;
import knightminer.tcomplement.TinkersComplement;
import mcjty.theoneprobe.TheOneProbe;
import net.blay09.mods.cookingforblockheads.CookingForBlockheads;
import org.zeith.thaumicadditions.InfoTAR;
import rustic.core.Rustic;
import shadows.attained.AttainedDrops2;
import slimeknights.tconstruct.TConstruct;
import teamroots.embers.Embers;
import thaumcraft.Thaumcraft;
import thedarkcolour.futuremc.FutureMC;
import toughasnails.core.ToughAsNails;
import twilightforest.TwilightForestMod;
import vazkii.botania.common.lib.LibMisc;
import vazkii.patchouli.common.base.Patchouli;
import wile.engineersdecor.ModEngineersDecor;

import javax.annotation.Nullable;

public enum ModIds {
    agricraft(ConstIds.agricraft),
    astral_sorcery(ConstIds.astral_sorcery),
    attained_drops(ConstIds.attained_drops),
    baubles(ConstIds.baubles),
    bewitchment(ConstIds.bewitchment),
    bloodmagic(ConstIds.bloodmagic),
    botania(ConstIds.botania),
    cooking_for_blockheads(ConstIds.cooking_for_blockheads),
    embers(ConstIds.embers),
    enderio(ConstIds.enderio),
    engineers_decor(ConstIds.engineers_decor),
    futuremc(ConstIds.futuremc),
    harvestcraft(ConstIds.harvestcraft),
    immersive_engineering(ConstIds.immersive_engineering),
    industrial_craft(ConstIds.industrialcraft),
    mystical_agriculture(ConstIds.mystical_agriculture),
    mystical_world(ConstIds.mystical_world),
    natures_aura(ConstIds.natures_aura),
    oreberries(ConstIds.oreberries),
    ore_stages(ConstIds.ore_stages),
    patchouli(ConstIds.patchouli),
    quality_tools(ConstIds.quality_tools),
    quark(ConstIds.quark),
    redstone_paste(ConstIds.redstone_paste),
    roots(ConstIds.roots),
    rustic(ConstIds.rustic),
    spartan_weaponry(ConstIds.spartan_weaponry),
    simpledifficulty(ConstIds.simpledifficulty),
    thaumcraft(ConstIds.thaumcraft),
    thaumic_additions(ConstIds.thaumic_additions),
    the_one_probe(ConstIds.the_one_probe),
    tinkers_construct(ConstIds.tinkers_construct),
    tinkers_complement(ConstIds.tinkers_complement),
    tough_as_nails(ConstIds.toughasnails),
    twilight_forest(ConstIds.twilight_forest),
    universal_tweaks(ConstIds.universal_tweaks, ConstVersions.universal_tweaks, true, false)
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
        public static final String astral_sorcery = AstralSorcery.MODID;
        public static final String attained_drops = AttainedDrops2.MODID;
        public static final String baubles = Baubles.MODID;
        public static final String bewitchment = Bewitchment.MODID;
        public static final String bloodmagic = BloodMagic.MODID;
        public static final String botania = LibMisc.MOD_ID;
        public static final String cooking_for_blockheads = CookingForBlockheads.MOD_ID;
        public static final String embers = Embers.MODID;
        public static final String enderio = EnderIO.MODID;
        public static final String engineers_decor = ModEngineersDecor.MODID;
        public static final String futuremc = FutureMC.ID;
        public static final String harvestcraft = Reference.MODID;
        public static final String immersive_engineering = ImmersiveEngineering.MODID;
        public static final String industrialcraft = IC2.MODID;
        public static final String mystical_agriculture = MysticalAgriculture.MOD_ID;
        public static final String mystical_world = MysticalWorld.MODID;
        public static final String natures_aura = NaturesAura.MOD_ID;
        public static final String oreberries = OreberriesMod.MODID;
        public static final String ore_stages = "orestages";
        public static final String patchouli = Patchouli.MOD_ID;
        public static final String quality_tools = QualityToolsMod.MOD_ID;
        public static final String quark = vazkii.quark.base.lib.LibMisc.MOD_ID;
        public static final String redstone_paste = "redstonepaste";
        public static final String roots = Roots.MODID;
        public static final String rustic = Rustic.MODID;
        public static final String simpledifficulty = SimpleDifficulty.MODID;
        public static final String spartan_weaponry = ModSpartanWeaponry.ID;
        public static final String thaumcraft = Thaumcraft.MODID;
        public static final String thaumic_additions = InfoTAR.MOD_ID;
        public static final String the_one_probe = TheOneProbe.MODID;
        public static final String tinkers_construct = TConstruct.modID;
        public static final String tinkers_complement = TinkersComplement.modID;
        public static final String toughasnails = ToughAsNails.MOD_ID;
        public static final String twilight_forest = TwilightForestMod.ID;
        public static final String universal_tweaks = "universaltweaks";
    }

    public static class ConstVersions {
        public static final String universal_tweaks = "1.15.0";
    }
}
