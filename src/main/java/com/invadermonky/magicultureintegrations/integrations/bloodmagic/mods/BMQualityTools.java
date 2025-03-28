package com.invadermonky.magicultureintegrations.integrations.bloodmagic.mods;

import WayofTime.bloodmagic.item.armour.ItemSentientArmour;
import com.invadermonky.magicultureintegrations.api.IProxy;
import com.invadermonky.magicultureintegrations.config.MIConfigIntegrations;
import com.tmtravlr.qualitytools.QualityToolsHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BMQualityTools implements IProxy {
    @Override
    public void preInit() {
        if(MIConfigIntegrations.blood_magic.sentient_armor_quality) {
            MinecraftForge.EVENT_BUS.register(this);
        }
    }

    @SubscribeEvent
    public void onEquipmentChangePre(LivingEquipmentChangeEvent event) {
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
