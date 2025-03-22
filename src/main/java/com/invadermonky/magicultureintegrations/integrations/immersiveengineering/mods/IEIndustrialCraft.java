package com.invadermonky.magicultureintegrations.integrations.immersiveengineering.mods;

import blusunrize.immersiveengineering.api.tool.ExternalHeaterHandler;
import com.invadermonky.magicultureintegrations.api.IProxy;
import com.invadermonky.magicultureintegrations.api.mods.immersiveengineering.ImmersiveEngineeringUtils;
import com.invadermonky.magicultureintegrations.api.tile.IBoostableTile;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import ic2.core.block.machine.tileentity.TileEntityBlastFurnace;
import ic2.core.block.machine.tileentity.TileEntityFermenter;
import ic2.core.block.steam.TileEntityCokeKiln;

public class IEIndustrialCraft implements IProxy {
    @Override
    public void preInit() {
        if(!ConfigHandlerMI.heatables.industrial_craft.blast_furnace._globalDisable && ConfigHandlerMI.heatables.industrial_craft.blast_furnace.external_heater) {
            ExternalHeaterHandler.registerHeatableAdapter(TileEntityBlastFurnace.class, new IndustrialCraftBlastFurnaceAdapter());
        }
        if(!ConfigHandlerMI.heatables.industrial_craft.coke_kiln._globalDisable && ConfigHandlerMI.heatables.industrial_craft.coke_kiln.external_heater) {
            ExternalHeaterHandler.registerHeatableAdapter(TileEntityCokeKiln.class, new IndustrialCraftCokeKilnAdapter());
        }
        if(!ConfigHandlerMI.heatables.industrial_craft.fermenter._globalDisable && ConfigHandlerMI.heatables.industrial_craft.fermenter.external_heater) {
            ExternalHeaterHandler.registerHeatableAdapter(TileEntityFermenter.class, new IndustrialCraftFermenterAdapter());
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

    public static class IndustrialCraftFermenterAdapter extends ExternalHeaterHandler.HeatableAdapter<TileEntityFermenter> {
        @Override
        public int doHeatTick(TileEntityFermenter furnace, int energy, boolean redstone) {
            return furnace instanceof IBoostableTile ? ImmersiveEngineeringUtils.doBoostableTick((IBoostableTile) furnace, energy, redstone) : 0;
        }
    }
}
