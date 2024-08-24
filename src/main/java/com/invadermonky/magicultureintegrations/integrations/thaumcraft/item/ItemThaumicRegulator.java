package com.invadermonky.magicultureintegrations.integrations.thaumcraft.item;

import baubles.api.BaubleType;
import com.charles445.simpledifficulty.api.SDBlocks;
import com.invadermonky.magicultureintegrations.MagicultureIntegrations;
import com.invadermonky.magicultureintegrations.api.item.ItemBaubleMI;
import com.invadermonky.magicultureintegrations.api.mods.IAddition;
import com.invadermonky.magicultureintegrations.api.mods.simpledifficulty.SimpleDifficultyUtils;
import com.invadermonky.magicultureintegrations.api.mods.toughasnails.ToughAsNailsUtils;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.util.ModIds;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.items.IRechargable;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.api.items.RechargeHelper;
import thaumcraft.common.config.ConfigItems;
import toughasnails.api.TANBlocks;

public class ItemThaumicRegulator extends ItemBaubleMI implements IRechargable, IAddition {
    public static final ItemThaumicRegulator THAUMIC_REGULATOR = new ItemThaumicRegulator();
    private static final String ITEM_ID = "thaumic_regulator";

    public static final String TAG_ENERGY = "energy";

    public ItemThaumicRegulator() {
        this.setRegistryName(MagicultureIntegrations.MOD_ID, ITEM_ID);
        this.setTranslationKey(this.getRegistryName().toString());
        this.setCreativeTab(ConfigItems.TABTC);
        this.setMaxStackSize(1);

        //Thaumcraft Research needs to be registered asap.
        ThaumcraftApi.registerResearchLocation(new ResourceLocation(MagicultureIntegrations.MOD_ID, "research/thaumic_regulator"));
    }

    /*
        IBauble Methods
    */

    @Override
    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
        if(player.world.isRemote || !(player instanceof EntityPlayer) || ((EntityPlayer) player).isCreative())
            return;

        if(player.ticksExisted % ConfigHandlerMI.thaumcraft.survival_mods.thaumic_regulator.delay == 0) {
            boolean hasCharge = RechargeHelper.getCharge(itemstack) > 0;
            boolean used = false;
            int cost = ConfigHandlerMI.thaumcraft.survival_mods.thaumic_regulator.cost;

            int e = itemstack.hasTagCompound() ? itemstack.getTagCompound().getInteger(TAG_ENERGY) : 0;

            if(hasCharge && e > 0) {
                e -= cost;
                used = true;
            } else if (e <= 0 && RechargeHelper.consumeCharge(itemstack, player, 1)) {
                e += 60 - cost;
                used = true;
            }
            itemstack.setTagInfo(TAG_ENERGY, new NBTTagInt(e));


            if(used) {
                if (ModIds.simpledifficulty.isLoaded) {
                    SimpleDifficultyUtils.stabilizePlayerTemperature((EntityPlayer) player);
                }

                if (ModIds.tough_as_nails.isLoaded) {
                    ToughAsNailsUtils.stabilizePlayerTemperature((EntityPlayer) player);
                }
            }
        }
    }

    @Override
    public boolean willAutoSync(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemStack) {
        return BaubleType.CHARM;
    }

    /*
        IRechargable Methods
    */

    @Override
    public int getMaxCharge(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        return 200;
    }

    @Override
    public EnumChargeDisplay showInHud(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        return EnumChargeDisplay.NORMAL;
    }

    @Override
    public void registerRecipe() {

        ItemStack cooler = new ItemStack(Blocks.ICE);
        ItemStack heater = new ItemStack(Blocks.MAGMA);
        if(ModIds.simpledifficulty.isLoaded) {
            cooler = new ItemStack(SDBlocks.chiller);
            heater = new ItemStack(SDBlocks.heater);
        }
        if(ModIds.tough_as_nails.isLoaded) {
            cooler = new ItemStack(TANBlocks.temperature_coil, 1, 0);
            heater = new ItemStack(TANBlocks.temperature_coil, 1, 1);
        }
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(MagicultureIntegrations.MOD_ID, "ThaumicRegulator"), new InfusionRecipe(
                "THAUMICREGULATOR",
            new ItemStack(THAUMIC_REGULATOR),
            5,
            (new AspectList()).add(Aspect.FIRE, 60).add(Aspect.COLD, 60).add(Aspect.MAN, 30),
            new ItemStack(ItemsTC.baubles, 1, 4),
            new Object[] {cooler, ThaumcraftApiHelper.makeCrystal(Aspect.FIRE), heater, ThaumcraftApiHelper.makeCrystal(Aspect.COLD)}
        ));
    }

    @Override
    public boolean isEnabled() {
        return ConfigHandlerMI.thaumcraft.survival_mods.thaumic_regulator.enable;
    }
}
