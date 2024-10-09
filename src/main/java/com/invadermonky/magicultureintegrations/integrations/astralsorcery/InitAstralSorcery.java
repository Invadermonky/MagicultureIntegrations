package com.invadermonky.magicultureintegrations.integrations.astralsorcery;

import com.invadermonky.magicultureintegrations.api.mods.IIntegrationModule;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.events.CommonEventHandler;
import com.invadermonky.magicultureintegrations.integrations.astralsorcery.events.ASCommonEvents;
import com.invadermonky.magicultureintegrations.util.IntegrationList;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import org.jetbrains.annotations.Nullable;

public class InitAstralSorcery implements IIntegrationModule {
    private final IntegrationList integrations = new IntegrationList("Astral Sorcery");

    @Override
    public void buildModIntegrations() {

    }

    @Nullable
    @Override
    public IntegrationList getModIntegrations() {
        return this.integrations;
    }

    @Override
    public void preInit() {
        if(ConfigHandlerMI.integrations.astral_sorcery.features.show_reservoir) {
            CommonEventHandler.registerEventSubscriber(PlayerInteractEvent.RightClickBlock.class, new ASCommonEvents());
        }
    }
}
