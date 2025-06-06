package com.invadermonky.magicultureintegrations.core.mixins.bloodmagic.rituals;

import WayofTime.bloodmagic.ritual.IMasterRitualStone;
import WayofTime.bloodmagic.ritual.types.RitualMagnetic;
import com.invadermonky.magicultureintegrations.util.ModIds;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.darkhax.gamestages.GameStageHelper;
import net.darkhax.orestages.api.OreTiersAPI;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = RitualMagnetic.class, remap = false)
public abstract class RitualMagneticOreStagesMixin {
    @ModifyExpressionValue(
            method = "performRitual",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/Block;getPickBlock(Lnet/minecraft/block/state/IBlockState;Lnet/minecraft/util/math/RayTraceResult;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/player/EntityPlayer;)Lnet/minecraft/item/ItemStack;"
            )
    )
    private ItemStack performRitualMixin(ItemStack original, @Local(ordinal = 2) BlockPos pos, @Local RayTraceResult rayTraceResult, @Local(argsOnly = true) IMasterRitualStone masterRitualStone) {
        World world = masterRitualStone.getWorldObj();
        IBlockState state = world.getBlockState(pos);
        EntityPlayer player = masterRitualStone.getOwnerNetwork().getPlayer();
        if (ModIds.ore_stages.isLoaded && player != null) {
            Tuple<String, IBlockState> check = OreTiersAPI.getStageInfo(state);
            while (check != null) {
                if (GameStageHelper.hasStage(player, check.getFirst())) {
                    break;
                } else {
                    state = check.getSecond();
                    check = OreTiersAPI.getStageInfo(state);
                }
            }
        }

        return player != null ? state.getBlock().getPickBlock(state, rayTraceResult, world, pos, player) : original;
    }
}
