package com.invadermonky.magicultureintegrations.events;

import com.invadermonky.magicultureintegrations.MagicultureIntegrations;
import com.invadermonky.magicultureintegrations.api.events.ICommonEvents;
import com.invadermonky.magicultureintegrations.api.events.ITileTickEvent;
import gnu.trove.map.hash.THashMap;
import gnu.trove.set.hash.THashSet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber(modid = MagicultureIntegrations.MOD_ID)
public class CommonEventHandler {
    private static final THashMap<Class<? extends Event>, THashSet<ICommonEvents>> COMMON_EVENT_SUBSCRIBERS = new THashMap<>();
    private static final THashMap<Class<? extends TileEntity>, ITileTickEvent> TILE_TICK_SUBSCRIBERS = new THashMap<>();

    public static void registerEventSubscriber(Class<? extends Event> eventClass, ICommonEvents subscriber) {
        if(!COMMON_EVENT_SUBSCRIBERS.containsKey(eventClass)) {
            COMMON_EVENT_SUBSCRIBERS.put(eventClass, new THashSet<>());
        }
        COMMON_EVENT_SUBSCRIBERS.get(eventClass).add(subscriber);
    }

    public static void registerTileTickSubscriber(Class<? extends TileEntity> tileEntity, ITileTickEvent tickSubscriber) {
        TILE_TICK_SUBSCRIBERS.put(tileEntity, tickSubscriber);
    }

    @SubscribeEvent
    public static void onWorldTick(TickEvent.WorldTickEvent event) {
        World world = event.world;

        if(!TILE_TICK_SUBSCRIBERS.isEmpty()) {
            for(TileEntity tile : world.tickableTileEntities) {
                if(TILE_TICK_SUBSCRIBERS.containsKey(tile.getClass()) && TILE_TICK_SUBSCRIBERS.get(tile.getClass()).checkTile(tile)) {
                    TILE_TICK_SUBSCRIBERS.get(tile.getClass()).onTileEntityTick(world, tile);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onBabyBorn(BabyEntitySpawnEvent event) {
        if(COMMON_EVENT_SUBSCRIBERS.containsKey(event.getClass())) {
            for(ICommonEvents subscriber : COMMON_EVENT_SUBSCRIBERS.get(event.getClass())) {
                subscriber.onBabyBorn(event);
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onEquipmentChangePre(LivingEquipmentChangeEvent event) {
        if(COMMON_EVENT_SUBSCRIBERS.containsKey(event.getClass())) {
            for(ICommonEvents subscriber : COMMON_EVENT_SUBSCRIBERS.get(event.getClass())) {
                subscriber.onEquipmentChangePre(event);
            }
        }
    }

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if(COMMON_EVENT_SUBSCRIBERS.containsKey(event.getClass())) {
            for(ICommonEvents subscriber : COMMON_EVENT_SUBSCRIBERS.get(event.getClass())) {
                subscriber.onRightClickBlock(event);
            }
        }
    }
}
