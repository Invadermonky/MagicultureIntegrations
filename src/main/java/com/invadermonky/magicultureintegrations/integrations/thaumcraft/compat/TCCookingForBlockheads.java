package com.invadermonky.magicultureintegrations.integrations.thaumcraft.compat;

import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.api.mods.cookingforblockheads.CookingForBlockheadsHeatable;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.integrations.thaumcraft.events.TCBellowsHandler;
import net.blay09.mods.cookingforblockheads.tile.TileOven;

public class TCCookingForBlockheads implements IModIntegration {
    @Override
    public void preInit() {
        if(ConfigHandlerMI.thaumcraft.arcane_bellows.cooking_for_blockheads)
            TCBellowsHandler.registerBellowsHeatable(TileOven.class, CookingForBlockheadsHeatable.class);
    }

    @Override
    public void init() {

    }

    @Override
    public void postInit() {

    }
}
