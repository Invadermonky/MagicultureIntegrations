package com.invadermonky.magicultureintegrations.events;

import com.invadermonky.magicultureintegrations.api.events.IConfigChangedEvent;
import com.invadermonky.magicultureintegrations.api.events.IGuiScreenEvent;
import net.minecraftforge.client.event.GuiContainerEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber
public class ClientEventHandler {
    private static ArrayList<IConfigChangedEvent> clientEventModules = new ArrayList<>();
    private static ArrayList<IGuiScreenEvent> guiEventModules = new ArrayList<>();

    @SubscribeEvent
    public static void onExternalConfigChanged(ConfigChangedEvent.PostConfigChangedEvent event) {
        for(IConfigChangedEvent configChangedModule : clientEventModules) {
            configChangedModule.onExternalConfigChanged(event.getModID());
        }
    }

    @SubscribeEvent
    public static void onGuiDrawForeground(GuiContainerEvent.DrawForeground event) {
        for(IGuiScreenEvent guiEventModule : guiEventModules) {
                guiEventModule.onGuiScreenDrawForeground(event);
        }
    }

    public static void addConfigChangedEventModule(IConfigChangedEvent iConfigChanged) {
        if(iConfigChanged != null) {
            clientEventModules.add(iConfigChanged);
        }
    }

    public static void addGuiScreenModule(IGuiScreenEvent iGuiScreenEvent) {
        if(iGuiScreenEvent != null) {
            guiEventModules.add(iGuiScreenEvent);
        }
    }
}
