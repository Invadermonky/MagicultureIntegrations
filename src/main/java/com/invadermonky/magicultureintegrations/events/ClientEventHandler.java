package com.invadermonky.magicultureintegrations.events;

import com.invadermonky.magicultureintegrations.api.events.IClientEvents;
import gnu.trove.map.hash.THashMap;
import gnu.trove.set.hash.THashSet;
import net.minecraftforge.client.event.GuiContainerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class ClientEventHandler {
    private static final THashMap<Class<? extends Event>, THashSet<IClientEvents>> CLIENT_EVENT_SUBSCRIBERS = new THashMap<>();

    public static void registerEventSubscriber(Class<? extends Event> eventClass, IClientEvents subscriber) {
        if(!CLIENT_EVENT_SUBSCRIBERS.containsKey(eventClass)) {
            CLIENT_EVENT_SUBSCRIBERS.put(eventClass, new THashSet<>());
        }
        CLIENT_EVENT_SUBSCRIBERS.get(eventClass).add(subscriber);
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onGuiDrawForeground(GuiContainerEvent.DrawForeground event) {
        if(CLIENT_EVENT_SUBSCRIBERS.containsKey(event.getClass())) {
            for(IClientEvents subscriber : CLIENT_EVENT_SUBSCRIBERS.get(event.getClass())) {
                subscriber.onGuiScreenDrawForeground(event);
            }
        }
    }
}
