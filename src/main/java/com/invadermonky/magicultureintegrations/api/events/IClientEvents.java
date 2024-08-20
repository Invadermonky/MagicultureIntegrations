package com.invadermonky.magicultureintegrations.api.events;

import net.minecraftforge.client.event.GuiContainerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IClientEvents {
    @SideOnly(Side.CLIENT)
    void onGuiScreenDrawForeground(GuiContainerEvent.DrawForeground event);
}
