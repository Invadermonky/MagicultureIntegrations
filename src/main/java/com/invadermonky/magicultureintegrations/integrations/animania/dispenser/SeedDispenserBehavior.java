package com.invadermonky.magicultureintegrations.integrations.animania.dispenser;

import com.animania.common.blocks.BlockSeeds;
import com.animania.common.handler.BlockHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class SeedDispenserBehavior extends BehaviorDefaultDispenseItem {
    private final Block cropBlock;
    private final BlockSeeds.EnumType seedType;

    public SeedDispenserBehavior(Block cropBlock, BlockSeeds.EnumType seedType) {
        this.cropBlock = cropBlock;
        this.seedType = seedType;
    }

    @Override
    protected @NotNull ItemStack dispenseStack(IBlockSource source, @NotNull ItemStack stack) {
        EnumFacing facing = source.getBlockState().getValue(BlockDispenser.FACING);
        BlockPos pos = source.getBlockPos().offset(facing);
        World world = source.getWorld();
        if (world.isAirBlock(pos)) {
            if (this.cropBlock.canPlaceBlockAt(world, pos)) {
                world.setBlockState(pos, this.cropBlock.getDefaultState());
                stack.shrink(1);
                return stack;
            } else if (BlockHandler.blockSeeds.canPlaceBlockAt(world, pos)) {
                world.setBlockState(pos, BlockHandler.blockSeeds.getDefaultState().withProperty(BlockSeeds.VARIANT, this.seedType));
                stack.shrink(1);
                return stack;
            }
        }
        return super.dispenseStack(source, stack);
    }
}
