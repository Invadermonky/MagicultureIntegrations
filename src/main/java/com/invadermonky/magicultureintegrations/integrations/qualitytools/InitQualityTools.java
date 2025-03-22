package com.invadermonky.magicultureintegrations.integrations.qualitytools;

import com.invadermonky.magicultureintegrations.api.mods.IIntegrationModule;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.util.IntegrationList;
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
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

public class InitQualityTools implements IIntegrationModule {
    private final IntegrationList integrations = new IntegrationList("Quality Tools");

    @Override
    public void buildModIntegrations() {}

    @Override
    public @NotNull IntegrationList getModIntegrations() {
        return this.integrations;
    }

    @Override
    public void preInit() {
        if(ConfigHandlerMI.integrations.quality_tools.enableGuiQualityText) {
            MinecraftForge.EVENT_BUS.register(new QTGuiEvent());
        }
    }

    public static class QTGuiEvent {
        /** This value controls the xOffset from center of the quality name text. Increasing this value will shift the text right. */
        public static int xOffset = 15;
        /** This value controls the yOffset from the top of the gui. Increasing this value will shift the text down. */
        public static int yOffset = 23;

        @SideOnly(Side.CLIENT)
        @SubscribeEvent
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
    }
}
