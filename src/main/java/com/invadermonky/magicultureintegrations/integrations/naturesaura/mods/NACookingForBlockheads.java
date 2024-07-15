package com.invadermonky.magicultureintegrations.integrations.naturesaura.mods;

import com.invadermonky.magicultureintegrations.api.mods.cookingforblockheads.CookingForBlockheadsHeatable;
import com.invadermonky.magicultureintegrations.api.mods.naturesaura.INAIntegration;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.integrations.naturesaura.events.NAExternalHeaterHandler;
import net.blay09.mods.cookingforblockheads.tile.TileOven;

public class NACookingForBlockheads implements INAIntegration {
    @Override
    public void registerExtraneousHeaterHandler() {
        if(ConfigHandlerMI.natures_aura.extraneous_firestarter.cooking_for_blockheads) {
            NAExternalHeaterHandler.registerExternalHeaterHeatable(TileOven.class, CookingForBlockheadsHeatable.class);
        }
    }
}
