package com.invadermonky.magicultureintegrations.api.mods;

import com.invadermonky.magicultureintegrations.util.IntegrationList;

import javax.annotation.Nullable;

public interface IIntegrationModule extends IModIntegration {
    void buildModIntegrations();

    @Nullable
    IntegrationList getModIntegrations();
}
