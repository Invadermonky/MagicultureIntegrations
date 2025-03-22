package com.invadermonky.magicultureintegrations.integrations.bloodmagic.mods;

import WayofTime.bloodmagic.ritual.harvest.HarvestRegistry;
import WayofTime.bloodmagic.ritual.harvest.IHarvestHandler;
import com.infinityraider.agricraft.tiles.TileEntityCrop;
import com.infinityraider.infinitylib.utility.WorldHelper;
import com.invadermonky.magicultureintegrations.api.IProxy;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class BMAgricraft implements IProxy, IHarvestHandler {
    @Override
    public void init() {
        if(ConfigHandlerMI.integrations.blood_magic.harvest_ritual.agricraft) {
            HarvestRegistry.registerHandler(this);
        }
    }

    @Override
    public boolean harvest(World world, BlockPos pos, IBlockState state, List<ItemStack> list) {
        WorldHelper.getTile(world, pos, TileEntityCrop.class).ifPresent(crop -> {
            crop.onHarvest((product) -> {
                WorldHelper.spawnItemInWorld(world, pos, product);
            }, null);
        });
        return true;
    }

    @Override
    public boolean test(World world, BlockPos pos, IBlockState iBlockState) {
        return WorldHelper.getTile(world, pos, TileEntityCrop.class).filter(TileEntityCrop::isMature).isPresent();
    }
}
