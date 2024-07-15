package com.invadermonky.magicultureintegrations.api.events;

import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;

public interface IEntityEquipEvent {
    void onArmorEquipEarly(LivingEquipmentChangeEvent event);
}
