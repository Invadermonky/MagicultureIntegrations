package com.invadermonky.magicultureintegrations.integrations.bloodmagic.compat;

import WayofTime.bloodmagic.item.armour.ItemSentientArmour;
import com.invadermonky.magicultureintegrations.api.events.ICommonEvents;
import com.invadermonky.magicultureintegrations.api.mods.IModIntegration;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.events.CommonEventHandler;
import com.tmtravlr.qualitytools.QualityToolsHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;

public class BMQualityTools implements IModIntegration, ICommonEvents {
    @Override
    public void preInit() {
        if(ConfigHandlerMI.blood_magic.quality_tools.sentientArmorQualityCopy) {
            CommonEventHandler.registerEventSubscriber(LivingEquipmentChangeEvent.class, this);
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void postInit() {

    }

    @Override
    public void onEquipmentChangePre(LivingEquipmentChangeEvent event) {
        ICommonEvents.super.onEquipmentChangePre(event);
        if(event.getEntity().world.isRemote)
            return;

        if(event.getEntity() instanceof EntityPlayer) {
            ItemStack oldArmor = event.getFrom();
            ItemStack newArmor = event.getTo();
            if(newArmor.getItem() instanceof ItemSentientArmour) {
                if(QualityToolsHelper.hasQualityTag(oldArmor)) {
                    NBTTagCompound qualityTag = QualityToolsHelper.getQualityTag(oldArmor);
                    newArmor.setTagInfo(QualityToolsHelper.TAG_NAME_QUALITY, qualityTag);
                } else {
                    newArmor.setTagInfo(QualityToolsHelper.TAG_NAME_QUALITY, new NBTTagCompound());
                }
            }
        }
    }

}
