package com.invadermonky.magicultureintegrations.config.tags;

import com.invadermonky.magicultureintegrations.config.MIConfigTweaks;
import gnu.trove.map.hash.THashMap;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModTags {
    public static THashMap<Integer, IBlockState> RITUAL_MAGNETIC_REPLACEMENTS = new THashMap<>();

    public static void syncConfig() {
        parseRitualMagneticReplacements();
    }

    private static void parseRitualMagneticReplacements() {
        RITUAL_MAGNETIC_REPLACEMENTS.clear();
        Pattern pattern = Pattern.compile("^(-?\\d+)=([^=\\s]+?):(\\d+)$");
        for (String str : MIConfigTweaks.blood_magic.ritual_magnetic_replacements) {
            Matcher matcher = pattern.matcher(str);
            if (matcher.find()) {
                int dimension = Integer.parseInt(matcher.group(1));
                Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(matcher.group(2)));
                int meta = Integer.parseInt(matcher.group(3));
                if (block != null && block != Blocks.AIR) {
                    RITUAL_MAGNETIC_REPLACEMENTS.put(dimension, block.getStateFromMeta(meta));
                }
            }
        }
    }

}
