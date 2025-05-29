package com.invadermonky.magicultureintegrations.core.mixins.newcrimsonrevelations;

import mod.icarus.crimsonrevelations.block.CRBlockManaPod;
import mod.icarus.crimsonrevelations.init.CRBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import vazkii.botania.api.item.IHornHarvestable;

import java.util.ArrayList;

@Mixin(value = CRBlockManaPod.class, remap = false)
public abstract class CRBlockManaPodHornHarvestableMixin extends Block implements IHornHarvestable {
    @Shadow
    @Final
    public static PropertyInteger AGE;

    public CRBlockManaPodHornHarvestableMixin(Material materialIn) {
        super(materialIn);
    }

    @Shadow
    public abstract ArrayList<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune);

    @Override
    public boolean canHornHarvest(World world, BlockPos blockPos, ItemStack itemStack, EnumHornType enumHornType) {
        return enumHornType == EnumHornType.WILD;
    }

    @Override
    public boolean hasSpecialHornHarvest(World world, BlockPos blockPos, ItemStack itemStack, EnumHornType enumHornType) {
        return enumHornType == EnumHornType.WILD;
    }

    @Override
    public void harvestByHorn(World world, BlockPos blockPos, ItemStack itemStack, EnumHornType enumHornType) {
        IBlockState state = world.getBlockState(blockPos);
        if (state.getBlock() == CRBlocks.MANA_POD && enumHornType == EnumHornType.WILD) {
            if (state.getValue(AGE) >= 8) {
                world.setBlockState(blockPos, state.getBlock().getDefaultState());
                world.playEvent(Constants.WorldEvents.BREAK_BLOCK_EFFECTS, blockPos, Block.getStateId(state));
                getDrops(world, blockPos, state, 0).forEach(stack -> spawnAsEntity(world, blockPos, stack));
            }
        }
    }
}
