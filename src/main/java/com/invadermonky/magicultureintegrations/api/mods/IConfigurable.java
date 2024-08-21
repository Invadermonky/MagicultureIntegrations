package com.invadermonky.magicultureintegrations.api.mods;

public interface IConfigurable {
    /**
     * Whether this particular feature is loaded. This is most often used to return config enable/disable values. This
     * is not the location for mod loaded checks.
     */
    boolean isEnabled();
}
