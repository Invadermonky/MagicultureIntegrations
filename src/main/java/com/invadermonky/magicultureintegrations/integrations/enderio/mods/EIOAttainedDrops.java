package com.invadermonky.magicultureintegrations.integrations.enderio.mods;

import com.enderio.core.common.util.NNList;
import com.invadermonky.magicultureintegrations.MagicultureIntegrations;
import com.invadermonky.magicultureintegrations.util.ModIds;
import crazypants.enderio.api.farm.*;
import crazypants.enderio.base.farming.FarmingTool;
import crazypants.enderio.base.farming.farmers.HarvestResult;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import shadows.attained.blocks.BlockBulb;
import shadows.attained.blocks.BlockPlant;
import shadows.attained.blocks.BlockVitalized;
import shadows.attained.init.ModRegistry;
import shadows.attained.items.ItemSeed;

public class EIOAttainedDrops extends AbstractFarmerJoe {

    public EIOAttainedDrops() {
        this.setRegistryName(new ResourceLocation(MagicultureIntegrations.MOD_ID, ModIds.attained_drops.modId));
        this.setPriority(EventPriority.LOW);
    }

    @Override
    public Result tryPrepareBlock(@NotNull IFarmer farm, @NotNull BlockPos pos, @NotNull IBlockState state) {
        IBlockState downState = farm.getWorld().getBlockState(pos.down());
        if(downState.getBlock() instanceof BlockVitalized) {
            ItemStack seedStack = farm.getSeedTypeInSuppliesFor(pos);
            if(seedStack.getItem() instanceof ItemSeed) {
                farm.getWorld().setBlockState(pos, ModRegistry.PLANT.getDefaultState());
                seedStack.shrink(1);
                farm.getWorld().playSound(farm.getFakePlayer(), pos, SoundEvents.BLOCK_GRASS_BREAK, SoundCategory.BLOCKS, 0.6F, 1.0F);
                return Result.ACTION;
            }
            return Result.CLAIM;
        }
        return Result.NEXT;
    }

    @Override
    public boolean canPlant(@NotNull ItemStack itemStack) {
        return !itemStack.isEmpty() && itemStack.getItem() == ModRegistry.SEED;
    }

    @Override
    public boolean canHarvest(@NotNull IFarmer iFarmer, @NotNull BlockPos blockPos, @NotNull IBlockState state) {
        return state.getBlock() instanceof BlockPlant;
    }

    @Override
    public @Nullable IHarvestResult harvestBlock(@NotNull IFarmer farm, @NotNull BlockPos pos, @NotNull IBlockState state) {
        World world = farm.getWorld();
        BlockPos harvestPos = pos.up();
        IBlockState harvestState = world.getBlockState(harvestPos);
        boolean harvestFlag = this.canHarvest(farm, pos, state)
                && state.getValue(BlockPlant.AGE) == ((BlockPlant) ModRegistry.PLANT).getMaxAge()
                && harvestState.getBlock() instanceof BlockBulb;

        if(harvestFlag && farm.checkAction(FarmingAction.HARVEST, FarmingTool.HOE)) {
            if(!farm.hasTool(FarmingTool.HOE)) {
                farm.setNotification(FarmNotification.NO_HOE);
                return null;
            } else {
                EntityPlayerMP joe = farm.startUsingItem(FarmingTool.HOE);
                int fortune = farm.getLootingValue(FarmingTool.HOE);
                final IHarvestResult result = new HarvestResult();

                NonNullList<ItemStack> drops = NonNullList.create();
                harvestState.getBlock().getDrops(drops, world, harvestPos, harvestState, fortune);
                float chance = ForgeEventFactory.fireBlockHarvesting(drops, world, harvestPos, harvestState, fortune, 1.0F, false, joe);

                for(ItemStack drop : drops) {
                    if(!drop.isEmpty() && world.rand.nextFloat() <= chance) {
                        result.addDrop(pos, drop.copy());
                    }
                }

                farm.registerAction(FarmingAction.HARVEST, FarmingTool.HOE, harvestState, harvestPos);

                NNList.wrap(farm.endUsingItem(FarmingTool.HOE)).apply(drop -> {
                    result.addDrop(pos, drop.copy());
                });

                world.setBlockToAir(harvestPos);
                return result;
            }
        }
        return null;
    }
}
