package com.invadermonky.magicultureintegrations.integrations.qualitytools;

import com.invadermonky.magicultureintegrations.api.events.IGuiScreenEvent;
import com.invadermonky.magicultureintegrations.api.mods.IConfigurable;
import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.events.ClientEventHandler;
import com.invadermonky.magicultureintegrations.util.ReflectionHelper;
import com.tmtravlr.qualitytools.QualityToolsHelper;
import com.tmtravlr.qualitytools.reforging.GuiReforgingStation;
import com.tmtravlr.qualitytools.reforging.TileEntityReforgingStation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.event.GuiContainerEvent;

public class InitQualityTools implements IModIntegration {
    @Override
    public void buildModules() {}

    @Override
    public void preInit() {}

    @Override
    public void init() {
        ClientEventHandler.addGuiScreenModule(new QTGuiEvent());
    }

    @Override
    public void postInit() {}







    public static class QTGuiEvent implements IGuiScreenEvent, IConfigurable {
        /** This value controls the xOffset from center of the quality name text. Increasing this value will shift the text right. */
        public static int xOffset = 15;
        /** This value controls the yOffset from the top of the gui. Increasing this value will shift the text down. */
        public static int yOffset = 23;

        @Override
        public void onGuiScreenDrawForeground(GuiContainerEvent.DrawForeground event) {
            GuiScreen guiScreen = event.getGuiContainer();
            if(guiScreen instanceof GuiReforgingStation) {
                GuiReforgingStation reforgeGui = (GuiReforgingStation) guiScreen;
                try {
                    TileEntityReforgingStation tile = (TileEntityReforgingStation) ReflectionHelper.getFieldObject(reforgeGui, "tileReforgingStation");
                    ItemStack reforgeStack = tile.getStackInSlot(0);
                    if(QualityToolsHelper.hasQualityTag(reforgeStack)) {
                        NBTTagCompound qualityTag = QualityToolsHelper.getQualityTag(reforgeStack);
                        if(qualityTag.hasKey(QualityToolsHelper.TAG_NAME_NAME)) {
                            String qualityStr = I18n.format(qualityTag.getString(QualityToolsHelper.TAG_NAME_NAME));
                            Minecraft.getMinecraft().fontRenderer.drawString(
                                    I18n.format(qualityStr),
                                    (reforgeGui.getXSize() / 2) + xOffset,
                                    yOffset,
                                    0x303030);
                        }
                    }
                } catch (Exception ignored) {}
            }
        }

        @Override
        public boolean isEnabled() {
            return ConfigHandlerMI.quality_tools.enableGuiQualityText;
        }
    }

}