package com.invadermonky.magicultureintegrations.integrations.naturesaura.compat;

import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.mods.cookingforblockheads.CookingForBlockheadsHeatable;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.integrations.naturesaura.events.NAExternalHeaterHandler;
import net.blay09.mods.cookingforblockheads.tile.TileOven;

public class NACookingForBlockheads implements IModIntegration {
    @Override
    public void preInit() {
        if(ConfigHandlerMI.natures_aura.extraneous_firestarter.cooking_for_blockheads) {
            NAExternalHeaterHandler.registerExternalHeaterHeatable(TileOven.class, CookingForBlockheadsHeatable.class);
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void postInit() {

    }
}
