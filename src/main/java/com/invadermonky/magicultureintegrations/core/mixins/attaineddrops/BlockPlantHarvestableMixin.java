package com.invadermonky.magicultureintegrations.core.mixins.attaineddrops;

import com.invadermonky.magicultureintegrations.api.block.IHarvestableCrop;
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
import shadows.attained.blocks.BlockBulb;
import shadows.attained.blocks.BlockPlant;

@Mixin(value = BlockPlant.class, remap = false)
public abstract class BlockPlantHarvestableMixin implements IHarvestableCrop {
    @Shadow
    @Final
    public static PropertyInteger CHARGE;

    @Shadow
    public abstract int getMaxAge();

    @Shadow
    public abstract int getAge(IBlockState state);

    @Override
    public BlockPos getHarvestPosition(World world, BlockPos cropPos) {
        return cropPos.up();
    }

    @Override
    public @NotNull HarvestResult getHarvestResult(World world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() instanceof BlockPlant) {
            boolean ageFlag = getAge(state) >= getMaxAge();
            boolean bulbFlag = world.getBlockState(this.getHarvestPosition(world, pos)).getBlock() instanceof BlockBulb;
            return ageFlag && bulbFlag ? HarvestResult.HARVEST : HarvestResult.CLAIM;
        }
        return HarvestResult.PASS;
    }

    @Override
    public @NotNull NonNullList<ItemStack> harvestCrop(@Nullable EntityPlayer player, World world, BlockPos pos, boolean silkTouch, int fortune) {
        NonNullList<ItemStack> drops = NonNullList.create();
        if (this.getHarvestResult(world, pos) == HarvestResult.HARVEST) {
            BlockPos harvestPos = this.getHarvestPosition(world, pos);
            IBlockState harvestState = world.getBlockState(harvestPos);
            harvestState.getBlock().getDrops(drops, world, harvestPos, harvestState, fortune);
            world.destroyBlock(pos.up(), false);
        }
        return drops;
    }
}
