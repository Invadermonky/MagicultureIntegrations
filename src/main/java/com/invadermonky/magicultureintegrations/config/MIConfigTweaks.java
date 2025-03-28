package com.invadermonky.magicultureintegrations.config;

import com.cleanroommc.configanytime.ConfigAnytime;
import com.invadermonky.magicultureintegrations.MagicultureIntegrations;
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
        @Config.Comment("Right clicking on a block at night while holding an Ichosic Resonator will display the fluid reservoir contained in that chunk.")
        public boolean show_reservoir = true;
    }

    public static class BloodMagicTweaks {
        @Config.RequiresMcRestart
        @Config.Name("Bound Tool Tweak")
        @Config.Comment
                ({
                        "Overhauls bound tool right-click harvest, significantly improving performance and firing the HarvestBlockEvent,",
                        "allowing drop modification through tweaker mods."
                })
        public boolean bound_tool_tweak = true;

        @Config.RequiresMcRestart
        @Config.Name("Cutting Fluid Tweak")
        @Config.Comment("Enables Cutting Fluid/Explosive Powder tweaks.")
        public boolean cutting_fluid_tweak = true;
        @Config.Comment("Maximum Cutting Fluid uses before being consumed.")
        public int cuttingFluidMaxUses = 16;
        @Config.Comment("Maximum Explosive Powder uses before being consumed.")
        public int explosivePowderMaxUses = 64;

        @Config.RequiresMcRestart
        @Config.Name("Ritual of the Crusher")
        @Config.Comment("Ritual of the Crusher will now fire the HarvestBlockEvent, allowing drop modification through tweaker mods.")
        public boolean ritual_crusher = true;

        @Config.RequiresMcRestart
        @Config.Name("Reap of the Harvest Moon")
        @Config.Comment("Reap of the Harvest Moon ritual will now fire the HarvestBlockEvent, allowing drop modification through tweaker mods.")
        public boolean ritual_harvest = true;

        @Config.RequiresMcRestart
        @Config.Name("Ritual of Magnetism")
        @Config.Comment
                ({
                        "The Ritual of Magnetism will now replace the blocks it pulls from the ground with stone. This will improve",
                        "performance in areas that have been stripped by the ritual by preventing the ritual from leaving empty cavities",
                        "in the stone."
                })
        public boolean ritual_magnetic_replace = true;

        @Config.Name("Ritual of Magnetism Block Replacements")
        @Config.Comment
                ({
                        "Dimension specific block overrides. The default stone block will be replaced by these values in the configured",
                        "dimension.",
                        "  Format: dimensionId=modid:blockid:meta",
                        "  0=minecraft:stone:0"
                })
        public String[] ritual_magnetic_replacements = {
                "-1=minecraft:netherrack:0",
                "1=minecraft:end_stone:0"
        };

    }

    public static class NaturesAuraTweaks {
        @Config.RequiresMcRestart
        @Config.Name("Lax Spirit of Birthing")
        @Config.Comment("Reduces the requirements needed to spawn a Spirit of Birthing, fixing an issue that prevented some\n" +
                "modded animals from spawning them when giving birth in high-aura chunks.")
        public boolean loose_birthing_spirit = true;
    }

    public static class QualityToolsTweaks {
        @Config.RequiresMcRestart
        @Config.Name("Reforging Station Quality Text")
        @Config.Comment("Adds item quality name to the Reforging Station GUI.")
        public boolean gui_quality_text = true;
        @Config.Name("Gui Text x-offset")
        @Config.Comment("The xOffset from center of the Reforging Station quality text. Increasing this value will shift the text right.")
        public int gui_quality_text_xOffset = 15;
        @Config.Name("Gui Text y-offset")
        @Config.Comment("The yOffset from center of the Reforging Station quality text. Increasing this value will shift the text down.")
        public int gui_quality_text_yOffset = 23;
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

    static {
        ConfigAnytime.register(MIConfigTweaks.class);
    }
}
