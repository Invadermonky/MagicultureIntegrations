package com.invadermonky.magicultureintegrations.integrations.astralsorcery.mods;

import com.invadermonky.magicultureintegrations.api.IProxy;
import com.invadermonky.magicultureintegrations.config.MIConfigIntegrations;
import com.oblivioussp.spartanweaponry.entity.projectile.EntityThrownWeapon;
import com.oblivioussp.spartanweaponry.item.ItemThrowingWeapon;
import hellfirepvp.astralsorcery.common.auxiliary.SwordSharpenHelper;
import hellfirepvp.astralsorcery.common.crafting.grindstone.GrindstoneRecipe;
import hellfirepvp.astralsorcery.common.crafting.grindstone.GrindstoneRecipeRegistry;
import hellfirepvp.astralsorcery.common.data.config.Config;
import hellfirepvp.astralsorcery.common.item.crystal.CrystalPropertyItem;
import hellfirepvp.astralsorcery.common.util.ItemUtils;
import hellfirepvp.astralsorcery.common.util.nbt.NBTHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;

public class ASSpartanWeaponry implements IProxy {

    public static boolean canBeSharpened(@Nonnull ItemStack stack) {
        if (stack.isEmpty()) {
            return false;
        } else {
            Item item = stack.getItem();
            if (item instanceof CrystalPropertyItem || !(item instanceof ItemThrowingWeapon)) {
                return false;
            } else if (SwordSharpenHelper.isSharpenableItem(stack)) {
                return false;
            }
            return !SwordSharpenHelper.blacklistedSharpenableSwordClassNames.contains(item.getClass().getName());
        }
    }

    public static boolean isWeaponSharpened(@Nonnull ItemStack stack) {
        return canBeSharpened(stack) && stack.hasTagCompound() && NBTHelper.getData(stack).getBoolean("sharp") && !SwordSharpenHelper.isSwordSharpened(stack);
    }

    public static void setSharpened(@Nonnull ItemStack stack) {
        NBTHelper.getData(stack).setBoolean("sharp", true);
    }

    @Override
    public void preInit() {
        if (MIConfigIntegrations.astral_sorcery.spartan_weaponry_thrown)
            MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void registerRecipe(RegistryEvent.Register<IRecipe> event) {
        GrindstoneRecipeRegistry.registerGrindstoneRecipe(new ThrowingWeaponSharpeningRecipe());
    }

    @SubscribeEvent
    public void onDamage(LivingHurtEvent event) {
        EntityLivingBase living = event.getEntityLiving();
        DamageSource source = event.getSource();
        if (living == null || living.getEntityWorld().isRemote)
            return;

        if (source.getTrueSource() != null) {
            EntityPlayer player;
            if (source.getTrueSource() instanceof EntityPlayer) {
                player = (EntityPlayer) source.getTrueSource();
            } else if (source.getTrueSource() instanceof EntityThrownWeapon) {
                Entity shooter = ((EntityThrownWeapon) source.getTrueSource()).shootingEntity;
                if (shooter instanceof EntityPlayer) {
                    player = (EntityPlayer) shooter;
                } else {
                    return;
                }
            } else {
                return;
            }
            ItemStack held = player.getHeldItemMainhand();
            if (isWeaponSharpened(held)) {
                event.setAmount(event.getAmount() * (1 + ((float) Config.swordSharpMultiplier)));
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onTooltip(ItemTooltipEvent event) {
        List<String> toolTip = event.getToolTip();
        ItemStack stack = event.getItemStack();
        if (isWeaponSharpened(stack)) {
            List<String> newTooltip = new LinkedList<>();
            if (toolTip.size() > 1) {
                newTooltip.addAll(toolTip);
                newTooltip.add(1, I18n.format("misc.sword.sharpened", Math.round(Config.swordSharpMultiplier * 100) + "%"));
            } else {
                newTooltip.add(I18n.format("misc.sword.sharpened", Math.round(Config.swordSharpMultiplier * 100) + "%"));
                newTooltip.addAll(toolTip);
            }
            toolTip.clear();
            toolTip.addAll(newTooltip);
        }
    }

    public static class ThrowingWeaponSharpeningRecipe extends GrindstoneRecipe {
        public ThrowingWeaponSharpeningRecipe() {
            super(ItemStack.EMPTY, ItemStack.EMPTY, 40);
        }

        @Override
        public boolean matches(ItemStack stackIn) {
            return !stackIn.isEmpty() && canBeSharpened(stackIn) && !isWeaponSharpened(stackIn);
        }

        @Override
        public boolean isValid() {
            return true;
        }

        @Override
        public @NotNull GrindResult grind(ItemStack stackIn) {
            if (canBeSharpened(stackIn) && rand.nextInt(this.chance) == 0) {
                ItemStack copy = ItemUtils.copyStackWithSize(stackIn, stackIn.getCount());
                setSharpened(copy);
                return GrindResult.itemChange(copy);
            } else {
                return GrindResult.failNoOp();
            }
        }
    }
}
