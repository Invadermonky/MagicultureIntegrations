package com.invadermonky.magicultureintegrations.config;

import com.cleanroommc.configanytime.ConfigAnytime;
import com.invadermonky.magicultureintegrations.MagicultureIntegrations;
import com.invadermonky.magicultureintegrations.config.generics.ConfigHeatableOnly;
import com.invadermonky.magicultureintegrations.config.generics.ConfigHeatables;
import com.invadermonky.magicultureintegrations.config.tags.ModTags;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config.LangKey("config." + MagicultureIntegrations.MOD_ID + ":integrations")
@Config(
        modid = MagicultureIntegrations.MOD_ID,
        name = MagicultureIntegrations.MOD_ID + "/" + MagicultureIntegrations.MOD_NAME + " - Integrations"
)
public class MIConfigIntegrations {
    @Config.Name("Agricraft")
    public static AgricraftIntegrations agricraft = new AgricraftIntegrations();
    @Config.Name("Astral Sorcery")
    public static AstralSorceryIntegrations astral_sorcery = new AstralSorceryIntegrations();
    @Config.Name("Attained Drops 2")
    public static AttainedDropsIntegrations attained_drops = new AttainedDropsIntegrations();
    @Config.Name("Bewitchment")
    public static BewitchmentIntegrations bewitchment = new BewitchmentIntegrations();
    @Config.Name("Blood Magic")
    public static BloodMagicIntegrations blood_magic = new BloodMagicIntegrations();
    @Config.Name("Botania")
    public static BotaniaIntegrations botania = new BotaniaIntegrations();
    @Config.Name("Cooking for Blockheads")
    public static CookingForBlockheadsIntegrations cooking_for_blockheads = new CookingForBlockheadsIntegrations();
    @Config.Name("Cyclic")
    public static CyclicMagicIntegrations cyclic = new CyclicMagicIntegrations();
    @Config.Name("Engineer's Decor")
    public static EngineersDecorIntegrations engineers_decor = new EngineersDecorIntegrations();
    @Config.Name("Future MC")
    public static FutureMCIntegrations future_mc = new FutureMCIntegrations();
    @Config.Name("Harvestcraft")
    public static HarvestcraftIntegrations harvestcraft = new HarvestcraftIntegrations();
    @Config.Name("Immersive Engineering")
    public static ImmersiveEngineeringIntegrations immersive_engineering = new ImmersiveEngineeringIntegrations();
    @Config.Name("Industrial Craft 2")
    public static IndustrialCraftIntegrations industrial_craft = new IndustrialCraftIntegrations();
    @Config.Name("Mystical Agriculture")
    public static MysticalAgricultureIntegrations mystical_agriculture = new MysticalAgricultureIntegrations();
    @Config.Name("Nature's Aura")
    public static NaturesAuraIntegrations natures_aura = new NaturesAuraIntegrations();
    @Config.Name("New Crimson Revelations")
    public static NewCrimsonRevelationsIntegrations new_crimson_revelations = new NewCrimsonRevelationsIntegrations();
    @Config.Name("Nutrition")
    public static NutritionIntegrations nutrition = new NutritionIntegrations();
    @Config.Name("Oreberries")
    public static OreberriesIntegrations oreberries = new OreberriesIntegrations();
    @Config.Name("Quark")
    public static QuarkIntegrations quark = new QuarkIntegrations();
    @Config.Name("Rats")
    public static RatsIntegrations rats = new RatsIntegrations();
    @Config.Name("Roots")
    public static RootsIntegrations roots = new RootsIntegrations();
    @Config.Name("Rustic")
    public static RusticIntegrations rustic = new RusticIntegrations();
    @Config.Name("Thaumcraft")
    public static ThaumcraftIntegrations thaumcraft = new ThaumcraftIntegrations();
    @Config.Name("Thaumic Additions")
    public static ThaumicAdditionsIntegrations thaumic_additions = new ThaumicAdditionsIntegrations();
    @Config.Name("The One Probe")
    public static TheOneProbeIntegrations the_one_probe = new TheOneProbeIntegrations();
    @Config.Name("Tinker's Construct")
    public static TinkersConstructIntegrations tinkers_construct = new TinkersConstructIntegrations();

    public static class AgricraftIntegrations {
        @Config.RequiresMcRestart
        @Config.Name("Harvestable Agricraft Crops")
        @Config.Comment
                ({
                        "Enables the IHarvestableCrop mixin injection, allowing Agricraft crops to be harvested by a",
                        "number of supported modded harvesters."
                })
        public boolean harvestable_mixin = true;
    }

    public static class AstralSorceryIntegrations {
        @Config.RequiresMcRestart
        @Config.Name("Spartan Thrown Weapon Sharpening")
        @Config.Comment("Allows Spartan Weaponry thrown weapons to be sharped at the grindstone.")
        public boolean spartan_weaponry_thrown = true;
    }

    public static class AttainedDropsIntegrations {
        @Config.RequiresMcRestart
        @Config.Name("Attained Drops 2")
        @Config.Comment
                ({
                        "Enables the IHarvestableCrop mixin injection, allowing Attained Drops 2 crops to be harvested",
                        "by a number ofsupported modded harvesters."
                })
        public boolean harvestable_mixin = true;
    }

    public static class BewitchmentIntegrations {
        @Config.RequiresMcRestart
        @Config.Name("Heatable Witches' Oven")
        @Config.Comment
                ({
                        "Enables the IHeatableTile mixin injection, allowing the Witches' Oven to be heated and boosted by",
                        "all supported furnace heaters. For individual toggles see the specific heater integration configs."
                })
        public boolean heatable_oven = true;
    }

    public static class BloodMagicIntegrations {
        @Config.RequiresMcRestart
        @Config.Name("Enable Burning Furnace Array Integrations")
        @Config.Comment("Global enable/disable for all Burning Furnace Array integrations.")
        public boolean furnace_array_mixins = true;

        public ConfigHeatableOnly furnace_array = new ConfigHeatableOnly();

        @Config.RequiresMcRestart
        @Config.Name("Ore Stages Magnetism")
        @Config.Comment("Ritual of Magnetism now respects player Game Stage date for ores hidden with Ore Stages.")
        public boolean ore_stages_magnetism = true;

        @Config.RequiresMcRestart
        @Config.Name("Reap of the Harvest Moon Integrations")
        public RitualHarvestIntegrations ritual_harvest = new RitualHarvestIntegrations();

        @Config.RequiresMcRestart
        @Config.Name("Sentient Armor Quality")
        @Config.Comment("Sentient armor will copy the quality of any currently worn armor when equipped.")
        public boolean sentient_armor_quality = true;

        public static class RitualHarvestIntegrations {
            public boolean bewitchment = true;
            public boolean harvestcraft = true;
            public boolean mystical_agriculture = true;
            public boolean mystical_world = true;
            public boolean roots = true;
            public boolean rustic = true;
            public boolean thaumic_additions = true;
        }
    }

    public static class BotaniaIntegrations {
        @Config.RequiresMcRestart
        @Config.Name("Enable Exoflame Integrations")
        @Config.Comment("Global enable/disable for all Exoflame integrations")
        public boolean exoflameMixin = true;

        @Config.RequiresMcRestart
        @Config.Name("Exoflame Integrations")
        public ConfigHeatables exoflameHeatables = new ConfigHeatables();

        @Config.Name("Horn/Drum of the Wild Integrations")
        public WildHornIntegrations wild_horn = new WildHornIntegrations();

        @Config.RequiresMcRestart
        @Config.Name("Enable Kekimurus Integrations")
        @Config.Comment("Global enable/disable for all Kekimurus integrations")
        public boolean kekimurus_mixin = true;

        @Config.RequiresMcRestart
        @Config.Name("Kekimurus Integrations")
        public KekimurusIntegrations kekimurus = new KekimurusIntegrations();

        public static class WildHornIntegrations {
            @Config.Name("Attained Drops 2")
            @Config.Comment("Allows the Horn and Drum of the Wild to harvest Attained Drops 2 crops.")
            public boolean attained_drops = true;
            @Config.Name("Harvestcraft")
            @Config.Comment("Allows the Horn and Drum of the Wild to harvest Harvestcraft fruit and bark.")
            public boolean harvestcraft = true;
            @Config.Name("Industrial Craft 2")
            @Config.Comment("Allows the Horn and Drum of the Wild to harvest IC2 Crops.")
            public boolean industrialcraft = true;
            @Config.Name("New Crimson Revelations")
            @Config.Comment("Allows the Horn and Drum of the Wild to harvest New Crimson Revelations' Mana Pods.")
            public boolean newcrimsonrevelations = true;
            @Config.Name("Oreberries")
            @Config.Comment("Allows the Horn and Drum of the Wild to harvest Oreberries orberry bushes.")
            public boolean oreberries = true;
        }

        public static class KekimurusIntegrations {
            @Config.RequiresMcRestart
            @Config.Comment("The Kekimurus will now eat Twilight Forest's Experiment 115.")
            public boolean experiment115 = true;
            @Config.Comment("The Kekimurus will destroy Experiment 115 completely if the cake is consumed.")
            public boolean experiment115Consume = false;
        }
    }

    public static class CookingForBlockheadsIntegrations {
        @Config.RequiresMcRestart
        @Config.Name("Heatable Oven")
        @Config.Comment
                ({
                        "Enables the IHeatableTile mixin injection, allowing the Oven to be heated and boosted by all",
                        "supported furnace heaters. For individual toggles see the specific heater integration configs."
                })
        public boolean heatableOven = true;
    }

    public static class CyclicMagicIntegrations {
        @Config.RequiresMcRestart
        @Config.Name("Harvester Expansion")
        @Config.Comment("Enables the Cyclic Gentle Harvester crop harvest expansion.")
        public boolean harvesterExpansion = true;
    }

    public static class EngineersDecorIntegrations {
        @Config.RequiresMcRestart
        @Config.Name("Heatable Laboratory Furnace")
        @Config.Comment
                ({
                        "Enables the IHeatableTile mixin injection, allowing the Laboratory Furnace to be heated and boosted by",
                        "all supported furnace heaters. For individual toggles see the specific heater integration configs."
                })
        public boolean heatableFurnace = true;
    }

    public static class FutureMCIntegrations {
        @Config.RequiresMcRestart
        @Config.Name("Heatable Blast Furnace/Smoker")
        @Config.Comment
                ({
                        "Enables the IHeatableTile mixin injection, allowing the Blast Furnace and Smoker to be heated and boosted",
                        "by all supported furnace heaters. For individual toggles see the specific heater integration configs."
                })
        public boolean heatableAdvancedFurnaces = true;
    }

    public static class HarvestcraftIntegrations {
        @Config.RequiresMcRestart
        @Config.Name("Harvestable Harvestcraft Fruit and Bark")
        @Config.Comment
                ({
                        "Enables the IHarvestableCrop mixin injection, allowing fruit and bark to be harvested by",
                        "most supported modded harvesters."
                })
        public boolean harvestable_fruit = true;
    }

    public static class ImmersiveEngineeringIntegrations {
        @Config.RequiresMcRestart
        @Config.Name("Boostable Alloy Smelter")
        @Config.Comment
                ({
                        "Enables the IBoostableTile mixin injection, allowing the Alloy Smelter to be boosted by all supported",
                        "furnace boosters. For individual toggles see the specific heater integration configs."
                })
        public boolean boostableAlloySmelter = false;

        @Config.RequiresMcRestart
        @Config.Name("Boostable Blast Furnace")
        @Config.Comment
                ({
                        "Enables the IBoostableTile mixin injection, allowing the Blast Furnace to be boosted by all supported",
                        "furnace boosters. For individual toggles see the specific heater integration configs."
                })
        public boolean boostableBlastFurnace = false;

        @Config.RequiresMcRestart
        @Config.Name("Boostable Improved Blast Furnace")
        @Config.Comment("Allows Furnace Boosters to also boost the Improved Blast Furnace.")
        public boolean boostableBlastFurnaceImproved = false;

        @Config.RequiresMcRestart
        @Config.Name("Boostable Coke Oven")
        @Config.Comment
                ({
                        "Enables the IBoostableTile mixin injection, allowing the Coke Oven to be boosted by all supported",
                        "furnace boosters. For individual toggles see the specific heater integration configs."
                })
        public boolean boostableCokeOven = false;

        @Config.RequiresMcRestart
        @Config.Name("External Heater Integrations")
        public ExternalHeaterIntegrations externalHeaterHeatables = new ExternalHeaterIntegrations();

        @Config.RequiresMcRestart
        @Config.Name("Harvestable Hemp")
        @Config.Comment
                ({
                        "Enables the IHarvestableCrop mixin injection, allowing IE Hemp to be harvested by a number of",
                        "modded harvesters."
                })
        public boolean harvestableMixin = true;

        public static class ExternalHeaterIntegrations {
            @Config.RequiresMcRestart
            @Config.Name("Bewitchment Witches' Oven")
            public boolean witches_oven = true;
            @Config.RequiresMcRestart
            @Config.Name("Engineer's Decor Laboratory Furnace")
            public boolean ed_laboratory_furnace = true;
            @Config.RequiresMcRestart
            @Config.Name("Future MC Blast Furnace/Smoker")
            public boolean fmc_advanced_furnaces = true;
            @Config.RequiresMcRestart
            @Config.Name("IC2 Blast Furnace")
            public boolean ic2_blast_furnace = true;
            @Config.RequiresMcRestart
            @Config.Name("IC2 Coke Kiln")
            public boolean ic2_coke_kiln = true;
            @Config.RequiresMcRestart
            @Config.Name("Mystical Agriculture Essence Furnace")
            public boolean ma_essence_furnaces = true;
            @Config.RequiresMcRestart
            @Config.Name("Rustic Basic/Advanced Alchemic Condenser")
            public boolean rustic_condenser = true;
            @Config.RequiresMcRestart
            @Config.Name("Thaumcraft Essentia Smelters")
            public boolean tc_essentia_smelter = true;
            @Config.RequiresMcRestart
            @Config.Name("Thaumic Additions Essentia Smelters")
            public boolean ta_essentia_smelter = true;
            @Config.RequiresMcRestart
            @Config.Name("Tinker's Construct Smeltery")
            public boolean tc_smeltery = true;
        }
    }

    public static class IndustrialCraftIntegrations {
        @Config.RequiresMcRestart
        @Config.Name("Boostable Blast Furnace")
        @Config.Comment
                ({
                        "Enables the IBoostableTile mixin injection, allowing the Blast Furnace to be boosted by all supported",
                        "furnace boosters. For individual toggles see the specific heater integration configs."
                })
        public boolean boostableBlastFurnace = false;

        @Config.RequiresMcRestart
        @Config.Name("Boostable Coke Kiln")
        @Config.Comment
                ({
                        "Enables the IBoostableTile mixin injection, allowing the Coke Kiln to be boosted by all supported",
                        "furnace boosters. For individual toggles see the specific heater integration configs."
                })
        public boolean boostableCokeKiLn = false;

        @Config.Name("Growable Crops - Bonemeal")
        @Config.Comment
                ({
                        "Allows IC2 Crop growth to be increased with Bonemeal and Bonemeal-type items. This setting requires",
                        "Growable Crops set to true."
                })
        public boolean growableCropsBonemeal = false;

        @Config.RequiresMcRestart
        @Config.Name("Harvestable Crops")
        @Config.Comment
                ({
                        "Enables the IHarvestableCrop mixin injection, allowing IC2 Crops to be harvested by supported",
                        "modded harvesters."
                })
        public boolean harvestableCrops = true;
    }

    public static class MysticalAgricultureIntegrations {
        @Config.RequiresMcRestart
        @Config.Name("Heatable Essence Furnaces")
        @Config.Comment
                ({
                        "Enables the IHeatableTile mixin injection, allowing all Essence Furnaces to be heated and boosted by",
                        "all supported furnace heaters. For individual toggles see the specific heater integration configs."
                })
        public boolean heatableFurnace = true;
    }

    public static class NaturesAuraIntegrations {
        @Config.RequiresMcRestart
        @Config.Name("Enable Extraneous Heater Integrations")
        @Config.Comment("Global enable/disable for all Extraneous Heater integrations.")
        public boolean extraneousHeaterMixins = true;

        @Config.Name("Extraneous Heater Integrations")
        public ConfigHeatables extraneousHeaterHeatables = new ConfigHeatables();
    }

    public static class NewCrimsonRevelationsIntegrations {
        @Config.RequiresMcRestart
        @Config.Name("Harvestable Mana Pods")
        @Config.Comment
                ({
                        "Enables the IHarvestableCrop mixin injection, allowing Mana Pods to be harvested by supported",
                        "modded harvesters."
                })
        public boolean harvestableManaPods = true;
    }

    public static class NutritionIntegrations {
        @Config.Name("Custom Foods")
        @Config.Comment
                ({
                        "Custom foods that are not normally supported by Nutrition can be added here. These can be any",
                        "item that features an 'eat' effect but does not extend the ItemFood class.",
                        "Format:",
                        "  modid:itemid:meta;nutritionalValue;nutrientName1;nutrientName2...",
                        "    modid:itemid - the registry name of the item",
                        "    meta - [optional] the metadata of the item",
                        "    nutritionalValue - the nutritional value of the food, this value must be between 0 and 100",
                        "    nutrientName - the names of the nutrient type, default values are dairy, fruit, grain, protein",
                        "                   and vegetable. Multiple names can be used separated by semi-colons.",
                        "Example:",
                        "  ic2:filled_tin_can;0.5;protein;grain"
                })
        public String[] customFoods = new String[]{};

        @Config.Name("Auto Feeders")
        public AutoFeedersIntegrations autoFeeders = new AutoFeedersIntegrations();

        public static class AutoFeedersIntegrations {
            @Config.Name("Blood Magic Ritual of Satiated Stomach")
            @Config.Comment("Ritual of the Satiated Stomach feeds players food directly so it does not need a cap or adjustment.")
            public boolean BMRitualFullStomach = true;

            @Config.Name("Botania Fruit of Grisaia")
            @Config.Comment("Fires 4 times per use of the item")
            public AutoFeederDeviceConfig BotFruitOfGrisaia = new AutoFeederDeviceConfig("fruit");

            @Config.Name("Cyclic Saturation Potion")
            public AutoFeederDeviceConfig cyclicSaturation = new AutoFeederDeviceConfig(0.05);

            @Config.Name("Industrial Craft Quantum Helmet")
            public AutoFeederDeviceConfig IC2QuantumHelmet = new AutoFeederDeviceConfig("protein");

            @Config.Name("Industrial Foregoing Meat Feeder")
            public AutoFeederDeviceConfig IFMeatFeeder = new AutoFeederDeviceConfig("protein");

            @Config.Name("Minecraft Saturation Potion")
            public AutoFeederDeviceConfig saturationPotion = new AutoFeederDeviceConfig(0.0025);

            @Config.Name("Traveller's Backpack Sunflower Backpack")
            public AutoFeederDeviceConfig TBSunflowerBackpack = new AutoFeederDeviceConfig();

            @Config.Name("Tinker's Construct Tasty Trait")
            public AutoFeederDeviceConfig TCTastyTrait = new AutoFeederDeviceConfig("protein");

            @Config.Name("Thaumcraft Verdant Charm")
            public AutoFeederDeviceConfig TCVerdantCharm = new AutoFeederDeviceConfig();

            public static class AutoFeederDeviceConfig {
                @Config.RequiresMcRestart
                @Config.Name("Enable Nutrition Integration")
                @Config.Comment("Enables Nutrition integration when this device auto-feeds the player.")
                public boolean enable = true;

                @Config.RangeDouble(min = 0, max = 100)
                @Config.Name("Nutritional Value")
                @Config.Comment
                        ({
                                "The nutritional value gained whenever this device auto-feeds the player. This value will",
                                "be applied across all configured nutrients."
                        })
                public double nutritionValue;

                @Config.RangeInt(min = 0, max = 100)
                @Config.Name("Maximum Nutritional Value")
                @Config.Comment
                        ({
                                "The maximum nutritional value this device can reach. A value of 75 can only achieve",
                                "75/100 nutrition."
                        })
                public int maxNutrition;

                @Config.Name("Nutrient Names")
                @Config.Comment
                        ({
                                "A list of nutrient names this device can increase. Leaving this option will cause this",
                                "device to increase all registered nutrients."
                        })
                public String[] nutrientNames;

                public AutoFeederDeviceConfig(double nutritionValue, int maxNutrition, String... nutrientNames) {
                    this.nutritionValue = nutritionValue;
                    this.maxNutrition = maxNutrition;
                    this.nutrientNames = nutrientNames;
                }

                public AutoFeederDeviceConfig(double nutritionValue, String... nutrientNames) {
                    this(nutritionValue, 75, nutrientNames);
                }

                public AutoFeederDeviceConfig(String... nutrientNames) {
                    this(0.5, nutrientNames);
                }
            }
        }
    }

    public static class OreberriesIntegrations {
        @Config.RequiresMcRestart
        @Config.Name("Hafvestable Oreberries")
        @Config.Comment
                ({
                        "Enables the IHarvestableCrop mixin injection, allowing oreberry bushes to be harvested by",
                        "supported modded harvesters."
                })
        public boolean harvestableOreberries = true;
    }

    public static class QuarkIntegrations {
        @Config.RequiresMcRestart
        @Config.Name("Enable Foxhound Booster")
        @Config.Comment("Enables Foxhound furnace booster integrations.")
        public boolean foxhoundHeater = true;
        @Config.Name("Foxhound Integrations")
        public ConfigHeatables foxhoundHeatables = new ConfigHeatables();

        @Config.Comment
                ({
                        "The cook time boost a sitting Foxhound provides a heatable tile. Processing time will be reduced",
                        "by this amount every 3 ticks."
                })
        public int foxButtSpeedup = 1;
    }

    public static class RatsIntegrations {
        @Config.RequiresMcRestart
        @Config.Name("Rat Harvest Expansion")
        @Config.Comment("Enables the Rats crop harvest crop expansion.")
        public boolean ratHarvestExpansion = true;
    }

    public static class RootsIntegrations {
        @Config.RequiresMcRestart
        @Config.Name("Elemental Soil Integrations")
        @Config.Comment("Enables the expanded elemental soil harvest integration.")
        public boolean elementalSoilMixins = true;
    }

    public static class RusticIntegrations {
        @Config.RequiresMcRestart
        @Config.Name("Harvestable Herbs")
        @Config.Comment
                ({
                        "Enables the IHarvestableCrop mixin injection, allowing Herbs to be harvested by supported",
                        "modded harvesters."
                })
        public boolean harvestableHerbs = true;

        @Config.RequiresMcRestart
        @Config.Name("Heatable Basic/Advance Alchemic Condenser")
        @Config.Comment
                ({
                        "Enables the IHeatableTile mixin injection, allowing the Basic and Advanced Alchemic Condenser",
                        "to be heated and boosted by all supported furnace heaters. For individual toggles see the",
                        "specific heater integration configs."
                })
        public boolean heatableCondenser = true;
    }

    public static class ThaumcraftIntegrations {
        @Config.RequiresMcRestart
        @Config.Name("Enable Arcane Bellows Integrations")
        @Config.Comment("Global enable/disable for all Arcane Bellows integrations.")
        public boolean bellowsMixins = true;

        @Config.Name("Arcane Bellows Integrations")
        public ConfigHeatables bellowsHeatables = new ConfigHeatables();

        @Config.RequiresMcRestart
        @Config.Name("Golem Harvest Expansion")
        @Config.Comment("Enables expanded Golem crop harvest integration.")
        public boolean golemHarvestExpansion = true;

        @Config.RequiresMcRestart
        @Config.Name("Heatable Essentia Smelteries")
        @Config.Comment
                ({
                        "Enables the IHeatableTile mixin injection, allowing the Essentia Smelteries to be heated and boosted",
                        "by all supported furnace heaters. For individual toggles see the specific heater integration configs."
                })
        public boolean essenceFurnaceMixins = true;
    }

    public static class ThaumicAdditionsIntegrations {
        @Config.RequiresMcRestart
        @Config.Name("Heatable Essentia Smelteries")
        @Config.Comment
                ({
                        "Enables the IHeatableTile mixin injection, allowing the Essentia Smelteries to be heated and boosted",
                        "by all supported furnace heaters. For individual toggles see the specific heater integration configs."
                })
        public boolean heatableEssenceSmelter = true;
    }

    public static class TheOneProbeIntegrations {
        @Config.Name("Redstone Paste")
        @Config.Comment("Enables Redstone Paste support, displaying redstone power when looking at redstone paste wires.")
        public boolean redstone_paste = true;

        @Config.Name("Ore Stages")
        @Config.Comment("Enables Ore Stages support, fixing errored tooltip when looking at staged ores on servers.")
        public boolean ore_stages = true;
    }

    public static class TinkersConstructIntegrations {
        @Config.RequiresMcRestart
        @Config.Name("Boostable Smeltery")
        @Config.Comment
                ({
                        "Enables the IHeatableTile mixin injection, allowing the Smeltery to be boosted by all supported",
                        "furnace boosters. For individual toggles see the specific heater integration configs."
                })
        public boolean boostableSmeltery = false;
    }

    @Mod.EventBusSubscriber(modid = MagicultureIntegrations.MOD_ID)
    public static class ConfigChangeListener {
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(MagicultureIntegrations.MOD_ID)) {
                ConfigManager.sync(MagicultureIntegrations.MOD_ID, Config.Type.INSTANCE);
                ModTags.syncConfig();
            }
        }
    }

    static {
        ConfigAnytime.register(MIConfigIntegrations.class);
    }
}
