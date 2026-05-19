package com.invadermonky.magicultureintegrations.config;

import com.cleanroommc.configanytime.ConfigAnytime;
import com.invadermonky.magicultureintegrations.MagicultureIntegrations;
import com.invadermonky.magicultureintegrations.config.tags.ModTags;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config.LangKey("config." + MagicultureIntegrations.MOD_ID + ":fixes")
@Config(
        modid = MagicultureIntegrations.MOD_ID,
        name = MagicultureIntegrations.MOD_ID + "/" + MagicultureIntegrations.MOD_NAME + " - Fixes"
)
public class MIConfigFixes {
    @Config.Name("Actually Additions")
    public static ActuallyAdditionsFixes actually_additions = new ActuallyAdditionsFixes();
    @Config.Name("Agricraft")
    public static AgricraftFixes agricraft = new AgricraftFixes();
    @Config.Name("Animania")
    public static AnimaniaFixes animania = new AnimaniaFixes();
    @Config.Name("Harvestcraft")
    public static HarvestcraftFixes harvestcraft = new HarvestcraftFixes();

    public static class ActuallyAdditionsFixes {
        @Config.RequiresMcRestart
        @Config.Name("Fix Default Farmer Replant Behavior")
        @Config.Comment("Fixes Actually Additions Farmer not replanting crops on modded soils")
        public boolean fixDefaultFarmerReplant = true;
    }

    public static class AgricraftFixes {
        @Config.RequiresMcRestart
        @Config.Name("Fix Ender IO Plugin")
        @Config.Comment
                ({
                        "Fixes broken Ender IO integration that causes Farming Station crash. This fix is included in",
                        "Universal Tweaks 1.15.0 and will disable itself if Universal Tweaks is present."
                })
        public boolean fixEnderioPlugin = true;
    }

    public static class AnimaniaFixes {
        @Config.RequiresMcRestart
        @Config.Name("Fix Dispenser Logic")
        @Config.Comment("Fixes seed placement dispenser logic when Botania or Quark is loaded")
        public boolean fixDispenserLogic = true;
    }

    public static class HarvestcraftFixes {
        @Config.RequiresMcRestart
        @Config.Name("Burnable Machine Fix")
        @Config.Comment("Removes the burntime from Harvestcraft machinery.")
        public boolean fixMachineBurntime = true;

        @Config.RequiresMcRestart
        @Config.Name("Bark Harvest Fix")
        @Config.Comment("Fixes bark harvesting causing items to launch in random directions.")
        public boolean fixBarkHarvest = true;
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
        ConfigAnytime.register(MIConfigFixes.class);
    }
}
