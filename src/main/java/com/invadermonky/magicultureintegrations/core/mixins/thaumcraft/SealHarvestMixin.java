package com.invadermonky.magicultureintegrations.core.mixins.thaumcraft;

import com.invadermonky.magicultureintegrations.api.block.IHarvestableCrop;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.ForgeEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import thaumcraft.api.golems.IGolemAPI;
import thaumcraft.api.golems.seals.ISealConfigToggles;
import thaumcraft.api.golems.tasks.Task;
import thaumcraft.common.golems.seals.SealHarvest;

@Mixin(value = SealHarvest.class, remap = false)
public abstract class SealHarvestMixin {
    @Shadow
    public abstract ISealConfigToggles.SealToggle[] getToggles();

    @Inject(
            method = "onTaskCompletion",
            at = @At(
                    value = "INVOKE",
                    target = "Lthaumcraft/common/lib/utils/BlockUtils;harvestBlock(Lnet/minecraft/world/World;Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/util/math/BlockPos;ZZIZ)Z"
            ),
            cancellable = true
    )
    private void onTaskCompletionMixin(World world, IGolemAPI golem, Task task, CallbackInfoReturnable<Boolean> cir, @Local FakePlayer player) {
        BlockPos pos = task.getPos();
        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() instanceof IHarvestableCrop) {
            IHarvestableCrop harvestable = (IHarvestableCrop) state.getBlock();
            if (harvestable.getHarvestResult(world, pos) == IHarvestableCrop.HarvestResult.HARVEST) {
                BlockPos harvestPos = harvestable.getHarvestPosition(world, pos);
                IBlockState harvestState = world.getBlockState(harvestPos);
                world.playEvent(Constants.WorldEvents.BREAK_BLOCK_EFFECTS, harvestPos, Block.getStateId(harvestState));
                NonNullList<ItemStack> drops = harvestable.harvestCrop(player, world, pos, false, 0);
                float chance = ForgeEventFactory.fireBlockHarvesting(drops, world, harvestPos, harvestState, 0, 1.0f, false, player);
                drops.removeIf(drop -> chance < world.rand.nextFloat());

                if (!world.isRemote) {
                    EnumFacing dropFacing = null;
                    if (!world.isAirBlock(harvestPos)) {
                        for (EnumFacing facing : EnumFacing.VALUES) {
                            if (world.isAirBlock(harvestPos.offset(facing))) {
                                dropFacing = facing;
                                break;
                            }
                        }
                    }
                    BlockPos dropPos = dropFacing != null ? harvestPos.offset(dropFacing) : harvestPos;
                    drops.stream().filter(drop -> chance >= world.rand.nextFloat() && !drop.isEmpty()).forEach(drop -> {
                        EntityItem entityItem = new EntityItem(world, dropPos.getX() + 0.5, dropPos.getY() + 0.2, dropPos.getZ() + 0.5, drop);
                        world.spawnEntity(entityItem);
                    });
                }

                golem.addRankXp(1);
                golem.swingArm();
                //IHarvestableCrop crops don't replant so the replant handler isn't needed.
            }
            //IHarvestableCrops need to be handled by this handler.
            task.setSuspended(true);
            cir.setReturnValue(true);
        }
    }
}
