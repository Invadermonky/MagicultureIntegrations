package com.invadermonky.magicultureintegrations.core.mixins.attaineddrops;

import com.invadermonky.magicultureintegrations.util.ModIds;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;
import org.spongepowered.asm.mixin.Mixin;
import shadows.attained.blocks.BlockBulb;
import shadows.attained.blocks.BlockPlant;
import vazkii.botania.api.item.IHornHarvestable;

@Optional.Interface(modid = ModIds.ConstIds.botania, iface = "vazkii.botania.api.item.IHornHarvestable", striprefs = true)
@Mixin(value = BlockPlant.class, remap = false)
public class BlockPlantMixin implements IHornHarvestable {

    @Optional.Method(modid = ModIds.ConstIds.botania)
    @Override
    public boolean canHornHarvest(World world, BlockPos blockPos, ItemStack itemStack, EnumHornType enumHornType) {
        return false;
    }

    @Optional.Method(modid = ModIds.ConstIds.botania)
    @Override
    public boolean hasSpecialHornHarvest(World world, BlockPos blockPos, ItemStack itemStack, EnumHornType enumHornType) {
        return enumHornType == EnumHornType.WILD;
    }

    @Optional.Method(modid = ModIds.ConstIds.botania)
    @Override
    public void harvestByHorn(World world, BlockPos pos, ItemStack itemStack, EnumHornType enumHornType) {
        IBlockState state = world.getBlockState(pos);
        IBlockState harvestState = world.getBlockState(pos.up());
        boolean harvestFlag = state.getBlock() instanceof BlockPlant
                && ((BlockPlant) state.getBlock()).getAge(state) == ((BlockPlant) state.getBlock()).getMaxAge()
                && harvestState.getBlock() instanceof BlockBulb;

        if(harvestFlag) {
            world.destroyBlock(pos.up(), true);
        }
    }
}
