package com.invadermonky.magicultureintegrations.integrations.thaumcraft.mods;

import com.invadermonky.magicultureintegrations.api.mods.cookingforblockheads.CookingForBlockheadsHeatable;
import com.invadermonky.magicultureintegrations.api.mods.thaumcraft.ITCIntegration;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.integrations.thaumcraft.events.TCBellowsHandler;
import net.blay09.mods.cookingforblockheads.tile.TileOven;

public class TCCookingForBlockheads implements ITCIntegration {
    @Override
    public void registerBellowsHandler() {
        if(ConfigHandlerMI.thaumcraft.arcane_bellows.cooking_for_blockheads)
            TCBellowsHandler.registerBellowsHeatable(TileOven.class, CookingForBlockheadsHeatable.class);
    }
}
