package com.invadermonky.magicultureintegrations.integrations.bloodmagic.events;

import WayofTime.bloodmagic.item.armour.ItemSentientArmour;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class BMClientEventHandler {
    @SubscribeEvent
    public void onSlotClicked(GuiScreenEvent.MouseInputEvent.Pre event) {
        EntityPlayer player = Minecraft.getMinecraft().player;
        if (player == null || player.isCreative() || !(event.getGui() instanceof GuiContainer)) {
            return;
        }

        GuiContainer container = (GuiContainer) event.getGui();
        Slot slot = container.getSlotUnderMouse();
        if(slot != null && slot.getHasStack() && slot.getStack().getItem() instanceof ItemSentientArmour) {
            ItemStack slotStack = slot.getStack();
            if (player.inventory.armorInventory.stream().anyMatch(armor -> armor == slotStack)) {
                event.setCanceled(true);
            }
        }
    }
}
