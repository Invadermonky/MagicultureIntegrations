package com.invadermonky.magicultureintegrations.core.mixins.bloodmagic;

import WayofTime.bloodmagic.ritual.IMasterRitualStone;
import WayofTime.bloodmagic.ritual.types.RitualHarvest;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Iterator;

@Mixin(value = RitualHarvest.class, remap = false)
public abstract class RitualHarvestMixin {
    @ModifyExpressionValue(
            method = "harvestBlock",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/List;iterator()Ljava/util/Iterator;",
                    ordinal = 1
            )
    )
    private static Iterator<ItemStack> harvestBlockMixin(Iterator<ItemStack> original, @Local(name = "world") World world, @Local(name = "cropPos") BlockPos cropPos, @Local(name = "controllerPos") BlockPos controllerPos) {
        IBlockState cropState = world.getBlockState(cropPos);
        TileEntity tile = world.getTileEntity(controllerPos);
        if (tile instanceof IMasterRitualStone) {
            NonNullList<ItemStack> drops = NonNullList.create();
            while (original.hasNext()) {
                drops.add(original.next());
            }
            IMasterRitualStone masterRitualStone = (IMasterRitualStone) tile;
            EntityPlayer player = masterRitualStone.getOwnerNetwork().getPlayer();
            float chance = ForgeEventFactory.fireBlockHarvesting(drops, world, cropPos, cropState, 0, 1.0f, false, player);
            drops.removeIf(drop -> drop.isEmpty() || chance < world.rand.nextFloat());
            original = drops.iterator();
        }
        return original;
    }
}
