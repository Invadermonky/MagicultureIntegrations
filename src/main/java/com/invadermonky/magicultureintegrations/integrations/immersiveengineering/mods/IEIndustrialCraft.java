package com.invadermonky.magicultureintegrations.integrations.immersiveengineering.mods;

import blusunrize.immersiveengineering.api.tool.ExternalHeaterHandler;
import com.invadermonky.magicultureintegrations.api.IProxy;
import com.invadermonky.magicultureintegrations.api.mods.immersiveengineering.ImmersiveEngineeringUtils;
import com.invadermonky.magicultureintegrations.api.tile.IBoostableTile;
import com.invadermonky.magicultureintegrations.config.MIConfigIntegrations;
import ic2.core.block.machine.tileentity.TileEntityBlastFurnace;
import ic2.core.block.steam.TileEntityCokeKiln;

public class IEIndustrialCraft implements IProxy {
    @Override
    public void preInit() {
        if(MIConfigIntegrations.industrial_craft.boostable_blast_furnace && MIConfigIntegrations.immersive_engineering.external_heater_integrations.ic2_blast_furnace) {
            ExternalHeaterHandler.registerHeatableAdapter(TileEntityBlastFurnace.class, new IndustrialCraftBlastFurnaceAdapter());
        }
        if(MIConfigIntegrations.industrial_craft.boostable_coke_kiln && MIConfigIntegrations.immersive_engineering.external_heater_integrations.ic2_coke_kiln) {
            ExternalHeaterHandler.registerHeatableAdapter(TileEntityCokeKiln.class, new IndustrialCraftCokeKilnAdapter());
        }
    }

    public static class IndustrialCraftBlastFurnaceAdapter extends ExternalHeaterHandler.HeatableAdapter<TileEntityBlastFurnace> {
        @Override
        public int doHeatTick(TileEntityBlastFurnace furnace, int energy, boolean redstone) {
            return furnace instanceof IBoostableTile ? ImmersiveEngineeringUtils.doBoostableTick((IBoostableTile) furnace, energy, redstone) : 0;
        }
    }

    public static class IndustrialCraftCokeKilnAdapter extends ExternalHeaterHandler.HeatableAdapter<TileEntityCokeKiln> {
        @Override
        public int doHeatTick(TileEntityCokeKiln furnace, int energy, boolean redstone) {
            return furnace instanceof IBoostableTile ? ImmersiveEngineeringUtils.doBoostableTick((IBoostableTile) furnace, energy, redstone) : 0;
        }
    }
}
