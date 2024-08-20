package com.invadermonky.magicultureintegrations.api.events;

import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;

public interface ICommonEvents {
    default void onBabyBorn(BabyEntitySpawnEvent event) {};
    default void onEquipmentChangePre(LivingEquipmentChangeEvent event) {}
}
