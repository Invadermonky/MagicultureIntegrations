package com.invadermonky.magicultureintegrations.integrations.naturesaura.events;

import com.invadermonky.magicultureintegrations.MagicultureIntegrations;
import com.invadermonky.magicultureintegrations.api.events.IConfigChangedEvent;
import com.invadermonky.magicultureintegrations.util.ModIds;

public class NAExtendedConfigHandler implements IConfigChangedEvent {
    @Override
    public void onExternalConfigChanged(String modId) {
        if(modId.equals(ModIds.natures_aura.modId) || modId.equals(MagicultureIntegrations.MOD_ID)) {
            //TODO: Extended config
        }
    }
}
