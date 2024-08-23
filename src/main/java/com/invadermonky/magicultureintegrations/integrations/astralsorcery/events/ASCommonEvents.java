package com.invadermonky.magicultureintegrations.integrations.astralsorcery.events;

import com.invadermonky.magicultureintegrations.api.events.ICommonEvents;
import com.invadermonky.magicultureintegrations.util.StringHelper;
import hellfirepvp.astralsorcery.common.base.FluidRarityRegistry;
import hellfirepvp.astralsorcery.common.constellation.distribution.ConstellationSkyHandler;
import hellfirepvp.astralsorcery.common.item.tool.ItemSkyResonator;
import hellfirepvp.astralsorcery.common.item.tool.ItemSkyResonator.ResonatorUpgrade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class ASCommonEvents implements ICommonEvents {
    @Override
    public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        World world = event.getWorld();
        double dstr = ConstellationSkyHandler.getInstance().getCurrentDaytimeDistribution(event.getWorld());
        if(world.isRemote || dstr <= 1.0E-4)
            return;

        ItemStack stack = event.getItemStack();
        if(ItemSkyResonator.hasUpgrade(stack, ResonatorUpgrade.FLUID_FIELDS) && ItemSkyResonator.getCurrentUpgrade(event.getEntityPlayer(), stack) == ResonatorUpgrade.FLUID_FIELDS) {
            FluidRarityRegistry.ChunkFluidEntry fluidEntry = FluidRarityRegistry.getChunkEntry(world.getChunk(event.getPos()));
            FluidStack fluid = fluidEntry == null ? new FluidStack(FluidRegistry.WATER, 1) : fluidEntry.tryDrain(1, false);
            if (fluid == null || fluid.getFluid() == null) {
                fluid = new FluidStack(FluidRegistry.WATER, 1);
            }
            event.getEntityPlayer().sendStatusMessage(new TextComponentTranslation(StringHelper.getTranslationKey("astral_sorcery_reservoir", "chat"), fluid.getLocalizedName()), true);
        }
    }
}
