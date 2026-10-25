package com.invadermonky.magicultureintegrations.integrations.familiarfauna.events;

import familiarfauna.entities.EntityDeer;
import familiarfauna.entities.EntityTurkey;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FFEventHandler {
    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event) {
        World world = event.getWorld();
        Entity entity = event.getEntity();
        if(!world.isRemote) {
            if (entity instanceof EntityDeer) {
                ((EntityDeer) entity).setDeerType(world.rand.nextInt(2));
            } else if (entity instanceof EntityTurkey) {
                ((EntityTurkey) entity).setTurkeyType(world.rand.nextInt(2));
            }
        }
    }
}
