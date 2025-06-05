package com.invadermonky.magicultureintegrations.api.mods.nutrition;

import ca.wescook.nutrition.capabilities.INutrientManager;
import ca.wescook.nutrition.nutrients.Nutrient;
import ca.wescook.nutrition.proxy.ClientProxy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AutoFeederDevice {
    protected final ResourceLocation feederDevice;
    protected final float nutrientValue;
    protected final float maxNutrition;
    protected final List<Nutrient> nutrients;

    public AutoFeederDevice(ResourceLocation feederDevice, float nutrientValue, float maxNutrition, List<Nutrient> nutrients) {
        this.feederDevice = feederDevice;
        this.nutrientValue = nutrientValue;
        this.maxNutrition = maxNutrition;
        this.nutrients = nutrients;
    }

    public ResourceLocation getFeederDevice() {
        return feederDevice;
    }

    public float getNutrientValue() {
        return nutrientValue;
    }

    public float getMaxNutrition() {
        return maxNutrition;
    }

    public List<Nutrient> getNutrients() {
        return nutrients;
    }

    @SuppressWarnings("unchecked")
    public void applyNutritionalValue(EntityPlayer player) {
        if (player.world.isRemote)
            return;

        Capability<INutrientManager> capability = NutritionUtils.getNutritionCapability();
        if (capability != null) {
            INutrientManager manager = player.getCapability(capability, null);
            if (manager != null) {
                List<Nutrient> toAdd = new ArrayList<>();
                for (Nutrient nutrient : this.nutrients) {
                    float current = manager.get(nutrient);
                    if (current < this.maxNutrition) {
                        toAdd.add(nutrient);
                    }
                }

                if (!toAdd.isEmpty()) {
                    if (!player.world.isRemote) {
                        manager.add(toAdd, this.nutrientValue);
                    } else {
                        ClientProxy.localNutrition.add(toAdd, this.nutrientValue);
                    }
                }
            }
        }
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getFeederDevice());
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof AutoFeederDevice))
            return false;
        AutoFeederDevice device = (AutoFeederDevice) object;
        return Objects.equals(getFeederDevice(), device.getFeederDevice());
    }
}
