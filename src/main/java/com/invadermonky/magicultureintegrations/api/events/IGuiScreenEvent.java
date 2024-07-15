package com.invadermonky.magicultureintegrations.api.events;

import com.invadermonky.magicultureintegrations.api.mods.IConfigurable;
import net.minecraftforge.client.event.GuiContainerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public interface IGuiScreenEvent extends IConfigurable {
    void onGuiScreenDrawForeground(GuiContainerEvent.DrawForeground event);
}
