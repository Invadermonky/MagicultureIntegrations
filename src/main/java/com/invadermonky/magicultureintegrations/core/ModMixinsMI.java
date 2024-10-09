package com.invadermonky.magicultureintegrations.core;

import com.invadermonky.magicultureintegrations.MagicultureIntegrations;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.util.LogHelper;
import com.invadermonky.magicultureintegrations.util.ModIds;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import zone.rong.mixinbooter.ILateMixinLoader;

import java.util.ArrayList;
import java.util.List;

public class ModMixinsMI implements ILateMixinLoader {
    private final List<String> MIXINS = new ArrayList<>();

    private void loadMixin(ModIds mod, boolean forceDisable, ModIds... dependencies) {
        if(forceDisable)
            return;

        boolean toLoad = mod.isLoaded;
        if(toLoad) {
            for(ModIds other : dependencies) {
                if(!other.isLoaded) {
                    toLoad = false;
                    break;
                }
            }
        }
        if(toLoad) {
            this.MIXINS.add("mixins." + MagicultureIntegrations.MOD_ID + "." + mod.modId + ".json");
        }
    }

    private void loadCustomMixin(ModIds mod, String customName, boolean forceDisable, ModIds... dependencies) {
        if(forceDisable)
            return;

        boolean toLoad = mod.isLoaded;
        if(toLoad) {
            for(ModIds other : dependencies) {
                if(!other.isLoaded) {
                    toLoad = false;
                    break;
                }
            }
        }
        if(toLoad) {
            this.MIXINS.add("mixins." + MagicultureIntegrations.MOD_ID + "." + mod.modId + "." + customName + ".json");
        }
    }

    @Override
    public List<String> getMixinConfigs() {
        LogHelper.info("Initializing mixins.");

        ConfigManager.sync(MagicultureIntegrations.MOD_ID, Config.Type.INSTANCE);

        loadMixin(ModIds.bewitchment, ConfigHandlerMI._mixins.disableBewitchmentMixins);
        loadMixin(ModIds.bloodmagic, ConfigHandlerMI._mixins.disableBloodMagicMixins);
        loadMixin(ModIds.botania, ConfigHandlerMI._mixins.disableBotaniaMixins);
        loadMixin(ModIds.cooking_for_blockheads, ConfigHandlerMI._mixins.disableCookingForBlockheadsMixins);
        loadMixin(ModIds.engineers_decor, ConfigHandlerMI._mixins.disableEngineersDecorMixins);
        loadMixin(ModIds.futuremc, ConfigHandlerMI._mixins.disableFutureMcMixins);
        loadMixin(ModIds.mystical_agriculture, ConfigHandlerMI._mixins.disableMysticalAgricultureMixins);
        loadMixin(ModIds.natures_aura, ConfigHandlerMI._mixins.disableNaturesAuraMixins);
        loadMixin(ModIds.rustic, ConfigHandlerMI._mixins.disableRusticMixins);
        loadMixin(ModIds.thaumcraft, ConfigHandlerMI._mixins.disableThaumcraftMixins);
        loadMixin(ModIds.thaumadditions, ConfigHandlerMI._mixins.disableThaumicAdditionsMixins);
        loadMixin(ModIds.tinkers_construct, ConfigHandlerMI._mixins.disableTinkersConstructMixins);
        loadMixin(ModIds.twilight_forest, ConfigHandlerMI._mixins.disableTwilightForestMixins, ModIds.botania);

        loadCustomMixin(ModIds.immersive_engineering, "alloysmelter", ConfigHandlerMI._mixins.disableImmersiveEngineeringMixins || ConfigHandlerMI.heatables.immersive_engineering.alloy_smelter._globalDisable);
        loadCustomMixin(ModIds.immersive_engineering, "blastfurnace", ConfigHandlerMI._mixins.disableImmersiveEngineeringMixins || ConfigHandlerMI.heatables.immersive_engineering.blast_furnace._globalDisable);
        loadCustomMixin(ModIds.immersive_engineering, "cokeoven", ConfigHandlerMI._mixins.disableImmersiveEngineeringMixins || ConfigHandlerMI.heatables.immersive_engineering.coke_oven._globalDisable);
        loadCustomMixin(ModIds.industrial_craft, "blastfurnace", ConfigHandlerMI._mixins.disableIndustrialCraftMixins || ConfigHandlerMI.heatables.industrial_craft.blast_furnace._globalDisable);
        /*  Broken, see TileEntityFermenterMixin for information.
            loadCustomMixin(ModIds.industrial_craft, "fermenter", ConfigHandlerMI._mixins.forceDisableIndustrialCraftMixins || ConfigHandlerMI.heatables.industrial_craft.coke_kiln._globalDisable);
         */
        loadCustomMixin(ModIds.industrial_craft, "cokekiln", ConfigHandlerMI._mixins.disableIndustrialCraftMixins || ConfigHandlerMI.heatables.industrial_craft.fermenter._globalDisable);

        return this.MIXINS;
    }

    @Override
    public boolean shouldMixinConfigQueue(String mixinConfig) {
        return MIXINS.contains(mixinConfig);
    }
}
