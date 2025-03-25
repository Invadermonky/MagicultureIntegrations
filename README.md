# Magiculture Integrations

Magiculture Integrations is mod built for the [Magiculture 2](https://www.curseforge.com/minecraft/modpacks/magiculture-2) modpack that provides a large number of coded integrations and bugfixes.

While built for Magiculture 2, Magiculture Integrations features a robust configuration, allowing nearly every feature to be disabled.

## **➕ Additions**
### **Astral Sorcery**
- **Resonance Analyzer:** New simple machine used to sort Rock Crystals based on crystal purity (block texture and model courtesy of [Kombee](https://www.curseforge.com/members/kombee))
### **Botania**
- **Auromeria:** New functional flower that drains vis, flux, and mana to generate mana bursts, see Lexicon entry for more details

## **🐞 Bugfixes**
### **Bewitchment**
- **Witches' Oven:** Fixes Bewitchment's Witches' Oven consuming container items
### **Cooking for Blockheads**
- **Oven:** Fixes Cooking for Blockheads Oven consuming container items
### **Ender IO**
- **Farming Station:** Fixes Ender IO's Farming Station causing a crash when harvesting Agricraft crops 
### **The One Probe**
- **Ore Stages:** Fixes error tooltip when looking at staged ores on servers

## **⚙ Integrations**
### **Blood Magic**
- **Agricraft:** Reap of the Harvest Moon ritual can now harvest Agricraft crops
- **Attained Drops 2:** Reap of the Harvest Moon ritual can now harvest Attained Drops 2 crops
- **Bewitchment:** Reap of the Harvest Moon ritual can now harvest Bewitchment crops
- **Harvestcraft:** Reap of the Harvest Moon ritual can now harvest Harvestcraft fruits and bark
- **Immersive Engineering:** Reap of the Harvest Moon ritual can now harvest Immersive Engineering hemp
- **Mystical Agriculture:** Reap of the Harvest Moon ritual can now harvest Mystical Agriculture crops (as well as most Mystical Agriculture addons)
- **Roots:** Reap of the Harvest Moon ritual can now harvest Roots herbs
- **Rustic:** Reap of the Harvest Moon ritual can now harvest Rustic herbs
- **Thaumic Additions:** Reap of the Harvest Moon ritual can now harvest Thaumic Additions crops
### **Botania**
- **Attained Drops 2:** Drum of the Forest can now harvest Attained Drops 2 crops
- **Twilight Forest:** Kekimurus flower can now consume Twilight Forest's Experiment 115 (cake destruction configurable)
### **Cooking for Blockheads**
- **Oven:** Cooking for Blockheads Oven can now be heated and boosted by all supported furnace heaters
### **Ender IO**
- **Attained Drops 2:** Farming Station 
### **Engineer's Decor**
- **Laboratory Furnace:** Engineer's Decor Laboratory Furnace can now be Coke Kiln by all supported furnace heaters
### **Future MC**
- **Blast Furnace:** Future MC's Blast Furnace can now be heated and boosted by all supported furnace heaters
- **Smoker:** Future MC's Smoker can now be heated and boosted by all supported furnace heaters
### **Immersive Engineering**
- **Alloy Smelter:** (*disabled by default*) Immersive Engineering's Alloy Smelter can be boosted, but not heated, by all supported furnace heaters
- **Blast Furnace:** (*disabled by default*) Immersive Engineering's Blast Furnace can be boosted, but not heated, by all supported furnace heaters
- **Coke Oven:** (*disabled by default*) Immersive Engineering's Coke Oven can be boosted, but not heated, by all supported furnace heaters
### **Industrial Craft**
- **Blast Furnace:** (*disabled by default*) Industrial Craft's Blast Furnace can be boosted, but not heated, by all supported furnace heaters
- **Coke Kiln:** (*disabled by default*) Industrial Craft's Coke Kiln can be boosted, but not heated, by all supported furnace heaters
### **Mystical Agriculture**
- **Essence Furnace:** Mystical Agriculture's Essence Furnaces can now be heated and boosted by all supported furnace heaters
### **Rustic**
- **Alchemic Condenser:** Rustics Basic and Advanced Alchemic Condensers can now be heated and boosted by all supported furnace heaters
### **Thaumcraft**
- **Agricraft:** Thaumcraft Golems can now harvest Agricraft crops
- **Attained Drops 2:** Thaumcraft Golems can now harvest Attained Drops 2 crops
- **Essence Smelter:** Thuamcraft's Essence Furnaces can now be heated and boosted by all supported furnace heaters
### **Thaumic Additions**
- **Essence Smelter:** Thaumic Additions Essence Furnaces can now be heated and boosted by all supported furnace heaters
### **The One Probe**
- **Redstone Paste:** Redstone Paste redstone now displays correct redstone power level
### **Tinker's Construct**
- **Smeltery:** (*default disabled*) Tinker's Construct Smeltery can now be boosted, but not heated, by all supported furnace heaters

## **🔥 Supported Furnace Heaters**

|          Mod          |        Feature        |  Heater  | Booster  |
|:---------------------:|:---------------------:|:--------:|:--------:|
|      Blood Magic      | Burning Furnace Array | &#x2714; |          |
|        Botania        |       Exoflame        | &#x2714; | &#x2714; |
| Immersive Engineering |    External Heater    | &#x2714; | &#x2714; |
|     Nature's Aura     |   Extraneous Heater   | &#x2714; | &#x2714; |
|         Quark         |       Foxhound        |          | &#x2714; |
|      Thaumcraft       |    Arcane Bellows     |          | &#x2714; |

- **Heaters:** features that can ignite and provide burn time to furnaces.
- **Boosters:** features that can increase the smelting speed of furnaces.

## **🔧 Tweaks**
### **Astral Sorcery**
- **Ichosic Resonator:** Right-clicking the ground at night with an Ichosic Resonator will display the fluid reservoir contained in that chunk
### **Blood Magic**
- **Ritual of the Crusher:** Ritual now fires the BlockHarvestEvent, exposing block drops to tweaker mods
- **Ritual of Magnetism:** Ritual now respects ore stages, bypassing ores that the player does not have access to
