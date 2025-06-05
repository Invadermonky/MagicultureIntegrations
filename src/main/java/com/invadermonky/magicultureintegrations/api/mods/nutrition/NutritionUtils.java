package com.invadermonky.magicultureintegrations.api.mods.nutrition;

import ca.wescook.nutrition.capabilities.INutrientManager;
import ca.wescook.nutrition.nutrients.Nutrient;
import ca.wescook.nutrition.nutrients.NutrientList;
import ca.wescook.nutrition.nutrients.NutrientUtils;
import ca.wescook.nutrition.proxy.ClientProxy;
import com.invadermonky.magicultureintegrations.config.MIConfigIntegrations.NutritionIntegrations.AutoFeedersIntegrations.AutoFeederDeviceConfig;
import com.invadermonky.magicultureintegrations.util.LogHelper;
import com.invadermonky.magicultureintegrations.util.ModIds;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBucketMilk;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.*;

@SuppressWarnings("unchecked")
public class NutritionUtils {
    private static final Map<ResourceLocation, AutoFeederDevice> autoFeederDevices = new HashMap<>();
    private static Capability<?> nutritionCapability;

    public static void addFeederDevice(AutoFeederDevice device) {
        autoFeederDevices.put(device.getFeederDevice(), device);
    }

    public static void doAutoFeed(EntityPlayer player, ResourceLocation location) {
        AutoFeederDevice device = autoFeederDevices.get(location);
        if (device != null) {
            device.applyNutritionalValue(player);
        }
    }

    public static void parseFeederDeviceConfig(ResourceLocation deviceName, AutoFeederDeviceConfig config) {
        if (config.enable && config.nutritionValue > 0) {
            List<Nutrient> nutrients = new ArrayList<>();
            if (config.nutrientNames.length > 0) {
                for (String nutrientName : config.nutrientNames) {
                    for (Nutrient nutrient : NutrientList.get()) {
                        if (nutrient.name.equals(nutrientName)) {
                            nutrients.add(nutrient);
                            break;
                        }
                    }
                }
            } else {
                nutrients.addAll(NutrientList.get());
            }
            addFeederDevice(new AutoFeederDevice(deviceName, (float) config.nutritionValue, (float) config.maxNutrition, nutrients));
        }
    }

    public static void applyNutrition(EntityPlayer player, float nutritionValue, String... nutrientNames) {
        if (ModIds.nutrition.isLoaded) {
            List<Nutrient> nutrientsToAdd = new ArrayList<>();
            for (Nutrient nutrient : NutrientList.get()) {
                for (String nutrientName : nutrientNames) {
                    if (nutrient.name.equals(nutrientName)) {
                        nutrientsToAdd.add(nutrient);
                        break;
                    }
                }
            }
            Capability<INutrientManager> capability = getNutritionCapability();
            if (capability != null) {
                if (!player.world.isRemote) {
                    player.getCapability(capability, null).add(nutrientsToAdd, nutritionValue);
                } else {
                    ClientProxy.localNutrition.add(nutrientsToAdd, nutritionValue);
                }
            }
        }
    }

    public static void applyNutrition(EntityPlayer player, ItemStack stack) {
        if (stack.getItem() instanceof ItemFood || stack.getItem() instanceof ItemBucketMilk) {
            List<Nutrient> foundNutrients = NutrientUtils.getFoodNutrients(stack);
            float nutritionValue = NutrientUtils.calculateNutrition(stack, foundNutrients);
            if (!player.getEntityWorld().isRemote) {
                ((INutrientManager) player.getCapability(getNutritionCapability(), null)).add(foundNutrients, nutritionValue);
            } else {
                ClientProxy.localNutrition.add(foundNutrients, nutritionValue);
            }
        }
    }

    public static @Nullable Capability getNutritionCapability() {
        if (nutritionCapability == null) {
            try {
                Field f = CapabilityManager.class.getDeclaredField("providers");
                f.setAccessible(true);
                IdentityHashMap<String, Capability<?>> providers = (IdentityHashMap<String, Capability<?>>) f.get(CapabilityManager.INSTANCE);
                nutritionCapability = providers.get("ca.wescook.nutrition.capabilities.INutrientManager");
            } catch (Exception e) {
                LogHelper.error("Unable to retrieve Nutrition capability.");
            }
        }
        return nutritionCapability;
    }

}
