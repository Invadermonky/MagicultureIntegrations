package com.invadermonky.magicultureintegrations.util;

import com.invadermonky.magicultureintegrations.MagicultureIntegrations;

public class StringHelper {
    public static String getTranslationKey(String name, String type, String... params) {
        StringBuilder s = new StringBuilder(type + "." + MagicultureIntegrations.MOD_ID + ":" + name);
        for (String p : params) {
            s.append(".").append(p);
        }
        return s.toString();
    }

    public static String getMixinString(ModIds modId, String... params) {
        StringBuilder s = new StringBuilder("mixins.mi." + modId.modId);
        for (String param : params) {
            s.append(".").append(param);
        }
        s.append(".json");
        return s.toString();
    }

    public static String getVanillaMixinString(String... params) {
        StringBuilder s = new StringBuilder("mixins.mi.minecraft");
        for (String param : params) {
            s.append(".").append(param);
        }
        s.append(".json");
        return s.toString();
    }
}
