# Changelog
## 1.12.2-2.2.6
### Fixed
- Fixed Actually Additions Farmer harvesting `IHarvestableCrop` crops without draining any power

---

## 1.12.2-2.2.5
### Fixed
- Fixed server crash when Auromeria mana bursts exploded
- Fixed rare crash when IC2 crops harvest method returned a null value

---

## 1.12.2-2.2.4
### Fixed
- Fixed Flux Lens not displaying recipe in thaumonomicon

---

## 1.12.2-2.2.3
### Added
- Added new Thaumcraft Infusion enchantment that allows all non-goggle armors to receive the "Goggles of Revealing" upgrade
### Fixed
- Fixed an issue causing the Cooking For Blockheads oven fix from not loading in some environments
- Fixed an issue causing Tinker's Construct Smeltery IBoostable interface to not update the tile correctly (this also fixes Tinker's Complement Melter)

---

## 1.12.2-2.2.2
### Fixed
- Fixed typo that prevented Actually Additions Farmer fix from loading

---

## 1.12.2-2.2.1
### Added
- Actually Additions Farmer default harvest behavior can now replant crops on modded soils
- Forestry Fruit Pods and Fruit Leaves can now be harvested by supported crop farmers
### Fixed
- Fixed Rats Harvest command being unable to correctly harvest certain crops
- Fixed some modded Farmers being unable to interact with Immersive Engineering Hemp
- Fixed Harvestcraft's right-click bark harvest fix not dropping items on bark interaction

---

## 1.12.2-2.2.0
### Added
- Added Blood Magic tweak for adjusting the LP storage of the Blood Letter's Pack and Coat of Arms
- Added IC2 Crop harvestable integration
- Added Rustic herb harvestable integration, fixing issues with some farming methods
- Actually Additions Farmer can now harvest all supported crops
- Cyclic Gentle Harvester can now harvest all supported crops
- Industrial Foregoing Plant Harvester can now harvest all supported crops
- Rats 'Harvest' command can now harvest all supported crops
- Added harvest support to New Crimson Revelation Mana Beans
- Added configuration to add Nutrition nutrient values to non-food consumable items
- Added ability to lock Thaumcraft item tooltip aspect values behing a Game Stage
- Several automatic feeder items and devices now increase Nutrition nutrient values when used
  - Blood Magic Ritual of the Satiated Stomach
  - Botania Fruit of Grisaia
  - Cyclic Saturation Potion
  - IC2 Quantum Helm
  - Minecraft Saturation Potion
  - New Crimson Revelations Sustainer Verdant Ring
  - Industrial Foregoing Meat Feeder
  - Thaumcraft Sustainer Verdant Charm
  - Tinker's Construct Tasty trait
  - Traveler's Backpack Sunflower Backpack

### Changed
- Configuration was adjusted to fix a number of inconsistent names and broken options be sure to regenerate your configuration after updating

### Fixed
- Fixed a bug causing preventing several config options from syncing correctly on restart
- Fixed Flux Lens failing to load when the Auromeria is enabled
- Fixed a few typos in the Flux Lens Thaumonomicon entry
- Fixed several configuration options displaying features that did not work

---

## 1.12.2-2.1.3
### Added
- Added fix for Animania seed placing dispenser logic when Botania or Quark is loaded
### Changed
- Minor internal tweaks to IntegrationModule API class to improve future additions
- Standardized some non-standard internal configuration names
### Fixed
- Fixed small remap bug with Bound Tool Mixin
- Fixed internal versioning
- Fixed Mystical Agriculture heatable mixin not loading
- Fixed Thaumcraft heatable essentia smelter configuration disable not working

---

## 1.12.2-2.1.2
### Added
- Added Blood Magic ritual fix, fixing the bug that caused rituals to reset on chunk/world reload
### Changed
- Fixes and Tweaks that have been merged into Universal Tweaks will automatically disable themselves when the correct version of UT is loaded
### Fixed
- Fixed mixin remap issue that sometimes caused Rustic condenser mixin to not load

## 1.12.2-2.1.1
### Changed
- Rewrote CFB Oven mixin to use threadsafe `@Share` tag

### Fixed
- Fixed crash when Ender IO installed without Oreberries

---

## 1.12.2-2.1.0
### Added
#### Additions
- Added new Astral Sorcery Crystal Sorter block, used to help automate crystal purity splitting (block texture and model courtesy of [Kombee](https://www.curseforge.com/members/kombee))
  <br><br>

#### Fixes
- Fixed Blood Magic fluid routing unable to support multiple liquids
- Fixed Blood Magic fluid routing locking up when encountering a full fluid tank
- Fixed crash when Ender IO's Farming Station harvested Agricraft crops
- Fixed Pam's Harvestcraft machines being registered as furnace fuel
- Fixed Pam's Harvestcraft bark harvesting launching drops in random directions
<br><br>

#### Integrations/Tweaks
- Astral Sorcery grindstone can sharpen Spartan Weaponry thrown weapons
- Blood Magic Bound Tools right-click harvest has been rewritten to significantly increase performance and fire BlockHarvestEvent
- Blood Magic Ritual of the Crusher now has OreStages support based on player stage
- Blood Magic Ritual of Magnetism no longer leaves empty cavities in the stone
- Blood Magic Ritual of Magnetism now correctly fires the BlockHarvestEvent, allowing access to drop tweaker mods
- Blood Magic Reap of the Harvest Moon now correctly fires the BlockHarvestEvent, allowing access to drop tweaker mods
- Blood Magic Reap of the Harvest Moon can now harvest Oreberries Oreberry Bushes
- Botania's Drum and Horn of the Wild can now harvest Attained Drops 2 crops
- Botania's Drum and Horn of the Wild can now harvest Oreberry Oreberry bushes
- Botania's Drum and Horn of the Wild can now harvest Pam's Harvestcraft fruit and bark
- Ender IO Farming Station can now harvest Agricraft crops
- Ender IO Farming Station can now harvest Attained Drops 2 crops
- Ender IO Farming Station can now harvest Oreberries Oreberry Bushes
  Ender IO Farming Station can now harvest Pam's Harvestcraft fruit and bark
- Roots Soils can now handle Agricraft crops (requires manual json soil settings)
- Thaumcraft Golems can now harvest Agricraft crops
- Thaumcraft Golems can now harvest Attained Drops 2 crops
- Thaumcraft Golems can now harvest Oreberries Oreberry Bushes
- Thaumcraft Golems can now harvest Pam's Harvestcraft fruit and bark
<br><br>

### Changed
- Rewrote Bewitchment Witches' Oven mixins so they're not horrible
- Rewrote Blood Magic Burning Furnace Array mixins for improved compatibility
- Rewrote Botania Exoflame mixins for improved compatibility
- Rewrote Botania Kekimurus mixins for improved compatibility
- Rewrote Cooking For Blockheads Oven mixins so they're not horrible
- Cleaned up and merged a huge amount of unnecessary code

### Fixed
- Fixed Cooking For Blockheads mixin throwing a non-fatal NoClassDefFoundError error
- Fixed Tinker's Construct mixin logging constructor warning message
- Fixed The One Probe OreStages integration lagging servers when looking at staged blocks

---

## 1.12.2-2.0.0
### Changed
<span style="font-size:1.2em;color:red;"><b>
As of version 2.0.0, Magiculture Integrations requires MixinBooter. This change was made to fix a number of bugs as well as significantly increase performance.
</b></span><br><br>
<span style="font-size:1.2em;color:red;"><b>
The survival tools module Magiculture Integrations has been split into its own mod. This was done in order to keep this mod focused on coded integrations and one-off additions.
</b></span>
<br><br>
<span style="font-size:1.2em;color:red;"><b>
The config has changed again (sorry last time I swear) so be sure to regenerate your config as many of the options have been re-organized for easier enabling/disabling of similar features.
</b></span>

### Removed
- Removed Survival Tools Module. All tools, blocks and machines have been shifted into their own mod

### Integrations
- Botantia Kekimurus can now eat Twilight Forest's Experiment 115 (block destruction configurable)
- Engineer's Decor Small Laboratory Furnace can now be heated by all supported heaters/boosters
- Immersive Engineering Coke Oven, Alloy Smelter, and Blast Furnace can now be boosted (but not fueled) by all supported boosters (disabled by default)
- Industrial Craft Blast Furnace, Coke Kiln, and Fermenter can now be boosted (but not fueled) by all supported boosters (disabled by default)
- Quark's Foxhound can now boost the cook speed of all supported furnaces
- Thaumcraft Essentia Smelters can now be heated/boosted by all supported heaters/boosters
- Thaumic Additions crops can now be harvested/boosted by Blood Magic's Reap of the Harvest Moon ritual
- Thaumic Additions Essentia Smelters can now be heated by all supported heaters/boosters
- Tinker's Construct Smeltery and Seared Furnace now can now be boosted (but not fueled) by all supported heaters/boosters

### Fixes
- Fixed Bewitchment's Witches' Oven consuming consuming container items (such as BM's Lava Crystal)
- Fixed Botania's Exoflame causing Future MC's Smoker and Blast Furnace to occasionally flicker
- Fixed Cooking for Blockhead's Oven consuming container items (such as BM's Lava Crystal)
- Fixed Cooking for Blockhead's Oven desync when boosting cook time

---

## 1.12.2-1.3.1
- Fix crash with Auromeria when Botania installed but Thaumcraft not installed
- Fix crash when right-clicking baubles when Botania not installed
- Fix crash with embers when Baubles not installed
- Fixed Gryllzalia render issue when Thaumcraft not installed
- Fixed Gryllzalia loading errored botania flower when TAN/SimpleDifficulty not installed

---

## 1.12.2-1.3.0
<span style="font-size:1.2em;color:red;"><b>The configuration has changed. A number of config options have been renamed or moved. Be sure to regenerate your config file after updating from previous versions.</b></span>

### Features
- Astral Sorcery
  - Right clicking on a block at night with an Ichosic Resonator will display the fluid reservoir contained in that chunk

### Integrations
- Botania
  - Added Auromeria - new Functional Flora that uses Vis/Flux to generate mana bursts
  - Added Gryllzalia - new Functional Flora that uses mana to regulate body temperature of all players in an area
- Blood Magic
  - Added Ritual of the Soothing Hearth - new ritual that regulate body temperature of all players in an area
- The One Probe
  - Added Ore Stages support, staged blocks no longer display error probe tooltips
  - Added Redstone Paste support, devices now show standard redstone information

### Fixes
- Fixed Bewitchment Witch's Oven not lighting up on servers
- Fixed Botania new lexicon entries occasionally not displaying
- Fixed Cooking for Blockheads oven flickering
- Fixed Immersive Engineering External Heater inconsistencies

---

## 1.12.2-1.2.0
### Additions:
- SimpleDifficulty/Tough As Nails:
  - Added Blood Magic lp-powered temperature regulation sigil (Sigil of the Temperate Lands)
    - Includes new Sanguine Scientiem entry
  - Added Blood Magic lp-powered auto-hydration sigil (Sigil of Hydration)
    - Includes new Sanguine Scientiem entry
  - Added Botania mana-powered temperature regulation ring (Ring of Seasons)
    - Includes new Lexica Botania entry
  - Added Embers ember-powered temperature regulation cloak (Mantle Cloak)
    - Includes new Ancient Codex entry
  - Added Nature's Aura aura-powered temperature regulation necklace (Environmental Amulet)
  - Added Thaumcraft vis-powered temperature regulation charm (Thaumic Regulator)
    - Includes new Thaumonomicon entry

### Integrations
- Nature's Aura
  - Spirit of Birthing can now spawn on birth events caused by most modded creatures (Animania Compat)

### Fixes
- Config menu can now be accessed and modified through the in-game Gui

---

## 1.12.2-1.1.0
- Fixed Immersive Engineering External Heater not updating Future MC's Blast Furnace and Smoker
- Fixed crash on servers
- Improved performance when accessing Bewitchment's Witches' Oven and Rustic's Alchemic Condensers

---

## 1.12.2-1.0.0
- Initial Release