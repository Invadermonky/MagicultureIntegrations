package com.invadermonky.magicultureintegrations.api.mods.toughasnails;

import net.minecraft.entity.player.EntityPlayer;
import toughasnails.api.TANPotions;
import toughasnails.api.config.GameplayOption;
import toughasnails.api.config.SyncedConfig;
import toughasnails.api.stat.capability.ITemperature;
import toughasnails.api.stat.capability.IThirst;
import toughasnails.api.temperature.Temperature;
import toughasnails.api.temperature.TemperatureHelper;
import toughasnails.api.thirst.ThirstHelper;

public class ToughAsNailsUtils {
    public static boolean stabilizePlayerTemperature(EntityPlayer player) {
        if(!SyncedConfig.getBooleanValue(GameplayOption.ENABLE_TEMPERATURE))
            return false;

        ITemperature temperature = TemperatureHelper.getTemperatureData(player);
        int curTemp = temperature.getTemperature().getRawValue();
        if(curTemp < 12) {
            temperature.setTemperature(new Temperature(curTemp + 1));
            return true;
        } else if(curTemp > 13) {
            temperature.setTemperature(new Temperature(curTemp - 1));
            return true;
        }
        return false;
    }

    public static void clearTemperatureDebuffs(EntityPlayer player) {
        if(player.isPotionActive(TANPotions.hyperthermia)) {
            player.removePotionEffect(TANPotions.hyperthermia);
        }
        if(player.isPotionActive(TANPotions.hypothermia)) {
            player.removePotionEffect(TANPotions.hypothermia);
        }
    }

    public static void hydratePlayer(EntityPlayer player, int amount, float saturation) {
        if(!SyncedConfig.getBooleanValue(GameplayOption.ENABLE_THIRST))
            return;

        IThirst thirst = ThirstHelper.getThirstData(player);
        thirst.addStats(amount, saturation);
    }
}
