package com.invadermonky.magicultureintegrations.core;

import com.google.common.collect.ImmutableMap;
import com.invadermonky.magicultureintegrations.config.MIConfigFixes;
import com.invadermonky.magicultureintegrations.config.MIConfigIntegrations;
import com.invadermonky.magicultureintegrations.config.MIConfigTweaks;
import com.invadermonky.magicultureintegrations.util.ModIds;
import com.invadermonky.magicultureintegrations.util.StringHelper;
import zone.rong.mixinbooter.ILateMixinLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BooleanSupplier;

public class ModMixinsMI implements ILateMixinLoader {
    private static final Map<String, BooleanSupplier> mixinConfigs = ImmutableMap.copyOf(new HashMap<String, BooleanSupplier>() {{
        put(StringHelper.getMixinString(ModIds.agricraft, "enderioplugin", "fix"), () -> ModIds.agricraft.isLoaded && ModIds.enderio.isLoaded && !ModIds.universal_tweaks.isLoaded && MIConfigFixes.agricraft.fixEnderioPlugin);
        put(StringHelper.getMixinString(ModIds.agricraft, "blockcrop", "harvestable"), () -> ModIds.agricraft.isLoaded && MIConfigIntegrations.agricraft.harvestable_mixin);
        put(StringHelper.getMixinString(ModIds.attained_drops, "blockplant", "harvestable"), () -> ModIds.attained_drops.isLoaded && MIConfigIntegrations.attained_drops.harvestable_mixin);
        put(StringHelper.getMixinString(ModIds.attained_drops, "blockplant", "hornharvestable"), () -> ModIds.attained_drops.isLoaded && ModIds.botania.isLoaded && MIConfigIntegrations.botania.wild_horn.attained_drops);
        put(StringHelper.getMixinString(ModIds.bewitchment, "oven", "fix"), () -> ModIds.bewitchment.isLoaded && !ModIds.universal_tweaks.isLoaded && MIConfigFixes.bewitchment.fixWitchesOven);
        put(StringHelper.getMixinString(ModIds.bewitchment, "oven", "heatable"), () -> ModIds.bewitchment.isLoaded && MIConfigIntegrations.bewitchment.heatable_oven);
        put(StringHelper.getMixinString(ModIds.bloodmagic, "burningfurnacearray"), () -> ModIds.bloodmagic.isLoaded && MIConfigIntegrations.blood_magic.furnace_array_mixins);
        put(StringHelper.getMixinString(ModIds.bloodmagic, "boundtool"), () -> ModIds.bloodmagic.isLoaded && !ModIds.universal_tweaks.isLoaded && MIConfigTweaks.blood_magic.boundToolTweak);
        put(StringHelper.getMixinString(ModIds.bloodmagic, "cuttingfluid"), () -> ModIds.bloodmagic.isLoaded && MIConfigTweaks.blood_magic.cuttingFluidTweak);
        put(StringHelper.getMixinString(ModIds.bloodmagic, "ritual"), () -> ModIds.bloodmagic.isLoaded && !ModIds.universal_tweaks.isLoaded && MIConfigFixes.blood_magic.fixRitual);
        put(StringHelper.getMixinString(ModIds.bloodmagic, "ritualcrusher"), () -> ModIds.bloodmagic.isLoaded && MIConfigTweaks.blood_magic.ritualCrusherTweak);
        put(StringHelper.getMixinString(ModIds.bloodmagic, "ritualharvest"), () -> ModIds.bloodmagic.isLoaded && MIConfigTweaks.blood_magic.ritualHarvestTweak);
        put(StringHelper.getMixinString(ModIds.bloodmagic, "ritualmagnetic", "tweak"), () -> ModIds.bloodmagic.isLoaded && MIConfigTweaks.blood_magic.ritualMagneticTweak);
        put(StringHelper.getMixinString(ModIds.bloodmagic, "ritualmagnetic", ModIds.ore_stages.modId), () -> ModIds.bloodmagic.isLoaded && ModIds.ore_stages.isLoaded && MIConfigIntegrations.blood_magic.ore_stages_magnetism);
        put(StringHelper.getMixinString(ModIds.bloodmagic, "fluidrouting"), () -> ModIds.bloodmagic.isLoaded && !ModIds.universal_tweaks.isLoaded && MIConfigFixes.blood_magic.fixFluidRouting);
        put(StringHelper.getMixinString(ModIds.botania, "exoflame"), () -> ModIds.botania.isLoaded && MIConfigIntegrations.botania.exoflameMixin);
        put(StringHelper.getMixinString(ModIds.botania, "kekimurus"), () -> ModIds.botania.isLoaded && MIConfigIntegrations.botania.kekimurus_mixin);
        put(StringHelper.getMixinString(ModIds.cooking_for_blockheads, "oven", "fix"), () -> ModIds.cooking_for_blockheads.isLoaded && !ModIds.universal_tweaks.isLoaded && MIConfigFixes.cooking_for_blockheads.fixOven);
        put(StringHelper.getMixinString(ModIds.cooking_for_blockheads, "oven", "heatable"), () -> ModIds.cooking_for_blockheads.isLoaded && MIConfigIntegrations.cooking_for_blockheads.heatableOven);
        put(StringHelper.getMixinString(ModIds.cyclic, "harvestexpansion"), () -> ModIds.cyclic.isLoaded && MIConfigIntegrations.cyclic.harvesterExpansion);
        put(StringHelper.getMixinString(ModIds.engineers_decor, "decorfurnace", "heatable"), () -> ModIds.engineers_decor.isLoaded && MIConfigIntegrations.engineers_decor.heatableFurnace);
        put(StringHelper.getMixinString(ModIds.futuremc, "advancedfurnace", "heatable"), () -> ModIds.futuremc.isLoaded && MIConfigIntegrations.future_mc.heatableAdvancedFurnaces);
        put(StringHelper.getMixinString(ModIds.harvestcraft, "blockpam", "harvestable"), () -> ModIds.harvestcraft.isLoaded && MIConfigIntegrations.harvestcraft.harvestable_fruit);
        put(StringHelper.getMixinString(ModIds.harvestcraft, "blockpam", "hornharvestable"), () -> ModIds.harvestcraft.isLoaded && ModIds.botania.isLoaded && MIConfigIntegrations.botania.wild_horn.harvestcraft);
        put(StringHelper.getMixinString(ModIds.harvestcraft, "rightclickharvesting", "tweak"), () -> ModIds.harvestcraft.isLoaded && MIConfigFixes.harvestcraft.fixBarkHarvest);
        put(StringHelper.getMixinString(ModIds.immersive_engineering, "alloysmelter", "boostable"), () -> ModIds.immersive_engineering.isLoaded && MIConfigIntegrations.immersive_engineering.boostableAlloySmelter);
        put(StringHelper.getMixinString(ModIds.immersive_engineering, "blastfurnace", "boostable"), () -> ModIds.immersive_engineering.isLoaded && MIConfigIntegrations.immersive_engineering.boostableBlastFurnace);
        put(StringHelper.getMixinString(ModIds.immersive_engineering, "cokeoven", "boostable"), () -> ModIds.immersive_engineering.isLoaded && MIConfigIntegrations.immersive_engineering.boostableCokeOven);
        put(StringHelper.getMixinString(ModIds.immersive_engineering, "hemp", "harvestable"), () -> ModIds.immersive_engineering.isLoaded && MIConfigIntegrations.immersive_engineering.harvestableMixin);
        put(StringHelper.getMixinString(ModIds.industrial_craft, "blastfurnace", "boostable"), () -> ModIds.industrial_craft.isLoaded && MIConfigIntegrations.industrial_craft.boostableBlastFurnace);
        put(StringHelper.getMixinString(ModIds.industrial_craft, "cokekiln", "boostable"), () -> ModIds.industrial_craft.isLoaded && MIConfigIntegrations.industrial_craft.boostableCokeKiLn);
        put(StringHelper.getMixinString(ModIds.industrial_craft, "crop", "harvestable"), () -> ModIds.industrial_craft.isLoaded && MIConfigIntegrations.industrial_craft.harvestableCrops);
        put(StringHelper.getMixinString(ModIds.industrial_craft, "crop", "hornharvestable"), () -> ModIds.industrial_craft.isLoaded && ModIds.botania.isLoaded && MIConfigIntegrations.botania.wild_horn.industrialcraft);
        put(StringHelper.getMixinString(ModIds.industrial_craft, "crop", "growable"), () -> ModIds.industrial_craft.isLoaded && MIConfigIntegrations.industrial_craft.growableCrops);
        put(StringHelper.getMixinString(ModIds.mystical_agriculture, "heatable"), () -> ModIds.mystical_agriculture.isLoaded && MIConfigIntegrations.mystical_agriculture.heatableFurnace);
        put(StringHelper.getMixinString(ModIds.natures_aura, "externalheater"), () -> ModIds.natures_aura.isLoaded && MIConfigIntegrations.natures_aura.extraneousHeaterMixins);
        put(StringHelper.getMixinString(ModIds.new_crimson_revelations, "blockmanapod", "harvestable"), () -> ModIds.new_crimson_revelations.isLoaded && MIConfigIntegrations.new_crimson_revelations.harvestableManaPods);
        put(StringHelper.getMixinString(ModIds.new_crimson_revelations, "blockmanapod", "hornharvestable"), () -> ModIds.new_crimson_revelations.isLoaded && ModIds.botania.isLoaded && MIConfigIntegrations.botania.wild_horn.newcrimsonrevelations);
        put(StringHelper.getMixinString(ModIds.oreberries, "blockoreberrybush", "harvestable"), () -> ModIds.oreberries.isLoaded && MIConfigIntegrations.oreberries.harvestableOreberries);
        put(StringHelper.getMixinString(ModIds.oreberries, "blockoreberrybush", "hornharvestable"), () -> ModIds.oreberries.isLoaded && ModIds.botania.isLoaded && MIConfigIntegrations.botania.wild_horn.oreberries);
        put(StringHelper.getMixinString(ModIds.rats, "ratharvestexpansion"), () -> ModIds.rats.isLoaded && MIConfigIntegrations.rats.ratHarvestExpansion);
        put(StringHelper.getMixinString(ModIds.roots, "elementalsoil"), () -> ModIds.roots.isLoaded && MIConfigIntegrations.roots.elementalSoilMixins);
        put(StringHelper.getMixinString(ModIds.rustic, "condenser", "heatable"), () -> ModIds.rustic.isLoaded && MIConfigIntegrations.rustic.heatableCondenser);
        put(StringHelper.getMixinString(ModIds.rustic, "blockherb", "harvestable"), () -> ModIds.rustic.isLoaded && MIConfigIntegrations.rustic.harvestableHerbs);
        put(StringHelper.getMixinString(ModIds.thaumcraft, "bellows"), () -> ModIds.thaumcraft.isLoaded && MIConfigIntegrations.thaumcraft.bellowsMixins);
        put(StringHelper.getMixinString(ModIds.thaumcraft, "golemharvestexpansion"), () -> ModIds.thaumcraft.isLoaded && MIConfigIntegrations.thaumcraft.golemHarvestExpansion);
        put(StringHelper.getMixinString(ModIds.thaumcraft, "smelter", "heatable"), () -> ModIds.thaumcraft.isLoaded && MIConfigIntegrations.thaumcraft.essenceFurnaceMixins);
        put(StringHelper.getMixinString(ModIds.thaumic_additions, "smelter", "heatable"), () -> ModIds.thaumic_additions.isLoaded && MIConfigIntegrations.thaumic_additions.heatableEssenceSmelter);
        put(StringHelper.getMixinString(ModIds.tinkers_construct, "smeltery", "boostable"), () -> ModIds.tinkers_construct.isLoaded && MIConfigIntegrations.tinkers_construct.boostableSmeltery);
        put(StringHelper.getMixinString(ModIds.twilight_forest, "experiment115", "kekimurus"), () -> ModIds.twilight_forest.isLoaded && ModIds.botania.isLoaded && MIConfigIntegrations.botania.kekimurus.experiment115);
    }});

    @Override
    public List<String> getMixinConfigs() {
        return new ArrayList<>(mixinConfigs.keySet());
    }

    @Override
    public boolean shouldMixinConfigQueue(String mixinConfig) {
        BooleanSupplier supplier = mixinConfigs.get(mixinConfig);
        return supplier == null || supplier.getAsBoolean();
    }
}
