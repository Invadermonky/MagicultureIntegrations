package com.invadermonky.magicultureintegrations.integrations.quark.events;

import com.invadermonky.magicultureintegrations.api.tile.HeatableUtils;
import com.invadermonky.magicultureintegrations.api.tile.IBoostableTile;
import com.invadermonky.magicultureintegrations.api.tile.IHeatableTile;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import vazkii.quark.world.entity.EntityFoxhound;

import java.util.List;

public class QuarkEventHandler {
    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
        World world = event.getEntity().world;
        if(world.isRemote)
            return;

        if(event.getEntityLiving() instanceof EntityFoxhound) {
            EntityFoxhound foxhound = (EntityFoxhound) event.getEntityLiving();
            if(foxhound.isTamed()) {
                BlockPos below  = foxhound.getPosition().down();
                TileEntity tile = world.getTileEntity(below);
                if(tile instanceof IHeatableTile && !HeatableUtils.isHeatableBlacklisted(foxhound.getClass(), tile)) {
                    IHeatableTile heatable = (IHeatableTile) tile;
                    int burnTime = heatable.getBurnTimeHeatable();
                    if(heatable.canSmeltHeatable() && burnTime > 0 && world.getTotalWorldTime() % 3L == 0L) {
                        List<EntityFoxhound> foxhounds = world.getEntitiesWithinAABB(EntityFoxhound.class, new AxisAlignedBB(foxhound.getPosition()), fox -> fox != null && fox.isTamed());
                        if(!foxhounds.isEmpty() && foxhounds.get(0) == foxhound) {
                            heatable.boostCookTimeHeatable(ConfigHandlerMI.integrations.quark.foxButtSpeedup);
                            heatable.updateTileHeatable();
                        }
                    }
                } else if(tile instanceof IBoostableTile && !HeatableUtils.isHeatableBlacklisted(EntityFoxhound.class, tile)) {
                    IBoostableTile boostable = ((IBoostableTile) tile).getTrueBoostable();
                    int smeltTime = boostable.getCookTimeBoostable();
                    if(boostable.canSmeltBoostable() && smeltTime > 0 && world.getTotalWorldTime() % 3L == 0) {
                        List<EntityFoxhound> foxhounds = world.getEntitiesWithinAABB(EntityFoxhound.class, new AxisAlignedBB(foxhound.getPosition()), fox -> fox != null && fox.isTamed());
                        if(!foxhounds.isEmpty() && foxhounds.get(0) == foxhound) {
                            boostable.boostCookTimeBoostable(ConfigHandlerMI.integrations.quark.foxButtSpeedup);
                            boostable.updateTileBoostable();
                        }
                    }
                }
            }
        }
    }
}
