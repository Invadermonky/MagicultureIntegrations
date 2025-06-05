package com.invadermonky.magicultureintegrations.util;

import javax.annotation.Nullable;

public enum ModIds {
    actually_additions(ConstIds.actually_additions),
    agricraft(ConstIds.agricraft),
    animania(ConstIds.animania),
    astral_sorcery(ConstIds.astral_sorcery),
    attained_drops(ConstIds.attained_drops),
    baubles(ConstIds.baubles),
    bewitchment(ConstIds.bewitchment),
    bloodmagic(ConstIds.bloodmagic),
    botania(ConstIds.botania),
    cooking_for_blockheads(ConstIds.cooking_for_blockheads),
    cyclic(ConstIds.cyclic),
    embers(ConstIds.embers),
    enderio(ConstIds.enderio),
    engineers_decor(ConstIds.engineers_decor),
    futuremc(ConstIds.futuremc),
    harvestcraft(ConstIds.harvestcraft),
    immersive_engineering(ConstIds.immersive_engineering),
    industrial_craft(ConstIds.industrialcraft),
    industrial_foregoing(ConstIds.industrial_foregoing),
    mystical_agriculture(ConstIds.mystical_agriculture),
    mystical_world(ConstIds.mystical_world),
    natures_aura(ConstIds.natures_aura),
    new_crimson_revelations(ConstIds.new_crimson_revelations, ConstVersions.new_crimson_revelations, true, false),
    nutrition(ConstIds.nutrition),
    oreberries(ConstIds.oreberries),
    ore_stages(ConstIds.ore_stages),
    patchouli(ConstIds.patchouli),
    quality_tools(ConstIds.quality_tools),
    quark(ConstIds.quark),
    rats(ConstIds.rats),
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
    travelers_backpack(ConstIds.travelers_backpack),
    twilight_forest(ConstIds.twilight_forest),
    universal_tweaks(ConstIds.universal_tweaks, ConstVersions.universal_tweaks, true, false);

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
        public static final String actually_additions = "actuallyadditions";
        public static final String agricraft = "agricraft";
        public static final String animania = "animania";
        public static final String astral_sorcery = "astralsorcery";
        public static final String attained_drops = "attaineddrops2";
        public static final String baubles = "baubles";
        public static final String bewitchment = "bewitchment";
        public static final String bloodmagic = "bloodmagic";
        public static final String botania = "botania";
        public static final String cooking_for_blockheads = "cookingforblockheads";
        public static final String cyclic = "cyclicmagic";
        public static final String embers = "embers";
        public static final String enderio = "enderio";
        public static final String engineers_decor = "engineersdecor";
        public static final String futuremc = "futuremc";
        public static final String harvestcraft = "harvestcraft";
        public static final String immersive_engineering = "immersiveengineering";
        public static final String industrialcraft = "ic2";
        public static final String industrial_foregoing = "industrialforegoing";
        public static final String mystical_agriculture = "mysticalagriculture";
        public static final String mystical_world = "mysticalworld";
        public static final String natures_aura = "naturesaura";
        public static final String new_crimson_revelations = "crimsonrevelations";
        public static final String nutrition = "nutrition";
        public static final String oreberries = "oreberries";
        public static final String ore_stages = "orestages";
        public static final String patchouli = "patchouli";
        public static final String quality_tools = "qualitytools";
        public static final String quark = "quark";
        public static final String rats = "rats";
        public static final String redstone_paste = "redstonepaste";
        public static final String roots = "roots";
        public static final String rustic = "rustic";
        public static final String simpledifficulty = "simpledifficulty";
        public static final String spartan_weaponry = "spartanweaponry";
        public static final String thaumcraft = "thaumcraft";
        public static final String thaumic_additions = "thaumadditions";
        public static final String the_one_probe = "theoneprobe";
        public static final String tinkers_construct = "tconstruct";
        public static final String tinkers_complement = "tcomplement";
        public static final String toughasnails = "toughasnails";
        public static final String travelers_backpack = "travelersbackpack";
        public static final String twilight_forest = "twilightforest";
        public static final String universal_tweaks = "universaltweaks";
    }

    public static class ConstVersions {
        public static final String new_crimson_revelations = "1.3.2";
        public static final String universal_tweaks = "1.15.0";
    }
}
