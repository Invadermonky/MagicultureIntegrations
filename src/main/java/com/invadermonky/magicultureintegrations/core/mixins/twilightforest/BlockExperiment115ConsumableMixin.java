package com.invadermonky.magicultureintegrations.core.mixins.twilightforest;

import com.invadermonky.magicultureintegrations.api.block.IKekimurusConsumable;
import com.invadermonky.magicultureintegrations.config.MIConfigIntegrations;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import twilightforest.block.BlockTFExperiment115;

@Mixin(value = BlockTFExperiment115.class, remap = false)
public abstract class BlockExperiment115ConsumableMixin implements IKekimurusConsumable {
    @Override
    public boolean canBeConsumed(World world, BlockPos pos, IBlockState state) {
        int nextSlice = state.getValue(BlockTFExperiment115.NOMS) + 1;
        return nextSlice <= 7 || MIConfigIntegrations.botania.kekimurus.experiment115Consume;
    }

    @Override
    public void consumeBlock(World world, BlockPos pos, IBlockState state) {
        int nextSlice = state.getValue(BlockTFExperiment115.NOMS) + 1;
        if(nextSlice <= 7) {
            world.setBlockState(pos, state.withProperty(BlockTFExperiment115.NOMS, nextSlice));
        } else {
            world.setBlockToAir(pos);
        }
    }
}
