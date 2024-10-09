package com.invadermonky.magicultureintegrations.api.events;

import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public interface ICommonEvents {
    default void onBabyBorn(BabyEntitySpawnEvent event) {}
    default void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {}
    default void onEquipmentChangePre(LivingEquipmentChangeEvent event) {}
    default void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {}
}
