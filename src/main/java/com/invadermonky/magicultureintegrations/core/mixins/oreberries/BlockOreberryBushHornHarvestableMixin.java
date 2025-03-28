package com.invadermonky.magicultureintegrations.core.mixins.oreberries;

import com.invadermonky.magicultureintegrations.util.ModIds;
import josephcsible.oreberries.BlockOreberryBush;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.Optional;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import vazkii.botania.api.item.IHornHarvestable;

@Optional.Interface(modid = ModIds.ConstIds.botania, iface = "vazkii.botania.api.item.IHornHarvestable")
@Mixin(value = BlockOreberryBush.class, remap = false)
public class BlockOreberryBushHornHarvestableMixin extends Block implements IHornHarvestable {
    @Shadow @Final public static PropertyInteger AGE;

    public BlockOreberryBushHornHarvestableMixin(Material materialIn) {
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
    public void harvestByHorn(World world, BlockPos pos, ItemStack itemStack, EnumHornType enumHornType) {
        IBlockState state = world.getBlockState(pos);
        if(state.getBlock() instanceof BlockOreberryBush && enumHornType == EnumHornType.WILD) {
            if(state.getValue(AGE) >= 3) {
                world.setBlockState(pos, state.withProperty(BlockOreberryBush.AGE, 2));
                world.playEvent(Constants.WorldEvents.BREAK_BLOCK_EFFECTS, pos, Block.getStateId(state));
                ItemStack drop = ((BlockOreberryBush) state.getBlock()).getBerriesStack(world.rand);
                spawnAsEntity(world, pos, drop);
            }
        }
    }
}
