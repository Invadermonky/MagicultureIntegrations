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
        put(StringHelper.getMixinString(ModIds.agricraft, "enderioplugin", "fix"), () -> ModIds.agricraft.isLoaded && ModIds.enderio.isLoaded && MIConfigFixes.agricraft.fix_enderio_plugin);
        put(StringHelper.getMixinString(ModIds.agricraft, "blockcrop", "harvestable"), () -> ModIds.agricraft.isLoaded && MIConfigIntegrations.agricraft.harvestable_mixin);
        put(StringHelper.getMixinString(ModIds.attained_drops, "blockplant", "harvestable"), () -> ModIds.attained_drops.isLoaded && MIConfigIntegrations.attained_drops.harvestable_mixin);
        put(StringHelper.getMixinString(ModIds.attained_drops, "blockplant", "hornharvestable"), () -> ModIds.attained_drops.isLoaded && ModIds.botania.isLoaded && MIConfigIntegrations.botania.wild_horn.attained_drops);
        put(StringHelper.getMixinString(ModIds.bewitchment, "oven", "fix"), () -> ModIds.bewitchment.isLoaded && MIConfigFixes.bewitchment.fix_witches_oven);
        put(StringHelper.getMixinString(ModIds.bewitchment, "oven", "heatable"), () -> ModIds.bewitchment.isLoaded && MIConfigIntegrations.bewitchment.heatable_oven);
        put(StringHelper.getMixinString(ModIds.bloodmagic, "burningfurnacearray"), () -> ModIds.bloodmagic.isLoaded && MIConfigIntegrations.blood_magic.furnace_array_mixins);
        put(StringHelper.getMixinString(ModIds.bloodmagic, "boundtool"), () -> ModIds.bloodmagic.isLoaded && MIConfigTweaks.blood_magic.bound_tool_tweak);
        put(StringHelper.getMixinString(ModIds.bloodmagic, "cuttingfluid"), () -> ModIds.bloodmagic.isLoaded && MIConfigTweaks.blood_magic.cutting_fluid_tweak);
        put(StringHelper.getMixinString(ModIds.bloodmagic, "ritualcrusher"),() -> ModIds.bloodmagic.isLoaded && MIConfigTweaks.blood_magic.ritual_crusher);
        put(StringHelper.getMixinString(ModIds.bloodmagic, "ritualharvest"), () -> ModIds.bloodmagic.isLoaded && MIConfigTweaks.blood_magic.ritual_harvest);
        put(StringHelper.getMixinString(ModIds.bloodmagic, "ritualmagnetic", "tweak"), () -> ModIds.bloodmagic.isLoaded && MIConfigTweaks.blood_magic.ritual_magnetic_replace);
        put(StringHelper.getMixinString(ModIds.bloodmagic, "ritualmagnetic", ModIds.ore_stages.modId), () -> ModIds.bloodmagic.isLoaded && ModIds.ore_stages.isLoaded && MIConfigIntegrations.blood_magic.ore_stages_magnetism);
        put(StringHelper.getMixinString(ModIds.botania, "exoflame"), () -> ModIds.botania.isLoaded && MIConfigIntegrations.botania.exoflame_mixin);
        put(StringHelper.getMixinString(ModIds.botania, "kekimurus"), () -> ModIds.botania.isLoaded && MIConfigIntegrations.botania.kekimurus_mixin);
        put(StringHelper.getMixinString(ModIds.cooking_for_blockheads, "oven", "fix"), () -> ModIds.cooking_for_blockheads.isLoaded && MIConfigFixes.cooking_for_blockheads.fix_oven);
        put(StringHelper.getMixinString(ModIds.cooking_for_blockheads, "oven", "heatable"), () -> ModIds.cooking_for_blockheads.isLoaded && MIConfigIntegrations.cooking_for_blockheads.heatable_oven);
        put(StringHelper.getMixinString(ModIds.engineers_decor, "decorfurnace", "heatable"), () -> ModIds.engineers_decor.isLoaded && MIConfigIntegrations.engineers_decor.heatable_furnace);
        put(StringHelper.getMixinString(ModIds.futuremc, "advancedfurnace", "heatable"), () -> ModIds.futuremc.isLoaded && MIConfigIntegrations.future_mc.heatable_advanced_furnaces);
        put(StringHelper.getMixinString(ModIds.harvestcraft, "blockpam", "harvestable"), () -> ModIds.harvestcraft.isLoaded && MIConfigIntegrations.harvestcraft.harvestable_fruit);
        put(StringHelper.getMixinString(ModIds.harvestcraft, "blockpam", "hornharvestable"), () -> ModIds.harvestcraft.isLoaded && ModIds.botania.isLoaded && MIConfigIntegrations.botania.wild_horn.harvestcraft);
        put(StringHelper.getMixinString(ModIds.harvestcraft, "rightclickharvesting", "tweak"), () -> ModIds.harvestcraft.isLoaded && MIConfigFixes.harvestcraft.fix_bark_harvest);
        put(StringHelper.getMixinString(ModIds.immersive_engineering, "alloysmelter", "boostable"), () -> ModIds.immersive_engineering.isLoaded && MIConfigIntegrations.immersive_engineering.boostable_alloy_smelter);
        put(StringHelper.getMixinString(ModIds.immersive_engineering, "blastfurnace", "boostable"), () -> ModIds.immersive_engineering.isLoaded && MIConfigIntegrations.immersive_engineering.boostable_blast_furnace);
        put(StringHelper.getMixinString(ModIds.immersive_engineering, "cokeoven", "boostable"), () -> ModIds.immersive_engineering.isLoaded && MIConfigIntegrations.immersive_engineering.boostable_coke_oven);
        put(StringHelper.getMixinString(ModIds.immersive_engineering, "hemp", "harvestable"), () -> ModIds.immersive_engineering.isLoaded && MIConfigIntegrations.immersive_engineering.harvestable_mixin);
        put(StringHelper.getMixinString(ModIds.industrial_craft, "blastfurnace", "boostable"), () -> ModIds.industrial_craft.isLoaded && MIConfigIntegrations.industrial_craft.boostable_blast_furnace);
        put(StringHelper.getMixinString(ModIds.industrial_craft, "cokekiln", "boostable"), () -> ModIds.industrial_craft.isLoaded && MIConfigIntegrations.industrial_craft.boostable_coke_kiln);
        put(StringHelper.getMixinString(ModIds.mystical_agriculture, "condenser", "heatable"), () -> ModIds.mystical_agriculture.isLoaded && MIConfigIntegrations.mystical_agriculture.heatable_furnace);
        put(StringHelper.getMixinString(ModIds.natures_aura, "externalheater"), () -> ModIds.natures_aura.isLoaded && MIConfigIntegrations.natures_aura.external_heater_mixins);
        put(StringHelper.getMixinString(ModIds.oreberries, "blockoreberrybush", "harvestable"), () -> ModIds.oreberries.isLoaded && MIConfigIntegrations.oreberries.harvestable_oreberries);
        put(StringHelper.getMixinString(ModIds.oreberries, "blockoreberrybush", "hornharvestable"), () -> ModIds.oreberries.isLoaded && ModIds.botania.isLoaded && MIConfigIntegrations.botania.wild_horn.oreberries);
        put(StringHelper.getMixinString(ModIds.roots, "elementalsoil"), () -> ModIds.roots.isLoaded && MIConfigIntegrations.roots.elemental_soil_mixins);
        put(StringHelper.getMixinString(ModIds.rustic, "condenser", "heatable"), () -> ModIds.rustic.isLoaded && MIConfigIntegrations.rustic.heatable_condenser);
        put(StringHelper.getMixinString(ModIds.thaumcraft, "bellows"), () -> ModIds.thaumcraft.isLoaded && MIConfigIntegrations.thaumcraft.bellows_mixins);
        put(StringHelper.getMixinString(ModIds.thaumcraft, "golemharvest"), () -> ModIds.thaumcraft.isLoaded && MIConfigIntegrations.thaumcraft.golem_mixins);
        put(StringHelper.getMixinString(ModIds.thaumcraft, "smelter", "heatable"), () -> ModIds.thaumcraft.isLoaded && MIConfigIntegrations.tinkers_construct.boostable_smeltery);
        put(StringHelper.getMixinString(ModIds.thaumic_additions, "smelter", "heatable"), () -> ModIds.thaumic_additions.isLoaded && MIConfigIntegrations.thaumic_additions.heatable_essence_smelter);
        put(StringHelper.getMixinString(ModIds.tinkers_construct, "smeltery", "boostable"), () -> ModIds.tinkers_construct.isLoaded && MIConfigIntegrations.tinkers_construct.boostable_smeltery);
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
