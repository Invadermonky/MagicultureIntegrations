package com.invadermonky.magicultureintegrations.core.mixins.agricraft;

import com.infinityraider.agricraft.api.v1.crop.IAgriCrop;
import com.infinityraider.agricraft.blocks.BlockCrop;
import com.infinityraider.agricraft.tiles.TileEntityCrop;
import com.infinityraider.infinitylib.utility.WorldHelper;
import com.invadermonky.magicultureintegrations.api.block.IHarvestableCrop;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Optional;

@Mixin(value = BlockCrop.class, remap = false)
public class BlockCropHarvestableMixin implements IHarvestableCrop {
    @Override
    public BlockPos getHarvestPosition(World world, BlockPos cropPos) {
        return cropPos;
    }

    @Override
    public @NotNull HarvestResult getHarvestResult(World world, BlockPos pos) {
        Optional<TileEntityCrop> optional = WorldHelper.getTile(world, pos, TileEntityCrop.class);
        if(optional.isPresent()) {
            return optional.filter(IAgriCrop::canBeHarvested).isPresent() ? HarvestResult.HARVEST : HarvestResult.CLAIM;
        }
        return HarvestResult.PASS;
    }

    @Override
    public @NotNull NonNullList<ItemStack> harvestCrop(@Nullable EntityPlayer player, World world, BlockPos pos, boolean silkTouch, int fortune) {
        NonNullList<ItemStack> drops = NonNullList.create();
        WorldHelper.getTile(world, pos, TileEntityCrop.class).ifPresent(crop -> crop.onHarvest(drops::add, player));
        return drops;
    }
}
