package com.invadermonky.magicultureintegrations.core.mixins.forestry;

import forestry.arboriculture.blocks.BlockFruitPod;
import forestry.arboriculture.tiles.TileFruitPod;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import vazkii.botania.api.item.IHornHarvestable;

@Mixin(value = BlockFruitPod.class, remap = false)
public class BlockFruitPodHornHarvestableMixin implements IHornHarvestable {
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
        TileEntity tile = world.getTileEntity(blockPos);
        if (tile instanceof TileFruitPod) {
            if (!((TileFruitPod) tile).canMature())
                ((TileFruitPod) tile).pickFruit(ItemStack.EMPTY).stream()
                        .filter(stack -> !stack.isEmpty())
                        .forEach(stack -> Block.spawnAsEntity(world, blockPos, stack));
        }
    }

}
