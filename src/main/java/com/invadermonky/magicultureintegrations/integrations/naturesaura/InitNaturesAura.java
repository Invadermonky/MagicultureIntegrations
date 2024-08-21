package com.invadermonky.magicultureintegrations.integrations.naturesaura;

import com.invadermonky.magicultureintegrations.api.mods.IIntegrationModule;
import com.invadermonky.magicultureintegrations.events.CommonEventHandler;
import com.invadermonky.magicultureintegrations.init.RegistrarMI;
import com.invadermonky.magicultureintegrations.integrations.naturesaura.compat.*;
import com.invadermonky.magicultureintegrations.integrations.naturesaura.events.NACommonEventHandler;
import com.invadermonky.magicultureintegrations.integrations.naturesaura.events.NAExternalHeaterHandler;
import com.invadermonky.magicultureintegrations.integrations.naturesaura.item.ItemTemperatureAmulet;
import com.invadermonky.magicultureintegrations.util.IntegrationList;
import com.invadermonky.magicultureintegrations.util.ModIds;
import de.ellpeck.naturesaura.blocks.tiles.TileEntityFurnaceHeater;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import org.jetbrains.annotations.Nullable;

public class InitNaturesAura implements IIntegrationModule {
    private final IntegrationList integrations = new IntegrationList("Nature's Aura");

    @Override
    public void buildModIntegrations() {
        integrations.addIntegration(ModIds.bewitchment, NABewitchment.class);
        integrations.addIntegration(ModIds.cooking_for_blockheads, NACookingForBlockheads.class);
        integrations.addIntegration(ModIds.futuremc, NAFutureMC.class);
        integrations.addIntegration(ModIds.mystical_agriculture, NAMysticalAgriculture.class);
        integrations.addIntegration(ModIds.rustic, NARustic.class);
    }

    @Nullable
    @Override
    public IntegrationList getModIntegrations() {
        return this.integrations;
    }

    @Override
    public void preInit() {
        if((ModIds.simpledifficulty.isLoaded || ModIds.tough_as_nails.isLoaded) && ModIds.baubles.isLoaded) {
            RegistrarMI.registerItem(ItemTemperatureAmulet.TEMPERATURE_AMULET);
        }
        /*
        PatchouliAPI.instance.setConfigFlag("magicultureintegrations:temperature_amulet",
                (ModIds.simpledifficulty.isLoaded || ModIds.tough_as_nails.isLoaded) &&
                        ModIds.baubles.isLoaded &&
                        ItemTemperatureAmulet.TEMPERATURE_AMULET.isEnabled()
        );
        */
    }

    @Override
    public void init() {
        CommonEventHandler.registerEventSubscriber(BabyEntitySpawnEvent.class, new NACommonEventHandler());

        if(!NAExternalHeaterHandler.naHeaterHeatableMap.isEmpty()) {
            CommonEventHandler.registerTileTickSubscriber(TileEntityFurnaceHeater.class, new NAExternalHeaterHandler());
        }
    }

    @Override
    public void postInit() {}
}
