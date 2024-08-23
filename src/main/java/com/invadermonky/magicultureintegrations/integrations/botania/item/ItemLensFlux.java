package com.invadermonky.magicultureintegrations.integrations.botania.item;

import com.invadermonky.magicultureintegrations.MagicultureIntegrations;
import com.invadermonky.magicultureintegrations.api.mods.IAddition;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.integrations.botania.item.lens.LensFlux;
import com.invadermonky.magicultureintegrations.util.StringHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.mana.*;
import vazkii.botania.common.Botania;
import vazkii.botania.common.core.BotaniaCreativeTab;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.lens.ItemLens;
import vazkii.botania.common.item.lens.Lens;

import java.awt.*;
import java.util.List;

public class ItemLensFlux extends Item implements ILensControl, ICompositableLens, ITinyPlanetExcempt, IAddition {
    public static final ItemLensFlux FLUX_LENS = new ItemLensFlux();
    private static final String ITEM_ID = "lens_flux";

    public ItemLensFlux() {
        this.setRegistryName(MagicultureIntegrations.MOD_ID, ITEM_ID);
        this.setTranslationKey(getRegistryName().toString());
        this.setCreativeTab(BotaniaCreativeTab.INSTANCE);
        this.setMaxStackSize(1);
    }

    public static Lens getLens() {
        return new LensFlux();
    }

    public static boolean isBlacklisted(ItemStack lens1, ItemStack lens2) {
        return (lens1.getItem() == ModItems.lens && lens1.getMetadata() == 0) || (lens2.getItem() == ModItems.lens && lens2.getMetadata() == 0);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        int storedColor = ItemLens.getStoredColor(stack);
        if (storedColor != -1) {
            tooltip.add(I18n.format("botaniamisc.color", I18n.format("botania.color" + storedColor)));
        }
    }

    private String getItemShortTermName(ItemStack stack) {
        if(stack.getItem() == ModItems.lens) {
            return net.minecraft.util.text.translation.I18n.translateToLocal(stack.getTranslationKey().replaceAll("item.", "item.botania:") + ".short");
        }
        return I18n.format(StringHelper.getTranslationKey(ITEM_ID, "item", "short"));
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        ItemStack compositeLens = this.getCompositeLens(stack);
        return compositeLens.isEmpty() ? super.getItemStackDisplayName(stack) : String.format(net.minecraft.util.text.translation.I18n.translateToLocal("item.botania:compositeLens.name"), this.getItemShortTermName(stack), this.getItemShortTermName(compositeLens));
    }

    @Override
    public int getManaToTransfer(IManaBurst burst, EntityThrowable entity, ItemStack stack, IManaReceiver receiver) {
        return getLens().getManaToTransfer(burst, entity, stack, receiver);
    }

    @Override
    public void apply(ItemStack stack, BurstProperties props) {
        int storedColor = ItemLens.getStoredColor(stack);
        if(storedColor != -1) {
            props.color = this.getLensColor(stack);
        }

        getLens().apply(stack, props);
        ItemStack compositeLens = this.getCompositeLens(stack);
        if(!compositeLens.isEmpty() && compositeLens.getItem() instanceof ILens) {
            ((ILens) compositeLens.getItem()).apply(compositeLens, props);
        }
    }

    @Override
    public boolean collideBurst(IManaBurst burst, RayTraceResult rayTrace, boolean isManaBlock, boolean dead, ItemStack stack) {
        EntityThrowable entity = (EntityThrowable) burst;
        dead = getLens().collideBurst(burst, entity, rayTrace, isManaBlock, dead, stack);
        ItemStack compositeLens = getCompositeLens(stack);
        if(!compositeLens.isEmpty() && compositeLens.getItem() instanceof ILens) {
            dead = ((ILens) compositeLens.getItem()).collideBurst(burst, rayTrace, isManaBlock, dead, compositeLens);
        }
        return dead;
    }

    @Override
    public void updateBurst(IManaBurst burst, ItemStack stack) {
        EntityThrowable entity = (EntityThrowable) burst;
        int storedColor = ItemLens.getStoredColor(stack);
        if(storedColor == 16 && entity.world.isRemote) {
            burst.setColor(this.getLensColor(stack));
        }

        getLens().updateBurst(burst, entity, stack);
        ItemStack compositeLens = getCompositeLens(stack);
        if(!compositeLens.isEmpty() && compositeLens.getItem() instanceof ILens) {
            ((ILens) compositeLens.getItem()).updateBurst(burst, compositeLens);
        }
    }

    @Override
    public boolean doParticles(IManaBurst iManaBurst, ItemStack itemStack) {
        return true;
    }

    @Override
    public boolean isControlLens(ItemStack itemStack) {
        return false;
    }

    @Override
    public boolean allowBurstShooting(ItemStack stack, IManaSpreader spreader, boolean redstone) {
        return getLens().allowBurstShooting(stack, spreader, redstone);
    }

    @Override
    public void onControlledSpreaderTick(ItemStack stack, IManaSpreader spreader, boolean redstone) {
        getLens().onControlledSpreaderTick(stack, spreader, redstone);
    }

    @Override
    public void onControlledSpreaderPulse(ItemStack stack, IManaSpreader spreader, boolean redstone) {
        getLens().onControlledSpreaderPulse(stack, spreader, redstone);
    }

    @Override
    public int getLensColor(ItemStack stack) {
        int storedColor = ItemLens.getStoredColor(stack);
        if (storedColor == -1) {
            return 16777215;
        } else {
            return storedColor == 16 ? Color.HSBtoRGB((float)(Botania.proxy.getWorldElapsedTicks() * 2L % 360L) / 360.0F, 1.0F, 1.0F) : EnumDyeColor.byMetadata(storedColor).colorValue;
        }
    }

    @Override
    public boolean canCombineLenses(ItemStack sourceLens, ItemStack compositeLens) {
        ICompositableLens sourceItem = (ICompositableLens) sourceLens.getItem();
        ICompositableLens compositeItem = (ICompositableLens) compositeLens.getItem();
        if(sourceItem == compositeItem && sourceLens.getMetadata() == compositeLens.getMetadata()) {
            return false;
        } else if(sourceItem.isCombinable(sourceLens) && compositeItem.isCombinable(compositeLens)) {
            boolean bl = isBlacklisted(sourceLens, compositeLens);
            return !isBlacklisted(sourceLens, compositeLens);
        }
        return false;
    }

    @Override
    public ItemStack getCompositeLens(ItemStack stack) {
        NBTTagCompound cmp = ItemNBTHelper.getCompound(stack, "compositeLens", true);
        return cmp == null ? ItemStack.EMPTY : new ItemStack(cmp);
    }

    @Override
    public ItemStack setCompositeLens(ItemStack sourceLens, ItemStack compositeLens) {
        if (!compositeLens.isEmpty()) {
            NBTTagCompound cmp = compositeLens.writeToNBT(new NBTTagCompound());
            ItemNBTHelper.setCompound(sourceLens, "compositeLens", cmp);
        }

        return sourceLens;
    }

    @Override
    public int getProps(ItemStack itemStack) {
        return 0;
    }

    @Override
    public boolean isCombinable(ItemStack itemStack) {
        return true;
    }

    @Override
    public boolean shouldPull(ItemStack itemStack) {
        return false;
    }


    /*
        IAddition Methods
    */

    @Override
    public void registerRecipe() {
        //TODO: Register new recipe for attaching the lens to the mana gun.
    }

    @Override
    public boolean isEnabled() {
        return ConfigHandlerMI.botania.flowers.auromeria.enableAuromeria;
    }

}