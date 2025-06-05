package com.invadermonky.magicultureintegrations.config.mods;

import net.minecraftforge.common.config.Config;

public class BloodMagicTweaks {
    @Config.RequiresMcRestart
    @Config.Name("Bound Tool Tweak")
    @Config.Comment
            ({
                    "Overhauls bound tool right-click harvest, significantly improving performance and firing the",
                    "HarvestBlockEvent, allowing drop modification through tweaker mods.  This fix is included in",
                    "Universal Tweaks 1.15.0 and will disable itself if UT is present."
            })
    public boolean boundToolTweak = true;

    @Config.RequiresMcRestart
    @Config.Name("Cutting Fluid Tweak")
    @Config.Comment("Enables Cutting Fluid/Explosive Powder tweaks.")
    public boolean cuttingFluidTweak = true;

    @Config.Name("Cutting Fluid Max Uses")
    @Config.Comment("Maximum Cutting Fluid uses before being consumed.")
    public int cuttingFluidMaxUses = 16;

    @Config.Name("Explosive Powder Max Uses")
    @Config.Comment("Maximum Explosive Powder uses before being consumed.")
    public int explosivePowderMaxUses = 64;

    @Config.Name("Ritual of the Crusher Tweaks")
    public RitualCrusherTweaks ritualCrusherTweaks = new RitualCrusherTweaks();
    @Config.Name("Reap of the Harvest Moon Tweaks")
    public RitualHarvestTweaks ritualHarvestTweaks = new RitualHarvestTweaks();
    @Config.Name("Ritual of Magnetism Tweaks")
    public RitualMagneticTweaks ritualMagneticTweaks = new RitualMagneticTweaks();

    public static class RitualCrusherTweaks {
        @Config.RequiresMcRestart
        @Config.Name("Ritual of the Crusher Tweak")
        @Config.Comment
                ({
                        "Ritual of the Crusher will now fire the HarvestBlockEvent, allowing drop modification",
                        "through tweaker mods."
                })
        public boolean ritualCrusherTweak = true;
    }

    public static class RitualHarvestTweaks {
        @Config.RequiresMcRestart
        @Config.Name("Reap of the Harvest Moon Tweak")
        @Config.Comment
                ({
                        "Reap of the Harvest Moon ritual will now fire the HarvestBlockEvent, allowing drop modification",
                        "through tweaker mods."
                })
        public boolean ritualHarvestTweak = true;
    }

    public static class RitualMagneticTweaks {
        @Config.RequiresMcRestart
        @Config.Name("Ritual of Magnetism Tweak")
        @Config.Comment
                ({
                        "The Ritual of Magnetism will now replace the blocks it pulls from the ground with stone. This will",
                        "improve performance in areas that have been stripped by the ritual by preventing the ritual from",
                        "leaving empty cavities in the stone."
                })
        public boolean ritualMagneticTweak = true;

        @Config.Name("Ritual of Magnetism Block Replacements")
        @Config.Comment
                ({
                        "Dimension specific block overrides. The default stone block will be replaced by these values in the configured",
                        "dimension.",
                        "  Format: dimensionId=modid:blockid:meta",
                        "  0=minecraft:stone:0"
                })
        public String[] ritualMagneticTweakReplacements = {
                "-1=minecraft:netherrack:0",
                "1=minecraft:end_stone:0"
        };
    }

}
