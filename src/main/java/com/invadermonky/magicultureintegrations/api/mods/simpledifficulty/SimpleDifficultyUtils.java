package com.invadermonky.magicultureintegrations.api.mods.simpledifficulty;

import com.charles445.simpledifficulty.api.SDCapabilities;
import com.charles445.simpledifficulty.api.SDPotions;
import com.charles445.simpledifficulty.api.config.QuickConfig;
import com.charles445.simpledifficulty.api.temperature.ITemperatureCapability;
import com.charles445.simpledifficulty.api.thirst.IThirstCapability;
import net.minecraft.entity.player.EntityPlayer;

public class SimpleDifficultyUtils {
    public static boolean stabilizePlayerTemperature(EntityPlayer player) {
        if(!QuickConfig.isTemperatureEnabled())
            return false;

        ITemperatureCapability tempData = SDCapabilities.getTemperatureData(player);
        if(tempData.getTemperatureLevel() < 12) {
            tempData.addTemperatureLevel(2);
            return true;
        } else if(tempData.getTemperatureLevel() > 13) {
            tempData.addTemperatureLevel(-2);
            return true;
        }
        return false;
    }

    public static void clearTemperatureDebuffs(EntityPlayer player) {
        if(player.isPotionActive(SDPotions.hyperthermia)) {
            player.removePotionEffect(SDPotions.hyperthermia);
        }
        if(player.isPotionActive(SDPotions.hypothermia)) {
            player.removePotionEffect(SDPotions.hypothermia);
        }
    }

    public static void hydratePlayer(EntityPlayer player, int amount, float saturation) {
        if(!QuickConfig.isThirstEnabled())
            return;

        IThirstCapability thirstData = SDCapabilities.getThirstData(player);
        thirstData.addThirstLevel(amount);
        thirstData.addThirstSaturation(saturation);
    }
}
