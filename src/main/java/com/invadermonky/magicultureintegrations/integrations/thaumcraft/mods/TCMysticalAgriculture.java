package com.invadermonky.magicultureintegrations.integrations.thaumcraft.mods;

import com.blakebr0.mysticalagriculture.tileentity.furnace.TileEssenceFurnace;
import com.invadermonky.magicultureintegrations.api.events.ITileTickEvent;
import com.invadermonky.magicultureintegrations.api.mods.mysticalagriculture.MysticalAgricultureHeatable;
import com.invadermonky.magicultureintegrations.api.mods.thaumcraft.ITCIntegration;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.events.WorldEventHandler;
import com.invadermonky.magicultureintegrations.integrations.thaumcraft.events.TCBellowsHandler;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import thaumcraft.common.lib.utils.BlockStateUtils;
import thaumcraft.common.tiles.devices.TileBellows;

public class TCMysticalAgriculture implements ITCIntegration {
    @Override
    public void registerBellowsHandler() {
        if(ConfigHandlerMI.thaumcraft.arcane_bellows.mystical_agriculture)
            TCBellowsHandler.registerBellowsHeatable(TileEssenceFurnace.class, MysticalAgricultureHeatable.class);
    }
}
