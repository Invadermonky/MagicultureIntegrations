package com.invadermonky.magicultureintegrations.integrations.astralsorcery;

import com.invadermonky.magicultureintegrations.api.mods.IntegrationModule;
import com.invadermonky.magicultureintegrations.config.MIConfigTweaks;
import com.invadermonky.magicultureintegrations.integrations.astralsorcery.block.BlockCrystalSorter;
import com.invadermonky.magicultureintegrations.integrations.astralsorcery.events.ASEventSubscriber;
import com.invadermonky.magicultureintegrations.integrations.astralsorcery.mods.ASSpartanWeaponry;
import com.invadermonky.magicultureintegrations.registry.RegistrarMI;
import com.invadermonky.magicultureintegrations.util.ModIds;
import net.minecraftforge.common.MinecraftForge;

public class InitAstralSorcery extends IntegrationModule {
    public static BlockCrystalSorter crystal_sorter = new BlockCrystalSorter();

    public InitAstralSorcery() {
        super("Astral Sorcery");
    }

    @Override
    public void buildModIntegrations() {
        this.integrations.addIntegration(ModIds.spartan_weaponry, ASSpartanWeaponry.class);
    }

    @Override
    public void preInit() {
        if (MIConfigTweaks.astral_sorcery.show_reservoir) {
            MinecraftForge.EVENT_BUS.register(new ASEventSubscriber());
        }

        RegistrarMI.registerAddition(crystal_sorter);
    }
}
