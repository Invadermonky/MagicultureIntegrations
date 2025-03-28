package com.invadermonky.magicultureintegrations.core.mixins.harvestcraft;

import com.invadermonky.magicultureintegrations.util.ModIds;
import com.pam.harvestcraft.blocks.growables.BlockPamFruit;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.Optional;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import vazkii.botania.api.item.IHornHarvestable;

@Optional.Interface(modid = ModIds.ConstIds.botania, iface = "vazkii.botania.api.item.IHornHarvestable")
@Mixin(value = BlockPamFruit.class, remap = false)
public abstract class BlockPamFruitHornHarvestableMixin extends Block implements IHornHarvestable {
    public BlockPamFruitHornHarvestableMixin(Material blockMaterialIn, MapColor blockMapColorIn) {
        super(blockMaterialIn, blockMapColorIn);
    }

    @Shadow public abstract int getMatureAge();
    @Shadow public abstract void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune);

    @Shadow public abstract Item getFruitItem();

    @Optional.Method(modid = ModIds.ConstIds.botania)
    @Override
    public boolean canHornHarvest(World world, BlockPos blockPos, ItemStack hornStack, EnumHornType enumHornType) {
        return enumHornType == EnumHornType.WILD || enumHornType == EnumHornType.COVERING;
    }

    @Optional.Method(modid = ModIds.ConstIds.botania)
    @Override
    public boolean hasSpecialHornHarvest(World world, BlockPos blockPos, ItemStack hornStack, EnumHornType enumHornType) {
        return enumHornType == EnumHornType.WILD || enumHornType == EnumHornType.COVERING;
    }

    @Optional.Method(modid = ModIds.ConstIds.botania)
    @Override
    public void harvestByHorn(World world, BlockPos pos, ItemStack hornStack, EnumHornType enumHornType) {
        IBlockState state = world.getBlockState(pos);
        if(enumHornType == EnumHornType.CANOPY || (BlockPamFruit.fruitRemoval && state.getValue(BlockPamFruit.AGE) >= this.getMatureAge())) {
            this.dropBlockAsItem(world, pos, state, 0);
            world.playEvent(Constants.WorldEvents.BREAK_BLOCK_EFFECTS, pos, Block.getStateId(world.getBlockState(pos)));
        } else if(enumHornType == EnumHornType.WILD) {
            if(state.getValue(BlockPamFruit.AGE) >= this.getMatureAge()) {
                NonNullList<ItemStack> drops = NonNullList.create();
                this.getDrops(drops, world, pos, state, 0);
                drops.remove(drops.size() - 1);
                drops.forEach(drop -> Block.spawnAsEntity(world, pos, drop));
                world.setBlockState(pos, state.withProperty(BlockPamFruit.AGE, 0));
                world.playEvent(Constants.WorldEvents.BREAK_BLOCK_EFFECTS, pos, Block.getStateId(world.getBlockState(pos)));
            }
        }
    }
}
