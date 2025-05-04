package com.invadermonky.magicultureintegrations.integrations.astralsorcery.client;

import com.invadermonky.magicultureintegrations.integrations.astralsorcery.block.tile.TileCrystalSorter;
import hellfirepvp.astralsorcery.client.effect.EffectHelper;
import hellfirepvp.astralsorcery.client.effect.fx.EntityFXFacingParticle;
import hellfirepvp.astralsorcery.client.util.RenderingUtils;
import hellfirepvp.astralsorcery.common.item.base.ItemHighlighted;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;

@SideOnly(Side.CLIENT)
public class RenderCrystalSorter extends TileEntitySpecialRenderer<TileCrystalSorter> {
    private Color highlightColor = Color.WHITE;

    @Override
    public void render(TileCrystalSorter te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        ItemStack crystalStack = te.getProcessingCrystal();
        if (canRenderCrystal(te, crystalStack)) {
            if (crystalStack.getItem() instanceof ItemHighlighted) {
                this.highlightColor = ((ItemHighlighted) crystalStack.getItem()).getHightlightColor(crystalStack);
            }
            RenderingUtils.renderItemAsEntity(crystalStack, x, y + 0.25, z, partialTicks, te.getTicksExisted());
            this.renderCraftingParticles(te.getWorld(), te.getPos());
        }
    }

    private boolean canRenderCrystal(TileCrystalSorter tile, ItemStack stack) {
        BlockPos posUp = tile.getPos().up();
        IBlockState state = tile.getWorld().getBlockState(posUp);
        return !stack.isEmpty() && (tile.getWorld().isAirBlock(posUp) || state.isTranslucent() && !state.isOpaqueCube());

    }

    private void renderCraftingParticles(World world, BlockPos pos) {
        if (world.getTotalWorldTime() % 5L == 0) {
            EntityFXFacingParticle p = EffectHelper.genericFlareParticle(
                    pos.getX() + 0.5 + (double) world.rand.nextFloat() * 0.2 * (double) (world.rand.nextBoolean() ? 1 : -1),
                    pos.getY() + 1.25 + (double) world.rand.nextFloat() * 0.2 * (double) (world.rand.nextBoolean() ? 1 : -1),
                    pos.getZ() + 0.5 + (double) world.rand.nextFloat() * 0.2 * (double) (world.rand.nextBoolean() ? 1 : -1));
            p.motion(
                    (double) world.rand.nextFloat() * 0.05 * (double) (world.rand.nextBoolean() ? 1 : -1),
                    (double) world.rand.nextFloat() * 0.1,
                    (double) world.rand.nextFloat() * 0.05 * (double) (world.rand.nextBoolean() ? 1 : -1));
            p.gravity(0.01);
            p.scale(0.2F).setColor(this.highlightColor);
        }
    }
}
