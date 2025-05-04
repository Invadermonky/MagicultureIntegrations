package com.invadermonky.magicultureintegrations.integrations.enderio;

import com.invadermonky.magicultureintegrations.api.mods.IntegrationModule;
import com.invadermonky.magicultureintegrations.integrations.enderio.mods.EIOAttainedDrops;
import com.invadermonky.magicultureintegrations.integrations.enderio.mods.EIOHarvestableCrop;
import com.invadermonky.magicultureintegrations.integrations.enderio.mods.EIOOreberries;
import com.invadermonky.magicultureintegrations.util.ModIds;
import crazypants.enderio.api.farm.IFarmerJoe;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class InitEnderIO extends IntegrationModule {
    public InitEnderIO() {
        super("Ender IO");
    }

    @Override
    public void buildModIntegrations() {
        this.integrations.addIntegration(ModIds.attained_drops, EIOAttainedDrops.class);
        this.integrations.addIntegration(ModIds.oreberries, EIOOreberries.class);
    }

    @Override
    public void preInit() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void registerFarmers(RegistryEvent.Register<IFarmerJoe> event) {
        event.getRegistry().register(new EIOHarvestableCrop());
    }
}
