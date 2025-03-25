package com.invadermonky.magicultureintegrations.integrations.botania;

import blusunrize.immersiveengineering.common.blocks.stone.TileEntityAlloySmelter;
import blusunrize.immersiveengineering.common.blocks.stone.TileEntityBlastFurnace;
import blusunrize.immersiveengineering.common.blocks.stone.TileEntityCokeOven;
import com.blakebr0.mysticalagriculture.tileentity.furnace.TileEssenceFurnace;
import com.invadermonky.magicultureintegrations.api.mods.IIntegrationModule;
import com.invadermonky.magicultureintegrations.api.tile.HeatableUtils;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.integrations.botania.block.BlockSpecialFlowerMI;
import com.invadermonky.magicultureintegrations.integrations.botania.item.ItemLensFlux;
import com.invadermonky.magicultureintegrations.registry.RegistrarMI;
import com.invadermonky.magicultureintegrations.util.IntegrationList;
import com.invadermonky.magicultureintegrations.util.ModIds;
import ic2.core.block.machine.tileentity.TileEntityFermenter;
import ic2.core.block.steam.TileEntityCokeKiln;
import net.blay09.mods.cookingforblockheads.tile.TileOven;
import org.jetbrains.annotations.NotNull;
import org.zeith.thaumicadditions.tiles.TileAbstractSmelter;
import rustic.common.tileentity.TileEntityCondenserBase;
import slimeknights.tconstruct.smeltery.tileentity.TileHeatingStructure;
import thaumcraft.common.tiles.essentia.TileSmelter;
import thedarkcolour.futuremc.tile.TileFurnaceAdvanced;
import vazkii.botania.common.block.subtile.functional.SubTileExoflame;
import wile.engineersdecor.blocks.BlockDecorFurnace;

public class InitBotania implements IIntegrationModule {
    private final IntegrationList integrations = new IntegrationList("Botania");

    @Override
    public void buildModIntegrations() {}

    @Override
    public @NotNull IntegrationList getModIntegrations() {
        return this.integrations;
    }

    @Override
    public void preInit() {
        //TODO: OreBerry block needs the IHornHarvestable interface added to it.

        this.initHeatables();
        if(ModIds.thaumcraft.isLoaded) {
            RegistrarMI.registerAddition(ItemLensFlux.FLUX_LENS);
            RegistrarMI.registerAddition(BlockSpecialFlowerMI.BLOCK_SPECIAL_FLOWER);
        }
    }

    private void initHeatables() {
        if(ModIds.cooking_for_blockheads.isLoaded && !ConfigHandlerMI.heatables.cooking_for_blockheads.exoflame) {
            HeatableUtils.blacklistHeatable(SubTileExoflame.class, TileOven.class);
        }

        if(ModIds.engineers_decor.isLoaded && !ConfigHandlerMI.heatables.engineers_decor.exoflame) {
            HeatableUtils.blacklistHeatable(SubTileExoflame.class, BlockDecorFurnace.BTileEntity.class);
        }

        if(ModIds.futuremc.isLoaded && !ConfigHandlerMI.heatables.future_mc.exoflame) {
            HeatableUtils.blacklistHeatable(SubTileExoflame.class, TileFurnaceAdvanced.class);
        }

        if(ModIds.immersive_engineering.isLoaded) {
            if(!ConfigHandlerMI.heatables.immersive_engineering.alloy_smelter.exoflame) {
                HeatableUtils.blacklistHeatable(SubTileExoflame.class, TileEntityAlloySmelter.class);
            }
            if(!ConfigHandlerMI.heatables.immersive_engineering.blast_furnace.exoflame) {
                HeatableUtils.blacklistHeatable(SubTileExoflame.class, TileEntityBlastFurnace.class);
            }
            if(!ConfigHandlerMI.heatables.immersive_engineering.coke_oven.exoflame) {
                HeatableUtils.blacklistHeatable(SubTileExoflame.class, TileEntityCokeOven.class);
            }
        }

        if(ModIds.mystical_agriculture.isLoaded && !ConfigHandlerMI.heatables.mystical_agriculture.exoflame) {
            HeatableUtils.blacklistHeatable(SubTileExoflame.class, TileEssenceFurnace.class);
        }

        if(ModIds.industrial_craft.isLoaded) {
            if(!ConfigHandlerMI.heatables.industrial_craft.blast_furnace.exoflame) {
                HeatableUtils.blacklistHeatable(SubTileExoflame.class, ic2.core.block.machine.tileentity.TileEntityBlastFurnace.class);
            }
            if(!ConfigHandlerMI.heatables.industrial_craft.coke_kiln.exoflame) {
                HeatableUtils.blacklistHeatable(SubTileExoflame.class, TileEntityCokeKiln.class);
            }
            if(!ConfigHandlerMI.heatables.industrial_craft.fermenter.exoflame) {
                HeatableUtils.blacklistHeatable(SubTileExoflame.class, TileEntityFermenter.class);
            }
        }

        if(ModIds.rustic.isLoaded && !ConfigHandlerMI.heatables.rustic.exoflame) {
            HeatableUtils.blacklistHeatable(SubTileExoflame.class, TileEntityCondenserBase.class);
        }

        if(ModIds.thaumcraft.isLoaded && !ConfigHandlerMI.heatables.thaumcraft.exoflame) {
            HeatableUtils.blacklistHeatable(SubTileExoflame.class, TileSmelter.class);
        }

        if(ModIds.thaumadditions.isLoaded && !ConfigHandlerMI.heatables.thaumic_additions.exoflame) {
            HeatableUtils.blacklistHeatable(SubTileExoflame.class, TileAbstractSmelter.class);
        }

        if(ModIds.tinkers_construct.isLoaded && !ConfigHandlerMI.heatables.tinkers_construct.exoflame) {
            HeatableUtils.blacklistHeatable(SubTileExoflame.class, TileHeatingStructure.class);
        }
    }
}
