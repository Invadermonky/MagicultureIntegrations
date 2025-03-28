package com.invadermonky.magicultureintegrations.integrations.enderio.mods;

import com.enderio.core.common.util.NNList;
import com.invadermonky.magicultureintegrations.MagicultureIntegrations;
import com.invadermonky.magicultureintegrations.api.block.IHarvestableCrop;
import crazypants.enderio.api.farm.*;
import crazypants.enderio.base.farming.FarmingTool;
import crazypants.enderio.base.farming.farmers.HarvestResult;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EIOHarvestableCrop extends AbstractFarmerJoe {
    public EIOHarvestableCrop() {
        this.setRegistryName(new ResourceLocation(MagicultureIntegrations.MOD_ID, "harvestable_crops"));
        this.setPriority(EventPriority.HIGH);
    }

    @Override
    public Result tryPrepareBlock(@NotNull IFarmer farm, @NotNull BlockPos pos, @NotNull IBlockState state) {
        return Result.NEXT;
    }

    @Override
    public boolean canPlant(@NotNull ItemStack stack) {
        return false;
    }

    @Override
    public boolean canHarvest(@NotNull IFarmer farmer, @NotNull BlockPos pos, @NotNull IBlockState state) {
        if(state.getBlock() instanceof IHarvestableCrop) {
            IHarvestableCrop.HarvestResult harvestResult = ((IHarvestableCrop) state.getBlock()).getHarvestResult(farmer.getWorld(), pos);
            return harvestResult == IHarvestableCrop.HarvestResult.HARVEST || harvestResult == IHarvestableCrop.HarvestResult.CLAIM;
        }
        return false;
    }

    @Override
    public @Nullable IHarvestResult harvestBlock(@NotNull IFarmer farm, @NotNull BlockPos pos, @NotNull IBlockState state) {
        World world = farm.getWorld();
        if(state.getBlock() instanceof IHarvestableCrop) {
            IHarvestableCrop harvestable = (IHarvestableCrop) state.getBlock();
            if(harvestable.getHarvestResult(world, pos) == IHarvestableCrop.HarvestResult.HARVEST && farm.checkAction(FarmingAction.HARVEST, FarmingTool.HOE)) {
                if(!farm.hasTool(FarmingTool.HOE)) {
                    farm.setNotification(FarmNotification.NO_HOE);
                    return null;
                } else {
                    EntityPlayerMP joe = farm.startUsingItem(FarmingTool.HOE);
                    boolean silkTouch = EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, farm.getTool(FarmingTool.HOE)) > 0;
                    int fortune = farm.getLootingValue(FarmingTool.HOE);
                    BlockPos harvestPos = harvestable.getHarvestPosition(world, pos);
                    IBlockState harvestState = world.getBlockState(harvestPos);
                    final IHarvestResult result = new HarvestResult(harvestPos);

                    NonNullList<ItemStack> drops = harvestable.harvestCrop(joe, world, pos, silkTouch, fortune);
                    float chance = ForgeEventFactory.fireBlockHarvesting(drops, world, harvestPos, harvestState, fortune, 1.0F, silkTouch, farm.getFakePlayer());
                    drops.removeIf(stack -> chance < world.rand.nextFloat());
                    for(ItemStack drop : drops) {
                        result.addDrop(harvestPos, drop.copy());
                    }

                    farm.registerAction(FarmingAction.HARVEST, FarmingTool.HOE, harvestState, harvestPos);

                    NNList.wrap(farm.endUsingItem(FarmingTool.HOE)).apply(drop -> {
                        result.addDrop(harvestPos, drop.copy());
                    });

                    return result;
                }
            }
        }
        return null;
    }
}
