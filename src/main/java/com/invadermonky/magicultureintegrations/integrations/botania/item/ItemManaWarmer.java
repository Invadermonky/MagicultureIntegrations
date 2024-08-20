package com.invadermonky.magicultureintegrations.integrations.botania.item;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.mana.IManaUsingItem;

public class ItemManaWarmer extends Item implements IBauble, IManaUsingItem {
    private static final int COST = 1;
    private static final int COST_INTERVAL = 10;


    @Override
    public BaubleType getBaubleType(ItemStack itemStack) {
        return null;
    }

    @Override
    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
        IBauble.super.onWornTick(itemstack, player);
    }

    @Override
    public void onEquipped(ItemStack itemstack, EntityLivingBase player) {
        IBauble.super.onEquipped(itemstack, player);
    }

    @Override
    public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
        IBauble.super.onUnequipped(itemstack, player);
    }

    @Override
    public boolean canEquip(ItemStack itemstack, EntityLivingBase player) {
        return IBauble.super.canEquip(itemstack, player);
    }

    @Override
    public boolean canUnequip(ItemStack itemstack, EntityLivingBase player) {
        return IBauble.super.canUnequip(itemstack, player);
    }

    @Override
    public boolean willAutoSync(ItemStack itemstack, EntityLivingBase player) {
        return IBauble.super.willAutoSync(itemstack, player);
    }

    @Override
    public boolean usesMana(ItemStack itemStack) {
        return true;
    }
}
