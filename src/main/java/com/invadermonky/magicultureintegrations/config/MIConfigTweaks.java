package com.invadermonky.magicultureintegrations.config;

import com.cleanroommc.configanytime.ConfigAnytime;
import com.invadermonky.magicultureintegrations.MagicultureIntegrations;
import com.invadermonky.magicultureintegrations.config.mods.BloodMagicTweaks;
import com.invadermonky.magicultureintegrations.config.tags.ModTags;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config.LangKey("config." + MagicultureIntegrations.MOD_ID + ":tweaks")
@Config(
        modid = MagicultureIntegrations.MOD_ID,
        name = MagicultureIntegrations.MOD_ID + "/" + MagicultureIntegrations.MOD_NAME + " - Tweaks"
)
public class MIConfigTweaks {
    @Config.Name("Astral Sorcery")
    public static AstralSorceryTweaks astral_sorcery = new AstralSorceryTweaks();
    @Config.Name("Blood Magic")
    public static BloodMagicTweaks blood_magic = new BloodMagicTweaks();
    @Config.Name("Nature's Aura")
    public static NaturesAuraTweaks natures_aura = new NaturesAuraTweaks();
    @Config.Name("Quality Tools")
    public static QualityToolsTweaks quality_tools = new QualityToolsTweaks();

    public static class AstralSorceryTweaks {
        @Config.RequiresMcRestart
        @Config.Name("Show Fluid Reservoir")
        @Config.Comment
                ({
                        "Right clicking on a block at night while holding an Ichosic Resonator will display the fluid",
                        "reservoir contained in that chunk."
                })
        public boolean showReservoir = true;
    }

    public static class NaturesAuraTweaks {
        @Config.RequiresMcRestart
        @Config.Name("Lax Spirit of Birthing")
        @Config.Comment("Reduces the requirements needed to spawn a Spirit of Birthing, fixing an issue that prevented some\n" +
                "modded animals from spawning them when giving birth in high-aura chunks.")
        public boolean birthingSpiritTweak = true;
    }

    public static class QualityToolsTweaks {
        @Config.RequiresMcRestart
        @Config.Name("Reforging Station Quality Text")
        @Config.Comment("Adds item quality name to the Reforging Station GUI.")
        public boolean gui_quality_text = true;
        @Config.Name("Gui Text x-offset")
        @Config.Comment
                ({
                        "The xOffset from center of the Reforging Station quality text. Increasing this value will shift the",
                        "text right."
                })
        public int gui_quality_text_xOffset = 15;
        @Config.Name("Gui Text y-offset")
        @Config.Comment
                ({
                        "The yOffset from center of the Reforging Station quality text. Increasing this value will shift the",
                        "text down."
                })
        public int gui_quality_text_yOffset = 23;
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
        ConfigAnytime.register(MIConfigTweaks.class);
    }
}
