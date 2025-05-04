package com.invadermonky.magicultureintegrations.core.mixins.harvestcraft;

import com.invadermonky.magicultureintegrations.api.block.IHarvestableCrop;
import com.pam.harvestcraft.blocks.growables.BlockPamFruit;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = BlockPamFruit.class, remap = false)
public abstract class BlockPamFruitHarvestableMixin implements IHarvestableCrop {
    @Shadow
    @Final
    private Item fruitItem;

    @Shadow
    public abstract int getMatureAge();

    @Shadow
    public abstract Item getFruitItem();

    @Override
    public BlockPos getHarvestPosition(World world, BlockPos cropPos) {
        return cropPos;
    }

    @Override
    public @NotNull HarvestResult getHarvestResult(World world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() instanceof BlockPamFruit) {
            return state.getValue(BlockPamFruit.AGE) >= this.getMatureAge() ? HarvestResult.HARVEST : HarvestResult.CLAIM;
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
            if (BlockPamFruit.fruitRemoval) {
                world.setBlockToAir(harvestPos);
            } else {
                world.setBlockState(harvestPos, harvestState.withProperty(BlockPamFruit.AGE, 0));
                drops.remove(drops.size() - 1);
            }
        }
        return drops;
    }
}
