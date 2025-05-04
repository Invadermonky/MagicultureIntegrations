package com.invadermonky.magicultureintegrations.core.mixins.harvestcraft;

import com.pam.harvestcraft.blocks.growables.BlockPamFruitLog;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import vazkii.botania.api.item.IHornHarvestable;

@Mixin(value = BlockPamFruitLog.class, remap = false)
public abstract class BlockPamFruitLogHornHarvestableMixin implements IHornHarvestable {
    @Shadow
    public abstract int getMatureAge();

    @Shadow
    public abstract void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune);

    @Override
    public boolean canHornHarvest(World world, BlockPos blockPos, ItemStack itemStack, EnumHornType enumHornType) {
        return enumHornType == EnumHornType.WILD;
    }

    @Override
    public boolean hasSpecialHornHarvest(World world, BlockPos blockPos, ItemStack itemStack, EnumHornType enumHornType) {
        return enumHornType == EnumHornType.WILD;
    }

    @Override
    public void harvestByHorn(World world, BlockPos pos, ItemStack hornStack, EnumHornType enumHornType) {
        IBlockState state = world.getBlockState(pos);
        if (state.getValue(BlockPamFruitLog.AGE) >= this.getMatureAge()) {
            NonNullList<ItemStack> drops = NonNullList.create();
            this.getDrops(drops, world, pos, state, 0);
            drops.remove(drops.size() - 1);
            drops.stream()
                    .filter(drop -> !drop.isEmpty())
                    .forEach(drop -> Block.spawnAsEntity(world, pos.offset(EnumFacing.HORIZONTALS[world.rand.nextInt(EnumFacing.HORIZONTALS.length)]), drop));
            world.playEvent(Constants.WorldEvents.BREAK_BLOCK_EFFECTS, pos, Block.getStateId(state));
            world.setBlockState(pos, state.withProperty(BlockPamFruitLog.AGE, 0));
        }
    }
}
