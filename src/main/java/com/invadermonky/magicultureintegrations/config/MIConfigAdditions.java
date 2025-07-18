package com.invadermonky.magicultureintegrations.config;

import com.cleanroommc.configanytime.ConfigAnytime;
import com.invadermonky.magicultureintegrations.MagicultureIntegrations;
import com.invadermonky.magicultureintegrations.config.tags.ModTags;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config.LangKey("config." + MagicultureIntegrations.MOD_ID + ":additions")
@Config(
        modid = MagicultureIntegrations.MOD_ID,
        name = MagicultureIntegrations.MOD_ID + "/" + MagicultureIntegrations.MOD_NAME + " - Additions"
)
public class MIConfigAdditions {
    @Config.Name("Astral Sorcery")
    public static ConfigAstralSorceryAdditions astral_sorcery = new ConfigAstralSorceryAdditions();
    @Config.Name("Botania")
    public static ConfigBotaniaAdditions botania = new ConfigBotaniaAdditions();
    @Config.Name("Thaumcraft")
    public static ConfigThaumcraftAdditions thaumcraft = new ConfigThaumcraftAdditions();


    public static class ConfigAstralSorceryAdditions {
        @Config.Name("Resonance Analyzer")
        @Config.Comment("Astral Sorcery machine used to sort celestial crystals based on size and purity.")
        public CrystalSorter crystal_sorter = new CrystalSorter();

        public static class CrystalSorter {
            @Config.RequiresMcRestart
            @Config.Comment("Enables the Resonance Analyzer.")
            public boolean enableCrystalSorter = true;

            @Config.Comment("The Crystal Sorter will sort sort respecting size in addition to purity.")
            public boolean sortRespectingSize = true;
        }
    }

    public static class ConfigBotaniaAdditions {
        @Config.Name("Auromeria")
        @Config.Comment
                ({
                        "Botania functional flower. Uses Thaumcraft vis and mana to generate mana bursts. See Lexicon entry for",
                        "more details"
                })
        public Auromeria auromeria = new Auromeria();

        public static class Auromeria {
            @Config.RequiresMcRestart
            @Config.Comment("Enables the Auromeria flower and the Flux Lens.")
            public boolean enableAuromeria = true;
            @Config.Comment("The amount of damage a flux mana burst deals when colliding with an entity.")
            public float fluxBurstDamage = 12.0f;
            @Config.Comment
                    ({
                            "The amount of flux drained from the environment when the Auromeria drains flux when creating a mana",
                            "burst."
                    })
            public double fluxDrainAmount = 0.05;
            @Config.Comment("The base chance that the Auromeria will drain flux when generating a mana burst.")
            public double fluxDrainChance = 0.05;
            @Config.Comment("The amount of flux generated when a flux mana burst explodes.")
            public double pollutionAmount = 0.2;
            @Config.Comment("The chance of aura pollution when a flux mana burst explodes.")
            public double pollutionChance = 0.5;
        }
    }

    public static class ConfigThaumcraftAdditions {
        @Config.RequiresMcRestart
        @Config.Name("Revealing Infusion Enchantment")
        @Config.Comment("Enables the Revealing infusion enchantment, allowing all non-goggle helmets to display Aspects on valid blocks.")
        public boolean enableRevealingInfusion = true;
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
        ConfigAnytime.register(MIConfigAdditions.class);
    }
}
