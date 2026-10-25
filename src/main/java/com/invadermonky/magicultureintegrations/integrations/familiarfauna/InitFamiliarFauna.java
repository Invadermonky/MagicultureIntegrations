package com.invadermonky.magicultureintegrations.integrations.familiarfauna;

import com.invadermonky.magicultureintegrations.api.mods.IntegrationModule;
import com.invadermonky.magicultureintegrations.config.MIConfigFixes;
import com.invadermonky.magicultureintegrations.integrations.familiarfauna.events.FFEventHandler;
import net.minecraftforge.common.MinecraftForge;

public class InitFamiliarFauna extends IntegrationModule {
    public InitFamiliarFauna() {
        super("Familiar Fauna");
    }

    @Override
    public void buildModIntegrations() {
    }

    @Override
    public void preInit() {
        if(MIConfigFixes.familiar_fauna.fixGenderBirthing) {
            MinecraftForge.EVENT_BUS.register(new FFEventHandler());
        }
    }
}
