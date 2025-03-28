package com.invadermonky.magicultureintegrations.core.mixins.harvestcraft;

import com.llamalad7.mixinextras.sugar.Local;
import com.pam.harvestcraft.addons.RightClickHarvesting;
import com.pam.harvestcraft.blocks.growables.BlockPamFruitLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = RightClickHarvesting.class, remap = false)
public abstract class RightClickHarvestMixin {
    @Redirect(
            method = "harvestFruit",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/pam/harvestcraft/addons/RightClickHarvesting;dropItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)V"
            )
    )
    private static void harvestFruitMixin(ItemStack itemStack, World world, BlockPos pos, @Local(argsOnly = true) IBlockState state, @Local(argsOnly = true) EntityPlayer player) {
        if(state.getBlock() instanceof BlockPamFruitLog) {
            RayTraceResult trace = player.rayTrace(player.getAttributeMap().getAttributeInstance(EntityPlayer.REACH_DISTANCE).getAttributeValue(), 0);
            if(trace != null && trace.sideHit != null) {
                pos = pos.offset(trace.sideHit);
            }
        }
        if (!world.restoringBlockSnapshots && !world.isRemote) {
            float f = 0.5F;
            double d0 = (double)(world.rand.nextFloat() * f) + (double)0.25F;
            double d1 = (double)(world.rand.nextFloat() * f) + (double)0.25F;
            double d2 = (double)(world.rand.nextFloat() * f) + (double)0.25F;
            EntityItem entityItem = new EntityItem(world, (double)pos.getX() + d0, (double)pos.getY() + d1, (double)pos.getZ() + d2, itemStack);
            entityItem.setDefaultPickupDelay();
            world.spawnEntity(entityItem);
        }
    }
}
