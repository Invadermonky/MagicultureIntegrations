package com.invadermonky.magicultureintegrations.api.tile;

import gnu.trove.map.hash.THashMap;
import gnu.trove.set.hash.THashSet;
import net.minecraft.tileentity.TileEntity;

public class HeatableUtils {
    private static final THashMap<Class<?>, THashSet<Class<? extends TileEntity>>> HEATABLE_BLACKLIST = new THashMap<>();

    public static void blacklistHeatable(Class<?> providerClass, Class<? extends TileEntity> heatable) {
        if(!HEATABLE_BLACKLIST.containsKey(providerClass)) {
            HEATABLE_BLACKLIST.put(providerClass, new THashSet<>());
        }
        HEATABLE_BLACKLIST.get(providerClass).add(heatable);
    }

    public static boolean isHeatableBlacklisted(Class<?> provider, TileEntity heatable) {
        return HEATABLE_BLACKLIST.containsKey(provider) && (HEATABLE_BLACKLIST.get(provider).contains(heatable.getClass()) || HEATABLE_BLACKLIST.get(provider).contains(heatable.getClass().getSuperclass()));
    }
}
