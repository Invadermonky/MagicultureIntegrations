package com.invadermonky.magicultureintegrations.api.mods;

import com.invadermonky.magicultureintegrations.api.IProxy;
import com.invadermonky.magicultureintegrations.util.IntegrationList;

public interface IIntegrationModule extends IProxy {
    void buildModIntegrations();

    IntegrationList getModIntegrations();
}
