package com.invadermonky.magicultureintegrations.integrations.nutrition;

import com.invadermonky.magicultureintegrations.MagicultureIntegrations;
import com.invadermonky.magicultureintegrations.api.mods.IntegrationModule;
import com.invadermonky.magicultureintegrations.api.mods.nutrition.NutritionUtils;
import com.invadermonky.magicultureintegrations.config.MIConfigIntegrations;
import com.invadermonky.magicultureintegrations.config.MIConfigIntegrations.NutritionIntegrations.AutoFeedersIntegrations.AutoFeederDeviceConfig;
import com.invadermonky.magicultureintegrations.util.LogHelper;
import com.invadermonky.magicultureintegrations.util.ModIds;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class InitNutrition extends IntegrationModule {
    private static InitNutrition INSTANCE;
    private final Map<String, Tuple<Float, List<String>>> customFoods = new HashMap<>();

    public InitNutrition() {
        super("Nutrition");
        INSTANCE = this;
    }

    public static InitNutrition getInstance() {
        return INSTANCE;
    }

    @Override
    public void buildModIntegrations() {
    }

    @Override
    public void preInit() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void postInit() {
        syncConfig();
        registerAutoFeeder(ModIds.botania, "infinitefruit", MIConfigIntegrations.nutrition.autoFeeders.BotFruitOfGrisaia);
        registerAutoFeeder(ModIds.cyclic, "potion.saturation", MIConfigIntegrations.nutrition.autoFeeders.cyclicSaturation);
        registerAutoFeeder(ModIds.industrial_craft, "quantum_helmet", MIConfigIntegrations.nutrition.autoFeeders.IC2QuantumHelmet);
        registerAutoFeeder(ModIds.industrial_foregoing, "meat_feeder", MIConfigIntegrations.nutrition.autoFeeders.IFMeatFeeder);
        registerAutoFeeder(ModIds.new_crimson_revelations, "verdant_ring", MIConfigIntegrations.nutrition.autoFeeders.TCVerdantCharm);
        registerAutoFeeder(ModIds.thaumcraft, "verdant_charm", MIConfigIntegrations.nutrition.autoFeeders.TCVerdantCharm);
        registerAutoFeeder(ModIds.travelers_backpack, "travelers_backpack", MIConfigIntegrations.nutrition.autoFeeders.TBSunflowerBackpack);
        registerAutoFeeder(ModIds.tinkers_construct, "tasty", MIConfigIntegrations.nutrition.autoFeeders.TCTastyTrait);

        //Vanilla Saturation Potion (Supports Env. Tech, RFTools, PotionCore and many others)
        registerAutoFeeder(MobEffects.SATURATION.getRegistryName(), MIConfigIntegrations.nutrition.autoFeeders.saturationPotion);
    }

    @SubscribeEvent
    public void onItemUseFinish(LivingEntityUseItemEvent.Finish event) {
        if (event.getEntityLiving() instanceof EntityPlayer && !event.getEntityLiving().world.isRemote) {
            ItemStack stack = event.getItem();
            Tuple<Float, List<String>> nutrition = getCustomFoodStats(stack);
            if (nutrition != null) {
                NutritionUtils.applyNutrition((EntityPlayer) event.getEntityLiving(), nutrition.getFirst(), nutrition.getSecond().toArray(new String[0]));
            }
        }
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(MagicultureIntegrations.MOD_ID)) {
            syncConfig();
            buildModIntegrations();
        }
    }

    private void syncConfig() {
        customFoods.clear();
        for (String str : MIConfigIntegrations.nutrition.customFoods) {
            try {
                String[] split = str.split(";");
                if (split.length >= 3) {
                    String item = split[0];
                    float value = MathHelper.clamp(Float.parseFloat(split[1]), 0f, 100.0f);
                    List<String> nutrients = new ArrayList<>(Arrays.asList(split).subList(2, split.length));
                    customFoods.put(item, new Tuple<>(value, nutrients));
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                LogHelper.error("Invalid nutrition configuration string: " + str);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        Tuple<Float, List<String>> nutrition = getCustomFoodStats(stack);
        if (nutrition != null) {
            String tooltip = null;
            StringJoiner stringJoiner = new StringJoiner(", ");
            for (String nutrientName : nutrition.getSecond()) {
                stringJoiner.add(I18n.format("nutrient.nutrition:" + nutrientName));
            }

            String nutrientString = stringJoiner.toString();
            float nutritionValue = nutrition.getFirst();
            if (!nutrientString.equals("")) {
                tooltip = I18n.format("tooltip.nutrition:nutrients") + " " + TextFormatting.DARK_GREEN + nutrientString + TextFormatting.DARK_AQUA + " (" + String.format("%.1f", nutritionValue) + "%)";
            }

            if (tooltip != null) {
                event.getToolTip().add(tooltip);
            }

        }
    }

    public @Nullable Tuple<Float, List<String>> getCustomFoodStats(ItemStack stack) {
        if (!stack.isEmpty()) {
            String itemName = stack.getItem().getRegistryName().toString();
            if (customFoods.containsKey(itemName)) {
                return customFoods.get(itemName);
            } else if (customFoods.containsKey(itemName + ":" + stack.getMetadata())) {
                return customFoods.get(itemName + ":" + stack.getMetadata());
            }
        }
        return null;
    }

    private void registerAutoFeeder(ResourceLocation deviceName, AutoFeederDeviceConfig config) {
        NutritionUtils.parseFeederDeviceConfig(deviceName, config);
    }

    private void registerAutoFeeder(ModIds mod, String deviceId, AutoFeederDeviceConfig config) {
        if (mod.isLoaded) {
            registerAutoFeeder(new ResourceLocation(mod.modId, deviceId), config);
        }
    }
}
