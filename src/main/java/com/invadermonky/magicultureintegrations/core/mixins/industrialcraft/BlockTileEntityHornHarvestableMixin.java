package com.invadermonky.magicultureintegrations.core.mixins.industrialcraft;

import com.invadermonky.magicultureintegrations.util.ModIds;
import ic2.core.block.BlockTileEntity;
import ic2.core.crop.TileEntityCrop;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.Optional;
import org.spongepowered.asm.mixin.Mixin;
import vazkii.botania.api.item.IHornHarvestable;

import java.util.List;

@Optional.Interface(modid = ModIds.ConstIds.botania, iface = "vazkii.botania.api.item.IHornHarvestable")
@Mixin(value = BlockTileEntity.class, remap = false)
public abstract class BlockTileEntityHornHarvestableMixin extends Block implements IHornHarvestable {
    public BlockTileEntityHornHarvestableMixin(Material materialIn) {
        super(materialIn);
    }

    @Optional.Method(modid = ModIds.ConstIds.botania)
    @Override
    public boolean canHornHarvest(World world, BlockPos blockPos, ItemStack itemStack, EnumHornType enumHornType) {
        return enumHornType == EnumHornType.WILD;
    }

    @Optional.Method(modid = ModIds.ConstIds.botania)
    @Override
    public boolean hasSpecialHornHarvest(World world, BlockPos blockPos, ItemStack itemStack, EnumHornType enumHornType) {
        return enumHornType == EnumHornType.WILD;
    }

    @Optional.Method(modid = ModIds.ConstIds.botania)
    @Override
    public void harvestByHorn(World world, BlockPos blockPos, ItemStack itemStack, EnumHornType enumHornType) {
        TileEntity tile = world.getTileEntity(blockPos);
        if (tile instanceof TileEntityCrop) {
            TileEntityCrop tileCrop = (TileEntityCrop) tile;
            if (tileCrop.getCrop() != null && tileCrop.getCrop().canBeHarvested(tileCrop)) {
                List<ItemStack> harvest = tileCrop.performHarvest();
                if (harvest != null) {
                    world.playEvent(Constants.WorldEvents.BREAK_BLOCK_EFFECTS, blockPos, Block.getStateId(world.getBlockState(blockPos)));
                    harvest.forEach(stack -> spawnAsEntity(world, blockPos, stack));
                }
            }
        }
    }
}
