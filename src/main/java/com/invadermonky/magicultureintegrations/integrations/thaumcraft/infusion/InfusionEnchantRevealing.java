package com.invadermonky.magicultureintegrations.integrations.thaumcraft.infusion;

import com.google.common.collect.ImmutableSet;
import com.invadermonky.magicultureintegrations.MagicultureIntegrations;
import com.invadermonky.magicultureintegrations.api.IAddition;
import com.invadermonky.magicultureintegrations.api.IProxy;
import com.invadermonky.magicultureintegrations.config.MIConfigAdditions;
import com.invadermonky.magicultureintegrations.integrations.thaumcraft.recipes.InfusionEnchantmentRecipeRevealing;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreIngredient;
import net.minecraftforge.registries.IForgeRegistry;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.items.IGoggles;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.client.lib.events.RenderEventHandler;
import thaumcraft.common.lib.crafting.InfusionEnchantmentRecipe;
import thaumcraft.common.lib.enchantment.EnumInfusionEnchantment;

import java.util.Set;

public class InfusionEnchantRevealing implements IAddition, IProxy {
    public EnumInfusionEnchantment REVEALING = EnumHelper.addEnum(EnumInfusionEnchantment.class, "REVEALING",
            new Class<?>[]{Set.class, int.class, String.class},
            ImmutableSet.of("helm"), 1, "MI_REVEALING_INFUSION");

    @Override
    public void preInitClient() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void init() {
        ThaumcraftApi.registerResearchLocation(new ResourceLocation(MagicultureIntegrations.MOD_ID, "research/revealing_infusion"));
    }

    @Override
    public void registerRecipes(IForgeRegistry<IRecipe> registry) {
        InfusionEnchantmentRecipe revealingInfusion = new InfusionEnchantmentRecipeRevealing(REVEALING,
                new AspectList().add(Aspect.AURA, 20).add(Aspect.PROTECT, 20).add(Aspect.SENSES, 40),
                new OreIngredient("slimeball"), new ItemStack(ItemsTC.goggles));

        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(MagicultureIntegrations.MOD_ID, "revealing_infusion"), revealingInfusion);
        ThaumcraftApi.addFakeCraftingRecipe(new ResourceLocation(MagicultureIntegrations.MOD_ID, "revealing_infusion_fake"), new InfusionEnchantmentRecipeRevealing(
                revealingInfusion, new ItemStack(Items.LEATHER_HELMET)));
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onBlockHighlight(DrawBlockHighlightEvent event) {
        EntityPlayer player = event.getPlayer();
        ItemStack helm = player.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
        RayTraceResult result = event.getTarget();
        if (result != null && result.typeOfHit == RayTraceResult.Type.BLOCK && this.isRevealingInfused(helm)) {
            TileEntity tile = player.world.getTileEntity(result.getBlockPos());
            if (tile instanceof IAspectContainer && ((IAspectContainer) tile).getAspects() != null && ((IAspectContainer) tile).getAspects().size() > 0) {
                boolean spaceAbove = event.getPlayer().world.isAirBlock(result.getBlockPos().up());

                float shift = 0.0F;
                if (RenderEventHandler.tagscale < 0.3F) {
                    RenderEventHandler.tagscale += 0.031F - RenderEventHandler.tagscale / 10.0F;
                }

                RenderEventHandler.drawTagsOnContainer(
                        result.getBlockPos().getX(),
                        ((float) result.getBlockPos().getY() + (spaceAbove ? 0.4F : 0.0F) + shift),
                        result.getBlockPos().getZ(),
                        ((IAspectContainer) tile).getAspects(),
                        220,
                        spaceAbove ? EnumFacing.UP : event.getTarget().sideHit,
                        event.getPartialTicks()
                );
            }
        }
    }

    public boolean isRevealingInfused(ItemStack stack) {
        if (!stack.isEmpty() && !(stack.getItem() instanceof IGoggles)) {
            return EnumInfusionEnchantment.getInfusionEnchantmentLevel(stack, REVEALING) > 0;
        }
        return false;
    }

    @Override
    public boolean isEnabled() {
        return MIConfigAdditions.thaumcraft.enableRevealingInfusion;
    }
}
