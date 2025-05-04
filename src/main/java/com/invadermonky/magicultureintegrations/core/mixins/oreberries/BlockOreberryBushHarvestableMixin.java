package com.invadermonky.magicultureintegrations.core.mixins.oreberries;

import com.invadermonky.magicultureintegrations.api.block.IHarvestableCrop;
import josephcsible.oreberries.BlockOreberryBush;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

@Mixin(value = BlockOreberryBush.class, remap = false)
public abstract class BlockOreberryBushHarvestableMixin implements IHarvestableCrop {
    @Shadow
    @Final
    public static PropertyInteger AGE;

    @Shadow
    public abstract ItemStack getBerriesStack(Random rand);

    @Override
    public BlockPos getHarvestPosition(World world, BlockPos cropPos) {
        return cropPos;
    }

    @Override
    public @NotNull HarvestResult getHarvestResult(World world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() instanceof BlockOreberryBush) {
            return state.getValue(AGE) >= 3 ? HarvestResult.HARVEST : HarvestResult.CLAIM;
        }
        return HarvestResult.PASS;
    }

    @Override
    public @NotNull NonNullList<ItemStack> harvestCrop(@Nullable EntityPlayer player, World world, BlockPos pos, boolean silkTouch, int fortune) {
        NonNullList<ItemStack> drops = NonNullList.create();
        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() instanceof BlockOreberryBush && state.getBlock() instanceof IHarvestableCrop && this.getHarvestResult(world, pos) == HarvestResult.HARVEST) {
            drops.add(getBerriesStack(world.rand));
            world.setBlockState(pos, state.withProperty(AGE, 2));
        }
        return drops;
    }
}
