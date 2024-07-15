package com.invadermonky.magicultureintegrations.events;

import com.invadermonky.magicultureintegrations.api.events.IEntityEquipEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;

@Mod.EventBusSubscriber
public class EntityEventHandler {
    private static ArrayList<IEntityEquipEvent> entityEquipModules = new ArrayList<>();

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onArmorEquipEarly(LivingEquipmentChangeEvent event) {
        entityEquipModules.forEach(c -> c.onArmorEquipEarly(event));
    }

    public static void registerEntityEventModule(IEntityEquipEvent equipModule) {
        if(equipModule != null) {
            entityEquipModules.add(equipModule);
        }
    }
}
