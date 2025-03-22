# Changelog
## 1.12.2-2.1.0
### Added
- Added new Astral Sorcery Crystal Sorter block, used to help automate crystal purity splitting
### Changed
- Rewrote Bewitchment Witches' Oven mixins so they're not horrible
- Rewrote CFB Oven mixins so they're not horrible
- Cleaned up and merged a huge amount of unnecessary code
### Fixed
- Fixed Tinker's Construct mixin logging constructor warning message
<br><br>

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
<br><br>
### Removed
- Removed Survival Tools Module. All tools, blocks and machines have been shifted into their own mod
<br><br>

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
<br><br>

### Fixes
- Fixed Bewitchment's Witches' Oven consuming consuming container items (such as BM's Lava Crystal)
- Fixed Botania's Exoflame causing Future MC's Smoker and Blast Furnace to occasionally flicker
- Fixed Cooking for Blockhead's Oven consuming container items (such as BM's Lava Crystal)
- Fixed Cooking for Blockhead's Oven desync when boosting cook time
<br><br>

## 1.12.2-1.3.1
- Fix crash with Auromeria when Botania installed but Thaumcraft not installed
- Fix crash when right-clicking baubles when Botania not installed
- Fix crash with embers when Baubles not installed
- Fixed Gryllzalia render issue when Thaumcraft not installed
- Fixed Gryllzalia loading errored botania flower when TAN/SimpleDifficulty not installed
<br><br>

## 1.12.2-1.3.0
<span style="font-size:1.2em;color:red;"><b>The configuration has changed. A number of config options have been renamed or moved. Be sure to regenerate your config file after updating from previous versions.</b></span>
<br><br>
### Features
- Astral Sorcery
  - Right clicking on a block at night with an Ichosic Resonator will display the fluid reservoir contained in that chunk
<br><br>
### Integrations
- Botania
  - Added Auromeria - new Functional Flora that uses Vis/Flux to generate mana bursts
  - Added Gryllzalia - new Functional Flora that uses mana to regulate body temperature of all players in an area
- Blood Magic
  - Added Ritual of the Soothing Hearth - new ritual that regulate body temperature of all players in an area
- The One Probe
  - Added Ore Stages support, staged blocks no longer display error probe tooltips
  - Added Redstone Paste support, devices now show standard redstone information
<br><br>
### Fixes
- Fixed Bewitchment Witch's Oven not lighting up on servers
- Fixed Botania new lexicon entries occasionally not displaying
- Fixed Cooking for Blockheads oven flickering
- Fixed Immersive Engineering External Heater inconsistencies
<br><br>

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
    - 
### Integrations
- Nature's Aura
  - Spirit of Birthing can now spawn on birth events caused by most modded creatures (Animania Compat)

### Fixes
- Config menu can now be accessed and modified through the in-game Gui
  <br><br>

## 1.12.2-1.1.0
- Fixed Immersive Engineering External Heater not updating Future MC's Blast Furnace and Smoker
- Fixed crash on servers
- Improved performance when accessing Bewitchment's Witches' Oven and Rustic's Alchemic Condensers
  <br><br>

## 1.12.2-1.0.0
- Initial Release