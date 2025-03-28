package com.invadermonky.magicultureintegrations.core.mixins.thaumcraft;

import com.invadermonky.magicultureintegrations.api.block.IHarvestableCrop;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import thaumcraft.common.lib.utils.CropUtils;

@Mixin(value = CropUtils.class, remap = false)
public class CropUtilsMixin {

    /**
     * @author Invadermonky
     * @reason Adding support for special crop handlers to Thaumcraft Golems
     *
     * <p>Thaumcraft Golem crop harvest implementation does not allow for the handling of complex interactions such as
     * those found in Agricraft or Attained Drops.</p><br>
     *
     * <p>This is part one of a two part mixin, the other found in {@link SealHarvestMixin}. This mixin handles the
     * 'should be harvested', while the other mixin actually handles the block harvest.</p>
     *
     * @param world The world object
     * @param pos The crop harvest position
     * @param cir Boolean callback returnable, set to true if crop can be harvested, false if crop is not ready to be harvested
     *            and ignored if the block is not a custom harvestable.
     */
    @Inject(
            method = "isGrownCrop",
            at = @At(value = "TAIL"),
            cancellable = true
    )
    private static void isGrownCropMixin(World world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        IBlockState state = world.getBlockState(pos);
        if(state.getBlock() instanceof IHarvestableCrop) {
            IHarvestableCrop harvestable = (IHarvestableCrop) state.getBlock();
            switch (harvestable.getHarvestResult(world, pos)) {
                case HARVEST:
                    cir.setReturnValue(true);
                    return;
                case CLAIM:
                    cir.setReturnValue(false);
                    return;
                case PASS:
            }
        }
    }
}
