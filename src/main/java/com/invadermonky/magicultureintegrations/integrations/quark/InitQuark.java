package com.invadermonky.magicultureintegrations.integrations.quark;

import blusunrize.immersiveengineering.common.blocks.stone.TileEntityAlloySmelter;
import blusunrize.immersiveengineering.common.blocks.stone.TileEntityBlastFurnace;
import blusunrize.immersiveengineering.common.blocks.stone.TileEntityCokeOven;
import com.bewitchment.common.block.tile.entity.TileEntityWitchesOven;
import com.blakebr0.mysticalagriculture.tileentity.furnace.TileEssenceFurnace;
import com.invadermonky.magicultureintegrations.api.mods.IIntegrationModule;
import com.invadermonky.magicultureintegrations.api.tile.HeatableUtils;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.events.CommonEventHandler;
import com.invadermonky.magicultureintegrations.integrations.quark.events.QuarkCommonEventHandler;
import com.invadermonky.magicultureintegrations.util.IntegrationList;
import com.invadermonky.magicultureintegrations.util.ModIds;
import ic2.core.block.machine.tileentity.TileEntityFermenter;
import net.blay09.mods.cookingforblockheads.tile.TileOven;
import net.minecraftforge.event.entity.living.LivingEvent;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.smeltery.tileentity.TileHeatingStructure;
import thedarkcolour.futuremc.tile.TileFurnaceAdvanced;
import vazkii.quark.world.entity.EntityFoxhound;
import wile.engineersdecor.blocks.BlockDecorFurnace;

public class InitQuark implements IIntegrationModule {
    @Override
    public void buildModIntegrations() {

    }

    @Nullable
    @Override
    public IntegrationList getModIntegrations() {
        return null;
    }

    @Override
    public void preInit() {
        initHeatables();
    }

    @Override
    public void init() {
        CommonEventHandler.registerEventSubscriber(LivingEvent.LivingUpdateEvent.class, new QuarkCommonEventHandler());
    }

    private void initHeatables() {
        if(ModIds.bewitchment.isLoaded && !ConfigHandlerMI.heatables.bewitchment.foxhound) {
            HeatableUtils.blacklistHeatable(EntityFoxhound.class, TileEntityWitchesOven.class);
        }

        if(ModIds.cooking_for_blockheads.isLoaded && !ConfigHandlerMI.heatables.cooking_for_blockheads.foxhound) {
            HeatableUtils.blacklistHeatable(EntityFoxhound.class, TileOven.class);
        }

        if(ModIds.engineers_decor.isLoaded && !ConfigHandlerMI.heatables.engineers_decor.foxhound) {
            HeatableUtils.blacklistHeatable(EntityFoxhound.class, BlockDecorFurnace.BTileEntity.class);
        }

        if(ModIds.futuremc.isLoaded && !ConfigHandlerMI.heatables.future_mc.foxhound) {
            HeatableUtils.blacklistHeatable(EntityFoxhound.class, TileFurnaceAdvanced.class);
        }

        //Immersive Engineering
        if(ModIds.immersive_engineering.isLoaded && !ConfigHandlerMI.heatables.immersive_engineering.alloy_smelter.foxhound) {
            HeatableUtils.blacklistHeatable(EntityFoxhound.class, TileEntityAlloySmelter.class);
        }
        if(ModIds.immersive_engineering.isLoaded && !ConfigHandlerMI.heatables.immersive_engineering.blast_furnace.foxhound) {
            HeatableUtils.blacklistHeatable(EntityFoxhound.class, TileEntityBlastFurnace.class);
        }
        if(ModIds.immersive_engineering.isLoaded && !ConfigHandlerMI.heatables.immersive_engineering.coke_oven.foxhound) {
            HeatableUtils.blacklistHeatable(EntityFoxhound.class, TileEntityCokeOven.class);
        }

        //Industrial Craft
        if(ModIds.industrial_craft.isLoaded && !ConfigHandlerMI.heatables.industrial_craft.blast_furnace.foxhound) {
            HeatableUtils.blacklistHeatable(EntityFoxhound.class, ic2.core.block.machine.tileentity.TileEntityBlastFurnace.class);
        }
        if(ModIds.industrial_craft.isLoaded && !ConfigHandlerMI.heatables.industrial_craft.fermenter.foxhound) {
            HeatableUtils.blacklistHeatable(EntityFoxhound.class, TileEntityFermenter.class);
        }

        if(ModIds.mystical_agriculture.isLoaded && !ConfigHandlerMI.heatables.mystical_agriculture.foxhound) {
            HeatableUtils.blacklistHeatable(EntityFoxhound.class, TileEssenceFurnace.class);
        }

        if(ModIds.tinkers_construct.isLoaded && !ConfigHandlerMI.heatables.tinkers_construct.foxhound) {
            HeatableUtils.blacklistHeatable(EntityFoxhound.class, TileHeatingStructure.class);
        }
    }
}
