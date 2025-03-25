package com.invadermonky.magicultureintegrations.integrations.enderio.mods;

import com.enderio.core.common.util.NNList;
import com.infinityraider.agricraft.api.v1.crop.IAgriCrop;
import com.infinityraider.agricraft.tiles.TileEntityCrop;
import com.infinityraider.infinitylib.utility.WorldHelper;
import com.invadermonky.magicultureintegrations.MagicultureIntegrations;
import com.invadermonky.magicultureintegrations.api.IProxy;
import crazypants.enderio.api.farm.*;
import crazypants.enderio.base.farming.FarmingTool;
import crazypants.enderio.base.farming.farmers.HarvestResult;
import crazypants.enderio.util.Prep;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EIOAgricraft extends AbstractFarmerJoe implements IProxy {
    public EIOAgricraft() {
        this.setPriority(EventPriority.HIGH);
        this.setRegistryName(new ResourceLocation(MagicultureIntegrations.MOD_ID, "agricraft"));
    }

    @Override
    public Result tryPrepareBlock(@NotNull IFarmer farm, @NotNull BlockPos pos, @NotNull IBlockState state) {
        return IFarmerJoe.Result.NEXT;
    }

    @Override
    public boolean canPlant(@NotNull ItemStack itemStack) {
        return false;
    }

    @Override
    public boolean canHarvest(@NotNull IFarmer iFarmer, @NotNull BlockPos blockPos, @NotNull IBlockState iBlockState) {
        return WorldHelper.getTile(iFarmer.getWorld(), blockPos, TileEntityCrop.class).filter(IAgriCrop::canBeHarvested).isPresent();
    }

    @Override
    public @Nullable IHarvestResult harvestBlock(@NotNull IFarmer farm, @NotNull BlockPos pos, @NotNull IBlockState state) {
        if(this.canHarvest(farm, pos, state) && farm.checkAction(FarmingAction.HARVEST, FarmingTool.HOE)) {
            if(!farm.hasTool(FarmingTool.HOE)) {
                farm.setNotification(FarmNotification.NO_HOE);
                return null;
            } else {
                World world = farm.getWorld();
                final IHarvestResult result = new HarvestResult(pos);

                WorldHelper.getTile(world, pos, TileEntityCrop.class).ifPresent(crop ->
                        crop.onHarvest(product -> {
                            if(Prep.isValid(product)) {
                                result.addDrop(pos, product);
                            }
                        }, farm.getFakePlayer()));

                NNList.wrap(farm.endUsingItem(FarmingTool.HOE)).apply(drop -> {
                    result.addDrop(pos, drop.copy());
                });

                return result;
            }
        }
        return null;
    }
}
