package com.invadermonky.magicultureintegrations.integrations.astralsorcery;

import com.invadermonky.magicultureintegrations.api.mods.IIntegrationModule;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.integrations.astralsorcery.block.BlockCrystalSorter;
import com.invadermonky.magicultureintegrations.integrations.astralsorcery.events.ASEventSubscriber;
import com.invadermonky.magicultureintegrations.registry.RegistrarMI;
import com.invadermonky.magicultureintegrations.util.IntegrationList;
import net.minecraftforge.common.MinecraftForge;
import org.jetbrains.annotations.NotNull;

public class InitAstralSorcery implements IIntegrationModule {
    private final IntegrationList integrations = new IntegrationList("Astral Sorcery");

    public static BlockCrystalSorter crystal_sorter = new BlockCrystalSorter();

    @Override
    public void buildModIntegrations() {}

    @Override
    public @NotNull IntegrationList getModIntegrations() {
        return this.integrations;
    }

    @Override
    public void preInit() {
        if(ConfigHandlerMI.integrations.astral_sorcery.show_reservoir) {
            MinecraftForge.EVENT_BUS.register(new ASEventSubscriber());
        }

        RegistrarMI.registerAddition(crystal_sorter);
    }
}
