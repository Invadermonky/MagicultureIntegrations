package com.invadermonky.magicultureintegrations.util;

import com.invadermonky.magicultureintegrations.MagicultureIntegrations;

public class StringHelper {
    public static String getTranslationKey(String name, String type, String... params) {
        StringBuilder s = new StringBuilder(type + "." + MagicultureIntegrations.MOD_ID + ":" + name);
        for(String p : params) {
            s.append(".").append(p);
        }
        return s.toString();
    }
}
