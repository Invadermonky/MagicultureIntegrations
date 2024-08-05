package com.invadermonky.magicultureintegrations.integrations.bloodmagic.mods;

import WayofTime.bloodmagic.item.armour.ItemSentientArmour;
import com.invadermonky.magicultureintegrations.api.events.IEntityEquipEvent;
import com.invadermonky.magicultureintegrations.api.mods.bloodmagic.IBMIntegration;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.events.EntityEventHandler;
import com.tmtravlr.qualitytools.QualityToolsHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;

public class BMQualityTools implements IBMIntegration {
    @Override
    public void registerMiscellaneous() {
        if(ConfigHandlerMI.blood_magic.quality_tools.sentientArmorQualityCopy)
            EntityEventHandler.registerEntityEventModule(new BMQTEntityEquip());
    }

    public static class BMQTEntityEquip implements IEntityEquipEvent {
        @Override
        public void onArmorEquipEarly(LivingEquipmentChangeEvent event) {
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
}
