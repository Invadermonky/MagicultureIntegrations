package com.invadermonky.magicultureintegrations.config;

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
    @Config.Name("Agricraft")
    public static AgricraftFixes agricraft = new AgricraftFixes();
    @Config.Name("Bewitchment")
    public static BewitchemntFixes bewitchment = new BewitchemntFixes();
    @Config.Name("Blood Magic")
    public static BloodMagicFixes blood_magic = new BloodMagicFixes();
    @Config.Name("Cooking for Blockheads")
    public static CookingForBlockheadsFixes cooking_for_blockheads = new CookingForBlockheadsFixes();
    @Config.Name("Harvestcraft")
    public static HarvestcraftFixes harvestcraft = new HarvestcraftFixes();

    public static class AgricraftFixes {
        @Config.RequiresMcRestart
        @Config.Name("Fix Ender IO Plugin")
        @Config.Comment("Fixes broken Ender IO integration that causes Farming Station crash.")
        public boolean fix_enderio_plugin = true;
    }

    public static class BewitchemntFixes {
        @Config.RequiresMcRestart
        @Config.Name("Witches' Oven Fix")
        @Config.Comment("Fixes Witches' Oven consuming container fuel items.")
        public boolean fix_witches_oven = true;
    }

    public static class BloodMagicFixes {
        @Config.RequiresMcRestart
        @Config.Name("Fluid Routing Node Fix")
        @Config.Comment
                ({
                        "Fixes Routing Node fluid routing unable to support multiple fluids and fixes fluid routing getting",
                        "stuck on the first full fluid tank encountered."
                })
        public boolean fix_fluid_routing = true;
    }

    public static class CookingForBlockheadsFixes {
        @Config.RequiresMcRestart
        @Config.Name("Oven Fix")
        @Config.Comment("Fixes Oven consuming container fuel items.")
        public boolean fix_oven = true;
    }

    public static class HarvestcraftFixes {
        @Config.RequiresMcRestart
        @Config.Name("Burnable Machine Fix")
        @Config.Comment("Removes the burntime from Harvestcraft machinery.")
        public boolean fix_machine_burntime = true;

        @Config.RequiresMcRestart
        @Config.Name("Bark Harvest Fix")
        @Config.Comment("Fixes bark harvesting causing items to launch in random directions.")
        public boolean fix_bark_harvest = true;
    }

    @Mod.EventBusSubscriber(modid = MagicultureIntegrations.MOD_ID)
    public static class ConfigChangeListener {
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
            if(event.getModID().equals(MagicultureIntegrations.MOD_ID)) {
                ConfigManager.sync(MagicultureIntegrations.MOD_ID, Config.Type.INSTANCE);
                ModTags.syncConfig();
            }
        }
    }
}
