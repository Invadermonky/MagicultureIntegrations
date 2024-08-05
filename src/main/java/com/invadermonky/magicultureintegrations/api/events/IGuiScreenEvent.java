package com.invadermonky.magicultureintegrations.api.events;

import com.invadermonky.magicultureintegrations.api.mods.IConfigurable;
import net.minecraftforge.client.event.GuiContainerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IGuiScreenEvent extends IConfigurable {
    @SideOnly(Side.CLIENT)
    void onGuiScreenDrawForeground(GuiContainerEvent.DrawForeground event);
}
