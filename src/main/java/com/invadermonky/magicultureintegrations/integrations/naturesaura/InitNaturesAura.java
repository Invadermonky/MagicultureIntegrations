package com.invadermonky.magicultureintegrations.integrations.naturesaura;

import blusunrize.immersiveengineering.common.blocks.stone.TileEntityAlloySmelter;
import blusunrize.immersiveengineering.common.blocks.stone.TileEntityBlastFurnace;
import blusunrize.immersiveengineering.common.blocks.stone.TileEntityCokeOven;
import com.bewitchment.common.block.tile.entity.TileEntityWitchesOven;
import com.blakebr0.mysticalagriculture.tileentity.furnace.TileEssenceFurnace;
import com.invadermonky.magicultureintegrations.api.mods.IIntegrationModule;
import com.invadermonky.magicultureintegrations.api.tile.HeatableUtils;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.integrations.naturesaura.events.NAEventHandler;
import com.invadermonky.magicultureintegrations.util.IntegrationList;
import com.invadermonky.magicultureintegrations.util.ModIds;
import de.ellpeck.naturesaura.blocks.tiles.TileEntityFurnaceHeater;
import ic2.core.block.machine.tileentity.TileEntityFermenter;
import ic2.core.block.steam.TileEntityCokeKiln;
import net.blay09.mods.cookingforblockheads.tile.TileOven;
import net.minecraftforge.common.MinecraftForge;
import org.jetbrains.annotations.NotNull;
import org.zeith.thaumicadditions.tiles.TileAbstractSmelter;
import rustic.common.tileentity.TileEntityCondenserBase;
import slimeknights.tconstruct.smeltery.tileentity.TileHeatingStructure;
import thaumcraft.common.tiles.essentia.TileSmelter;
import thedarkcolour.futuremc.tile.TileFurnaceAdvanced;
import vazkii.botania.common.block.subtile.functional.SubTileExoflame;
import wile.engineersdecor.blocks.BlockDecorFurnace;

public class InitNaturesAura implements IIntegrationModule {
    private final IntegrationList integrations = new IntegrationList("Nature's Aura");

    @Override
    public void buildModIntegrations() {}

    @Override
    public @NotNull IntegrationList getModIntegrations() {
        return this.integrations;
    }

    @Override
    public void preInit() {
        this.initHeatables();
        MinecraftForge.EVENT_BUS.register(new NAEventHandler());
    }

    private void initHeatables() {
        if(ModIds.bewitchment.isLoaded && !ConfigHandlerMI.heatables.bewitchment.extraneous_firestarter) {
            HeatableUtils.blacklistHeatable(TileEntityFurnaceHeater.class, TileEntityWitchesOven.class);
        }

        if(ModIds.cooking_for_blockheads.isLoaded && !ConfigHandlerMI.heatables.cooking_for_blockheads.extraneous_firestarter) {
            HeatableUtils.blacklistHeatable(TileEntityFurnaceHeater.class, TileOven.class);
        }

        if(ModIds.engineers_decor.isLoaded && !ConfigHandlerMI.heatables.engineers_decor.extraneous_firestarter) {
            HeatableUtils.blacklistHeatable(TileEntityFurnaceHeater.class, BlockDecorFurnace.BTileEntity.class);
        }

        if(ModIds.futuremc.isLoaded && !ConfigHandlerMI.heatables.future_mc.extraneous_firestarter) {
            HeatableUtils.blacklistHeatable(TileEntityFurnaceHeater.class, TileFurnaceAdvanced.class);
        }

        if(ModIds.immersive_engineering.isLoaded) {
            if(!ConfigHandlerMI.heatables.immersive_engineering.alloy_smelter.extraneous_firestarter) {
                HeatableUtils.blacklistHeatable(TileEntityFurnaceHeater.class, TileEntityAlloySmelter.class);
            }
            if(!ConfigHandlerMI.heatables.immersive_engineering.blast_furnace.extraneous_firestarter) {
                HeatableUtils.blacklistHeatable(TileEntityFurnaceHeater.class, TileEntityBlastFurnace.class);
            }
            if(!ConfigHandlerMI.heatables.immersive_engineering.coke_oven.extraneous_firestarter) {
                HeatableUtils.blacklistHeatable(TileEntityFurnaceHeater.class, TileEntityCokeOven.class);
            }
        }

        if(ModIds.industrial_craft.isLoaded) {
            if(!ConfigHandlerMI.heatables.industrial_craft.blast_furnace.extraneous_firestarter) {
                HeatableUtils.blacklistHeatable(TileEntityFurnaceHeater.class, ic2.core.block.machine.tileentity.TileEntityBlastFurnace.class);
            }
            if(!ConfigHandlerMI.heatables.industrial_craft.coke_kiln.extraneous_firestarter) {
                HeatableUtils.blacklistHeatable(TileEntityFurnaceHeater.class, TileEntityCokeKiln.class);
            }
            if(!ConfigHandlerMI.heatables.industrial_craft.fermenter.extraneous_firestarter) {
                HeatableUtils.blacklistHeatable(TileEntityFurnaceHeater.class, TileEntityFermenter.class);
            }
        }

        if(ModIds.mystical_agriculture.isLoaded && !ConfigHandlerMI.heatables.mystical_agriculture.extraneous_firestarter) {
            HeatableUtils.blacklistHeatable(TileEntityFurnaceHeater.class, TileEssenceFurnace.class);
        }

        if(ModIds.rustic.isLoaded && !ConfigHandlerMI.heatables.rustic.extraneous_firestarter) {
            HeatableUtils.blacklistHeatable(TileEntityFurnaceHeater.class, TileEntityCondenserBase.class);
        }

        if(ModIds.thaumcraft.isLoaded && !ConfigHandlerMI.heatables.thaumcraft.extraneous_firestarter) {
            HeatableUtils.blacklistHeatable(TileEntityFurnaceHeater.class, TileSmelter.class);
        }

        if(ModIds.thaumadditions.isLoaded && !ConfigHandlerMI.heatables.thaumic_additions.extraneous_firestarter) {
            HeatableUtils.blacklistHeatable(TileEntityFurnaceHeater.class, TileAbstractSmelter.class);
        }

        if(ModIds.tinkers_construct.isLoaded && !ConfigHandlerMI.heatables.tinkers_construct.extraneous_firestarter) {
            HeatableUtils.blacklistHeatable(SubTileExoflame.class, TileHeatingStructure.class);
        }
    }
}
