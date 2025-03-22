package com.invadermonky.magicultureintegrations.integrations.thaumcraft;

import blusunrize.immersiveengineering.common.blocks.stone.TileEntityAlloySmelter;
import blusunrize.immersiveengineering.common.blocks.stone.TileEntityBlastFurnace;
import blusunrize.immersiveengineering.common.blocks.stone.TileEntityCokeOven;
import com.bewitchment.common.block.tile.entity.TileEntityWitchesOven;
import com.blakebr0.mysticalagriculture.tileentity.furnace.TileEssenceFurnace;
import com.invadermonky.magicultureintegrations.api.mods.IIntegrationModule;
import com.invadermonky.magicultureintegrations.api.tile.HeatableUtils;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.util.IntegrationList;
import com.invadermonky.magicultureintegrations.util.ModIds;
import ic2.core.block.machine.tileentity.TileEntityFermenter;
import ic2.core.block.steam.TileEntityCokeKiln;
import net.blay09.mods.cookingforblockheads.tile.TileOven;
import org.zeith.thaumicadditions.tiles.TileAbstractSmelter;
import rustic.common.tileentity.TileEntityCondenserBase;
import slimeknights.tconstruct.smeltery.tileentity.TileHeatingStructure;
import thaumcraft.common.tiles.devices.TileBellows;
import thaumcraft.common.tiles.essentia.TileSmelter;
import thedarkcolour.futuremc.tile.TileFurnaceAdvanced;
import wile.engineersdecor.blocks.BlockDecorFurnace;

public class InitThaumcraft implements IIntegrationModule {
    private final IntegrationList integrations = new IntegrationList("Thaumcraft");

    @Override
    public void buildModIntegrations() {}

    @Override
    public IntegrationList getModIntegrations() {
        return this.integrations;
    }

    @Override
    public void preInit() {
        initHeatables();
    }

    private void initHeatables() {
        if(ModIds.bewitchment.isLoaded && !ConfigHandlerMI.heatables.bewitchment.arcane_bellows) {
            HeatableUtils.blacklistHeatable(TileBellows.class, TileEntityWitchesOven.class);
        }
        if(ModIds.cooking_for_blockheads.isLoaded && !ConfigHandlerMI.heatables.cooking_for_blockheads.arcane_bellows) {
            HeatableUtils.blacklistHeatable(TileBellows.class, TileOven.class);
        }
        if(ModIds.engineers_decor.isLoaded && !ConfigHandlerMI.heatables.engineers_decor.arcane_bellows) {
            HeatableUtils.blacklistHeatable(TileBellows.class, BlockDecorFurnace.BTileEntity.class);
        }
        if(ModIds.futuremc.isLoaded && !ConfigHandlerMI.heatables.future_mc.arcane_bellows) {
            HeatableUtils.blacklistHeatable(TileBellows.class, TileFurnaceAdvanced.class);
        }
        //Immersive Engineering
        if(ModIds.immersive_engineering.isLoaded) {
            if (!ConfigHandlerMI.heatables.immersive_engineering.alloy_smelter.arcane_bellows) {
                HeatableUtils.blacklistHeatable(TileBellows.class, TileEntityAlloySmelter.class);
            }
            if (!ConfigHandlerMI.heatables.immersive_engineering.blast_furnace.arcane_bellows) {
                HeatableUtils.blacklistHeatable(TileBellows.class, TileEntityBlastFurnace.class);
            }
            if (!ConfigHandlerMI.heatables.immersive_engineering.coke_oven.arcane_bellows) {
                HeatableUtils.blacklistHeatable(TileBellows.class, TileEntityCokeOven.class);
            }
        }

        //Industrial Craft
        if(ModIds.industrial_craft.isLoaded) {
            if (!ConfigHandlerMI.heatables.industrial_craft.blast_furnace.arcane_bellows) {
                HeatableUtils.blacklistHeatable(TileBellows.class, ic2.core.block.machine.tileentity.TileEntityBlastFurnace.class);
            }
            if (!ConfigHandlerMI.heatables.industrial_craft.coke_kiln.arcane_bellows) {
                HeatableUtils.blacklistHeatable(TileBellows.class, TileEntityCokeKiln.class);
            }
            if (!ConfigHandlerMI.heatables.industrial_craft.fermenter.arcane_bellows) {
                HeatableUtils.blacklistHeatable(TileBellows.class, TileEntityFermenter.class);
            }
        }

        if(ModIds.mystical_agriculture.isLoaded && !ConfigHandlerMI.heatables.mystical_agriculture.arcane_bellows) {
            HeatableUtils.blacklistHeatable(TileBellows.class, TileEssenceFurnace.class);
        }
        if(ModIds.rustic.isLoaded && !ConfigHandlerMI.heatables.rustic.arcane_bellows) {
            HeatableUtils.blacklistHeatable(TileBellows.class, TileEntityCondenserBase.class);
        }
        if(ModIds.tinkers_construct.isLoaded && !ConfigHandlerMI.heatables.tinkers_construct.arcane_bellows) {
            HeatableUtils.blacklistHeatable(TileBellows.class, TileHeatingStructure.class);
        }
        //Thaumcraft bellows heatables are already handled by Thaumcraft.
        HeatableUtils.blacklistHeatable(TileBellows.class, TileSmelter.class);

        if(ModIds.thaumadditions.isLoaded) {
            HeatableUtils.blacklistHeatable(TileBellows.class, TileAbstractSmelter.class);
        }
    }
}
