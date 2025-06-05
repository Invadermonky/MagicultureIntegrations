package com.invadermonky.magicultureintegrations.core.mixins.bloodmagic.rituals;

import WayofTime.bloodmagic.ritual.IMasterRitualStone;
import WayofTime.bloodmagic.ritual.types.RitualCrushing;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.ForgeEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;

@SuppressWarnings("unchecked")
@Mixin(value = RitualCrushing.class, remap = false)
public abstract class RitualCrushingMixin {
    @Shadow
    protected abstract FakePlayer getFakePlayer(WorldServer world);

    /**
     * @return List of drops that has been modified through the {@link net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent}.
     * @author Invadermonky
     * @reason Adding harvest event support to Blood Magic's Ritual of Crushing.<br>
     *
     * <p>Blood Magic's Ritual of the Crusher pulled block drops from a depreciated method and performed all its actions
     * without firing the {@link net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent}, resulting in it bypassing
     * many drop tweakers such as Dropt.</p>
     */
    @ModifyExpressionValue(
            method = "performRitual",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/Block;getDrops(Lnet/minecraft/world/IBlockAccess;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/state/IBlockState;I)Ljava/util/List;"
            )
    )
    private List<ItemStack> performRitualMixin(List original,
                                               @Local(name = "masterRitualStone") IMasterRitualStone masterRitualStone,
                                               @Local(name = "newPos") BlockPos pos,
                                               @Local(name = "state") IBlockState state,
                                               @Local(name = "isSilkTouch") boolean isSilkTouch,
                                               @Local(name = "fortune") int fortune
    ) {
        World world = masterRitualStone.getWorldObj();
        EntityPlayer player = masterRitualStone.getOwnerNetwork().getPlayer();
        float chance = ForgeEventFactory.fireBlockHarvesting(original, world, pos, state, fortune, 1.0f, isSilkTouch, player != null ? player : this.getFakePlayer((WorldServer) world));
        original.removeIf(drop -> ((ItemStack) drop).isEmpty() || chance < world.rand.nextFloat());
        return original;
    }
}
