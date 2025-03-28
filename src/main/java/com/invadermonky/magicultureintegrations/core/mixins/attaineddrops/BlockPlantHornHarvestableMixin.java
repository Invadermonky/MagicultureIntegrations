package com.invadermonky.magicultureintegrations.core.mixins.attaineddrops;

import com.invadermonky.magicultureintegrations.util.ModIds;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import shadows.attained.blocks.BlockBulb;
import shadows.attained.blocks.BlockPlant;
import vazkii.botania.api.item.IHornHarvestable;

@Optional.Interface(modid = ModIds.ConstIds.botania, iface = "vazkii.botania.api.item.IHornHarvestable")
@Mixin(value = BlockPlant.class, remap = false)
public abstract class BlockPlantHornHarvestableMixin implements IHornHarvestable {
    @Shadow public abstract int getAge(IBlockState state);
    @Shadow public abstract int getMaxAge();

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
    public void harvestByHorn(World world, BlockPos pos, ItemStack hornStack, EnumHornType enumHornType) {
        IBlockState state = world.getBlockState(pos);
        if(state.getBlock() instanceof BlockPlant && enumHornType == EnumHornType.WILD) {
            boolean cropFlag = getAge(state) >= getMaxAge();
            boolean bulbFlag = world.getBlockState(pos.up()).getBlock() instanceof BlockBulb;
            if(cropFlag && bulbFlag) {
                BlockPos harvestPos = pos.up();
                IBlockState harvestState = world.getBlockState(harvestPos);
                harvestState.getBlock().dropBlockAsItem(world, harvestPos, harvestState, 0);
            }
        }
    }
}
