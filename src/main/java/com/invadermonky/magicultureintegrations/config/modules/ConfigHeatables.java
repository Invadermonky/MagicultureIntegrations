package com.invadermonky.magicultureintegrations.config.modules;

import net.minecraftforge.common.config.Config;

public class ConfigHeatables {
    public BewitchmentHeatable bewitchment = new BewitchmentHeatable();
    public CookingForBlockheadsHeatable cooking_for_blockheads = new CookingForBlockheadsHeatable();
    public EngineersDecorHeatable engineers_decor = new EngineersDecorHeatable();
    public FutureMCHeatable future_mc = new FutureMCHeatable();
    public ImmersiveEngineeringHeatable immersive_engineering = new ImmersiveEngineeringHeatable();
    public IndustrialCraftHeatable industrial_craft = new IndustrialCraftHeatable();
    public MysticalAgricultureHeatable mystical_agriculture = new MysticalAgricultureHeatable();
    public RusticHeatable rustic = new RusticHeatable();
    public ThaumicAdditionsHeatable thaumic_additions = new ThaumicAdditionsHeatable();
    public ThaumcraftHeatable thaumcraft = new ThaumcraftHeatable();
    public TinkersConstructHeatable tinkers_construct = new TinkersConstructHeatable();

    public static class BewitchmentHeatable {
        @Config.Comment("Thaumcraft Bellows will boost Bewitchment's Witches' Oven.")
        public boolean arcane_bellows = true;
        @Config.Comment("Immersive Engineering External Heater will heat/boost Bewitchment's Witches' Oven.")
        public boolean external_heater = true;
        @Config.Comment("Nature's Aura Extraneous Firestarter will heat/boost Bewitchment's Witches' Oven.")
        public boolean extraneous_firestarter = true;
        @Config.Comment("Quark's Foxhound will boost Bewitchment's Witches' Oven.")
        public boolean foxhound = true;
        @Config.Comment("Blood Magic Furnace Heater Array will heat Bewitchment's Witches' Oven.")
        public boolean furnace_heater_array = true;
    }
    public static class CookingForBlockheadsHeatable {
        @Config.Comment("Thaumcraft Bellows will boost Cooking for Blockheads' Oven.")
        public boolean arcane_bellows = true;
        @Config.Comment("Botania Exoflame will heat/boost Cooking for Blockheads' Oven.")
        public boolean exoflame = true;
        @Config.Comment("Nature's Aura Extraneous Firestarter will heat/boost Cooking for Blockheads' Oven.")
        public boolean extraneous_firestarter = true;
        @Config.Comment("Quark's Foxhound will boost Cooking for Blockheads' Oven.")
        public boolean foxhound = true;
        @Config.Comment("Blood Magic Furnace Heater Array will heat Cooking for Blockheads' Oven.")
        public boolean furnace_heater_array = true;
    }
    public static class EngineersDecorHeatable {
        @Config.Comment("Thaumcraft Bellows will boost Engineer's Decor's Small Laboratory Furnace.")
        public boolean arcane_bellows = true;
        @Config.Comment("Botania Exoflame will heat/boost Engineer's Decor's Small Laboratory Furnace.")
        public boolean exoflame = true;
        @Config.Comment("Immersive Engineering External Heater will heat/boost Engineer's Decor's Small Laboratory Furnace.")
        public boolean external_heater = true;
        @Config.Comment("Nature's Aura Extraneous Firestarter will heat/boost Engineer's Decor's Small Laboratory Furnace.")
        public boolean extraneous_firestarter = true;
        @Config.Comment("Quark's Foxhound will boost Engineer's Decor's Small Laboratory Furnace.")
        public boolean foxhound = true;
        @Config.Comment("Blood Magic Furnace Heater Array will heat Engineer's Decor's Small Laboratory Furnace.")
        public boolean furnace_heater_array = true;
    }
    public static class FutureMCHeatable {
        @Config.Comment("Thaumcraft Bellows will boost Future MC's Blast Furnace and Smoker.")
        public boolean arcane_bellows = true;
        @Config.Comment("Botania Exoflame will heat/boost Future MC's Blast Furnace and Smoker.")
        public boolean exoflame = true;
        @Config.Comment("Immersive Engineering External Heater will heat/boost Future MC's Blast Furnace and Smoker.")
        public boolean external_heater = true;
        @Config.Comment("Nature's Aura Extraneous Firestarter will heat/boost Future MC's Blast Furnace and Smoker.")
        public boolean extraneous_firestarter = true;
        @Config.Comment("Quark's Foxhound will boost Future MC's Blast Furnace and Smoker.")
        public boolean foxhound = true;
        @Config.Comment("Blood Magic Furnace Heater Array will heat Future MC's Blast Furnace and Smoker.")
        public boolean furnace_heater_array = true;
    }
    public static class ImmersiveEngineeringHeatable {
        public AlloySmelter alloy_smelter = new AlloySmelter();
        public BlastFurnace blast_furnace = new BlastFurnace();
        public CokeOven coke_oven = new CokeOven();

        public static class AlloySmelter {
            @Config.Comment("Set to false to allow the Alloy Smelter to be registered as a boostable machine.")
            public boolean _globalDisable = true;
            @Config.Comment("Thaumcraft Bellows will boost the Immersive Engineering Alloy Smelter. Fuel will still need to be provided\nfrom an external source.")
            public boolean arcane_bellows = true;
            @Config.Comment("Botania Exoflame will boost the Immersive Engineering Alloy Smelter. Fuel will still need to be provided\nfrom an external source.")
            public boolean exoflame = true;
            @Config.Comment("Nature's Aura Extraneous Firestarter will boost the Immersive Engineering Alloy Smelter. Fuel will still\nneed to be provided from an external source.")
            public boolean extraneous_firestarter = true;
            @Config.Comment("Quark's Foxhound will boost the Immersive Engineering Alloy Smelter. Fuel will still need to be provided from\nan external source.")
            public boolean foxhound = true;
        }
        public static class BlastFurnace {
            @Config.Comment("Set to false to allow the Blast Furnace to be registered as a boostable machine.")
            public boolean _globalDisable = true;
            @Config.Comment("Set to false to allow the Advanced Blast Furnace to receive boostable effects.")
            public boolean _restrictFurnace = true;
            @Config.Comment("Thaumcraft Bellows will boost the Immersive Engineering Blast Furnace. Fuel will still need to be provided\nfrom an external source.")
            public boolean arcane_bellows = true;
            @Config.Comment("Botania Exoflame will boost the Immersive Engineering Blast Furnace. Fuel will still need to be provided\nfrom an external source.")
            public boolean exoflame = true;
            @Config.Comment("Nature's Aura Extraneous Firestarter will boost the Immersive Engineering Blast Furnace. Fuel will still\nneed to be provided from an external source.")
            public boolean extraneous_firestarter = true;
            @Config.Comment("Quark's Foxhound will boost the Immersive Engineering Blast Furnace. Fuel will still need to be provided from\nan external source.")
            public boolean foxhound = true;
        }
        public static class CokeOven {
            @Config.Comment("Set to false to allow the Coke Oven to be registered as a boostable machine.")
            public boolean _globalDisable = true;
            @Config.Comment("Thaumcraft Bellows will boost the Immersive Engineering Coke Oven. Fuel will still need to be provided\nfrom an external source.")
            public boolean arcane_bellows = true;
            @Config.Comment("Botania Exoflame will boost the Immersive Engineering Coke Oven. Fuel will still need to be provided\nfrom an external source.")
            public boolean exoflame = true;
            @Config.Comment("Nature's Aura Extraneous Firestarter will boost the Immersive Engineering Coke Oven. Fuel will still\nneed to be provided from an external source.")
            public boolean extraneous_firestarter = true;
            @Config.Comment("Quark's Foxhound will boost the Immersive Engineering Coke Oven. Fuel will still need to be provided from\nan external source.")
            public boolean foxhound = true;
        }
    }
    public static class IndustrialCraftHeatable {
        public BlastFurnace blast_furnace = new BlastFurnace();
        public CokeKiln coke_kiln = new CokeKiln();
        public Fermenter fermenter = new Fermenter();

        public static class BlastFurnace {
            @Config.Comment("Set to false to allow the Blast Furnace to be registered as a boostable machine.")
            public boolean _globalDisable = true;
            @Config.Comment("Thaumcraft Bellows will boost the Industrial Craft Blast Furnace. Fuel will still need to be provided\nfrom an external source.")
            public boolean arcane_bellows = true;
            @Config.Comment("Botania Exoflame will boost the Industrial Craft Blast Furnace. Fuel will still need to be provided\nfrom an external source.")
            public boolean exoflame = true;
            @Config.Comment("Immersive Engineering External Heater will boost the Industrial Craft Blast Furnace. Fuel will still\nneed to be provided from an external source.")
            public boolean external_heater = true;
            @Config.Comment("Nature's Aura Extraneous Firestarter will boost the Industrial Craft Blast Furnace. Fuel will still\nneed to be provided from an external source.")
            public boolean extraneous_firestarter = true;
            @Config.Comment("Quark's Foxhound will boost the Industrial Craft Blast Furnace. Fuel will still need to be provided from\nan external source.")
            public boolean foxhound = true;
        }
        public static class CokeKiln {
            @Config.Comment("Set to false to allow the Coke Kiln to be registered as a boostable machine.")
            public boolean _globalDisable = true;
            @Config.Comment("Thaumcraft Bellows will boost the Industrial Craft Coke Kiln. Boosting device must be connected directly to the Coke Kiln block.")
            public boolean arcane_bellows = true;
            @Config.Comment("Botania Exoflame will boost the Industrial Craft Coke Kiln. Boosting device must be connected directly to the Coke Kiln block.")
            public boolean exoflame = true;
            @Config.Comment("Immersive Engineering External Heater will boost the Industrial Craft Coke Kiln. Boosting device must be connected directly to the Coke Kiln block.")
            public boolean external_heater = true;
            @Config.Comment("Nature's Aura Extraneous Firestarter will boost the Industrial Craft Coke Kiln. Boosting device must be connected directly to the Coke Kiln block.")
            public boolean extraneous_firestarter = true;
        }
        public static class Fermenter {
            @Config.Comment("Set to false to allow the Fermenter to be registered as a boostable machine.")
            public boolean _globalDisable = true;
            @Config.Comment("Thaumcraft Bellows will boost the Industrial Craft Fermenter. Fuel will still need to be provided\nfrom an external source.")
            public boolean arcane_bellows = true;
            @Config.Comment("Botania Exoflame will boost the Industrial Craft Fermenter. Fuel will still need to be provided\nfrom an external source.")
            public boolean exoflame = true;
            @Config.Comment("Immersive Engineering External Heater will boost the Industrial Craft Fermenter. Fuel will still\nneed to be provided from an external source.")
            public boolean external_heater = true;
            @Config.Comment("Nature's Aura Extraneous Firestarter will boost the Industrial Craft Fermenter. Fuel will still\nneed to be provided from an external source.")
            public boolean extraneous_firestarter = true;
            @Config.Comment("Quark's Foxhound will boost the Industrial Craft Fermenter. Fuel will still need to be provided from\nan external source.")
            public boolean foxhound = true;
        }
    }
    public static class MysticalAgricultureHeatable {
        @Config.Comment("Thaumcraft Bellows will boost Mystical Agriculture's Essence Furnaces.")
        public boolean arcane_bellows = true;
        @Config.Comment("Botania Exoflame will heat/boost Mystical Agriculture's Essence Furnaces.")
        public boolean exoflame = true;
        @Config.Comment("Immersive Engineering External Heater will heat/boost Mystical Agriculture's Essence Furnaces.")
        public boolean external_heater = true;
        @Config.Comment("Nature's Aura Extraneous Firestarter will heat/boost Mystical Agriculture's Essence Furnaces.")
        public boolean extraneous_firestarter = true;
        @Config.Comment("Quark's Foxhound will boost Mystical Agriculture's Essence Furnaces.")
        public boolean foxhound = true;
        @Config.Comment("Blood Magic Furnace Heater Array will heat Mystical Agriculture's Essence Furnaces.")
        public boolean furnace_heater_array = true;
    }
    public static class RusticHeatable {
        @Config.Comment("Thaumcraft Bellows will boost Rustic's Alchemic and Advanced Alchemic Condensers.")
        public boolean arcane_bellows = true;
        @Config.Comment("Botania Exoflame will heat/boost Rustic's Alchemic and Advanced Alchemic Condensers.")
        public boolean exoflame = true;
        @Config.Comment("Immersive Engineering External Heater will heat/boost Rustic's Alchemic and Advanced Alchemic Condensers.")
        public boolean external_heater = true;
        @Config.Comment("Nature's Aura Extraneous Firestarter will heat/boost Rustic's Alchemic and Advanced Alchemic Condensers.")
        public boolean extraneous_firestarter = true;
        @Config.Comment("Quark's Foxhound will boost Rustic's Alchemic and Advanced Alchemic Condensers.")
        public boolean foxhound = true;
        @Config.Comment("Blood Magic Furnace Heater Array will heat Rustic's Alchemic and Advanced Alchemic Condensers.")
        public boolean furnace_heater_array = true;
    }
    public static class ThaumicAdditionsHeatable {
        @Config.Comment("Botania Exoflame will heat/boost Thaumic Additions' Essentia Smelters.")
        public boolean exoflame = true;
        @Config.Comment("Immersive Engineering External Heater will heat/boost Thaumic Additions' Essentia Smelters.")
        public boolean external_heater = true;
        @Config.Comment("Nature's Aura Extraneous Firestarter will heat/boost Thaumic Additions' Essentia Smelters.")
        public boolean extraneous_firestarter = true;
        @Config.Comment("Blood Magic Furnace Heater Array will heat Thaumic Additions' Essentia Smelters.")
        public boolean furnace_heater_array = true;
    }
    public static class ThaumcraftHeatable {
        @Config.Comment("Botania Exoflame will heat/boost Thaumcraft's Essentia Smelters.")
        public boolean exoflame = true;
        @Config.Comment("Immersive Engineering External Heater will heat/boost Thaumcraft's Essentia Smelters.")
        public boolean external_heater = true;
        @Config.Comment("Nature's Aura Extraneous Firestarter will heat/boost Thaumcraft's Essentia Smelters.")
        public boolean extraneous_firestarter = true;
        @Config.Comment("Blood Magic Furnace Heater Array will heat Thaumcraft's Essentia Smelters.")
        public boolean furnace_heater_array = true;
    }
    public static class TinkersConstructHeatable {
        @Config.Comment("Thaumcraft Bellows will boost Tinkers Construct's Smeltery and Seared Furnace as well as Tinkers Complement's\n" +
                "Melter. Fuel will still need to be provided from an external source.")
        public boolean arcane_bellows = true;
        @Config.Comment("Botania Exoflame will boost Tinkers Construct's Smeltery and Seared Furnace as well as Tinkers Complement's\n" +
                "Melter. Fuel will still need to be provided from an external source.")
        public boolean exoflame = true;
        @Config.Comment("Immersive Engineering External Heater will boost Tinkers Construct's Smeltery and Seared Furnace as well as\n" +
                "Tinkers Complement's Melter. Fuel will still need to be provided from an external source.")
        public boolean external_heater = true;
        @Config.Comment("Nature's Aura Extraneous Firestarter will boost Tinkers Construct's Smeltery and Seared Furnace as well as\n" +
                "Tinkers Complement's Melter. Fuel will still need to be provided from an external source.")
        public boolean extraneous_firestarter = true;
        @Config.Comment("Quark's Foxhound will boost Tinkers Construct's Smeltery and Seared Furnace as well as Tinkers Complement's\n" +
                "Melter. Fuel will still need to be provided from an external source.")
        public boolean foxhound = true;
    }
}
