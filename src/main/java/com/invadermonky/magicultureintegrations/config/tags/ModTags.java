package com.invadermonky.magicultureintegrations.config.tags;

import com.invadermonky.magicultureintegrations.config.MIConfigTweaks;
import com.invadermonky.magicultureintegrations.util.ConfigHelper;
import gnu.trove.map.hash.THashMap;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModTags {
    public static Map<Integer, IBlockState> RITUAL_MAGNETIC_REPLACEMENTS = new THashMap<>();

    public static void syncConfig() {
        parseRitualMagneticReplacements();
    }

    private static void parseRitualMagneticReplacements() {
        RITUAL_MAGNETIC_REPLACEMENTS.clear();
        Pattern pattern = Pattern.compile("^(-?\\d+)=([^=\\s]+?):(\\d+)$");
        for (String str : MIConfigTweaks.blood_magic.ritualMagneticTweakReplacements) {
            Matcher matcher = pattern.matcher(str.trim());
            if (matcher.find()) {
                int dimension = Integer.parseInt(matcher.group(1));
                IBlockState state = ConfigHelper.parseBlockState(matcher.group(2), Integer.parseInt(matcher.group(3)));
                if (state != null && state != Blocks.AIR) {
                    RITUAL_MAGNETIC_REPLACEMENTS.put(dimension, state);
                }
            }
        }
    }

    static {
        syncConfig();
    }
}
