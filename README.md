# Magiculture Integrations

[![Requires MixinBooter](https://img.shields.io/badge/Requires-MixinBooter-3498db.svg?labelColor=34495e&style=for-the-badge)](https://www.curseforge.com/minecraft/mc-mods/mixin-booter)
[![Requires ConfigAnytime](https://img.shields.io/badge/Requires-ConfigAnytime-3498db.svg?labelColor=34495e&style=for-the-badge)](https://www.curseforge.com/minecraft/mc-mods/configanytime)

Magiculture Integrations is mod built for the [Magiculture 2](https://www.curseforge.com/minecraft/modpacks/magiculture-2) modpack that provides a large number of coded integrations and bugfixes.

While built for Magiculture 2, Magiculture Integrations features a robust configuration, allowing nearly every feature to be disabled.

---

## **➕ Additions**
### **Astral Sorcery**
- **Resonance Analyzer:** New simple machine used to sort Rock Crystals based on crystal purity (block texture and model courtesy of [Kombee](https://www.curseforge.com/members/kombee))
### **Botania**
- **Auromeria:** New functional flower that drains vis, flux, and mana to generate mana bursts, see Lexicon entry for more details

---

## **🐞 Bugfixes**
### **Agricraft**
- **Ender IO:**  Fixes crash caused by Agricraft's Ender IO Farming Station integration
### **Animania**
- **Dispenser Logic:** Fixes seed placement dispenser logic when Botania or Quark is loaded
### **Bewitchment**
- **Witches' Oven:** Fixes Bewitchment's Witches' Oven consuming container items
### **Blood Magic**
- **Fluid Routing:** Fixes Routing Node fluid routing being unable to support multiple liquids and soft-locking when encountering a full tank
- **Ritual Fix:** Fixes Blood Magic ritual resetting on world/chunk unload
### **Cooking for Blockheads**
- **Oven:** Fixes Cooking for Blockheads Oven consuming container items
### **Pam's Harvestcraft**
- **Bark Harvest:** Fixes bark harvesting launching harvested drops in random directions
- **Machines:** Pam's Harvestcraft machines (grinder, market, et cetera) are no longer registered as burnable furnace fuel

---

## **⚙ Integrations**
### **Astral Sorcery**
- **Spartan Weaponry:** Spartan Weaponry thrown weapons can be sharpened at the grindstone
### **Blood Magic**
- **Agricraft:** Reap of the Harvest Moon ritual can harvest Agricraft crops
- **Attained Drops 2:** Reap of the Harvest Moon ritual can harvest Attained Drops 2 crops
- **Bewitchment:** Reap of the Harvest Moon ritual can harvest Bewitchment crops
- **Immersive Engineering:** Reap of the Harvest Moon ritual can harvest Immersive Engineering hemp
- **Mystical Agriculture:** Reap of the Harvest Moon ritual can harvest Mystical Agriculture crops (as well as most Mystical Agriculture addons)
- **Oreberries:** Reap of the Harvest Moon ritual can harvest Oreberries Oreberry bushes
- **Ore Stages:** Ritual of Magnetism ignores staged ores the player does not have access to
- **Pam's Harvestcraft:** Reap of the Harvest Moon ritual can harvest Harvestcraft fruits and bark
- **Roots:** Reap of the Harvest Moon ritual can harvest Roots herbs
- **Rustic:** Reap of the Harvest Moon ritual can harvest Rustic herbs
- **Thaumic Additions:** Reap of the Harvest Moon ritual can harvest Thaumic Additions crops
### **Botania**
- **Twilight Forest:** Kekimurus flower can consume Twilight Forest's Experiment 115 (cake destruction configurable)
### **Cooking for Blockheads**
- **Oven:** Cooking for Blockheads Oven can be heated and boosted by all supported furnace heaters
### **Ender IO**
- **Agricraft:** Ender IO's Farming Station can harvest Agricraft crops
- **Attained Drops 2:** Ender IO's Farming Station can harvest Attained Drops 2 crops
- **Oreberries:** Ender IO's Farming Station can harvest Oreberries Oreberry bushes
- **Pam's Harvestcraft:** Ender IO's Farming Station can harvest Harvestcraft fruit
### **Engineer's Decor**
- **Laboratory Furnace:** Engineer's Decor Laboratory Furnace can be heated and boosted by all supported furnace heaters
### **Future MC**
- **Blast Furnace:** Future MC's Blast Furnace can be heated and boosted by all supported furnace heaters
- **Smoker:** Future MC's Smoker can be heated and boosted by all supported furnace heaters
### **Immersive Engineering**
- **Alloy Smelter:** (*disabled by default*) Immersive Engineering's Alloy Smelter can be boosted, but not heated, by all supported furnace heaters
- **Blast Furnace:** (*disabled by default*) Immersive Engineering's Blast Furnace can be boosted, but not heated, by all supported furnace heaters
- **Coke Oven:** (*disabled by default*) Immersive Engineering's Coke Oven can be boosted, but not heated, by all supported furnace heaters
### **Industrial Craft**
- **Blast Furnace:** (*disabled by default*) Industrial Craft's Blast Furnace can be boosted, but not heated, by all supported furnace heaters
- **Coke Kiln:** (*disabled by default*) Industrial Craft's Coke Kiln can be boosted, but not heated, by all supported furnace heaters
### **Mystical Agriculture**
- **Essence Furnace:** Mystical Agriculture's Essence Furnaces can be heated and boosted by all supported furnace heaters
### **Roots**
- **Agricraft:** Roots elemental soils can handle Agricraft crops (requires manual Agricraft soil json settings)
- **Attained Drops 2:** Roots elemental soils can handle Attained Drops 2 crops (Magmatic and Terran Soils do not effect drops due to soil implementation limitations)
- **Oreberries:** Roots elemental soils can handle Oreberries oreberry bushes
### **Rustic**
- **Alchemic Condenser:** Rustics Basic and Advanced Alchemic Condensers can be heated and boosted by all supported furnace heaters
### **Thaumcraft**
- **Agricraft:** Thaumcraft Golems can harvest Agricraft crops
- **Attained Drops 2:** Thaumcraft Golems can harvest Attained Drops 2 crops
- **Essence Smelter:** Thuamcraft's Essence Furnaces can be heated and boosted by all supported furnace heaters
- **Oreberries:** Thaumcraft Golems can harvest Oreberries Oreberry bushes
- **Pam's Harvestcraft:** Thaumcraft Golems can harvest Harvestcraft fruit and bark
### **Thaumic Additions**
- **Essence Smelter:** Thaumic Additions Essence Furnaces can be heated and boosted by all supported furnace heaters
### **The One Probe**
- **Ore Stages:** Fixes error tooltip when looking at staged ores on servers
- **Redstone Paste:** Redstone Paste redstone displays correct redstone power level
### **Tinker's Construct**
- **Smeltery:** (*disabled by default*) Tinker's Construct Smeltery can be boosted, but not heated, by all supported furnace heaters

---

## **🔥 Supported Furnace Heaters**

|          Mod          |        Feature        | Heater | Booster |
|:---------------------:|:---------------------:|:------:|:-------:|
|      Blood Magic      | Burning Furnace Array |   ✔    |         |
|        Botania        |       Exoflame        |   ✔    |    ✔    |
| Immersive Engineering |    External Heater    |   ✔    |    ✔    |
|     Nature's Aura     |   Extraneous Heater   |   ✔    |    ✔    |
|         Quark         |       Foxhound        |        |    ✔    |
|      Thaumcraft       |    Arcane Bellows     |        |    ✔    |

<br>

- **Heaters:** features that can ignite and provide burn time to furnaces.
- **Boosters:** features that can increase the smelting speed of furnaces.

---

## **🔧 Tweaks**
### **Astral Sorcery**
- **Ichosic Resonator:** Right-clicking the ground at night with an Ichosic Resonator will display the fluid reservoir contained in that chunk
### **Blood Magic**
- **Bound Tools:** Bound Tool right click harvest has been overhauled, significantly increasing performance and firing the BlockHarvestEvent, exposing block drops to tweaker mods
- **Cutting Fluid/Explosive Powder:** Cutting Fluid and Explosive Powder can have their max uses modified
- **Ritual of the Crusher:** Ritual fires the BlockHarvestEvent, exposing block drops to tweaker mods
- **Reap of the Harvest Moon:** Ritual fires the BlockHarvestEvent, exposing block drops to tweaker mods
- **Ritual of Magnetism:** Ritual no longer leaves empty cavities in the stone when pulling up ores
### **Nature's Aura**
- **Lax Spirit of Birthing:** Reduces the requirements needed to spawn a Spirit of Birthing, fixing an issue that prevented some modded animals from spawning them when giving birth in high aura chunks
