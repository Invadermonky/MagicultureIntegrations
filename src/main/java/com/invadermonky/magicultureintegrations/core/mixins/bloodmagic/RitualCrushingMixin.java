package com.invadermonky.magicultureintegrations.core.mixins.bloodmagic;

import WayofTime.bloodmagic.ritual.IMasterRitualStone;
import WayofTime.bloodmagic.ritual.types.RitualCrushing;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.ForgeEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(value = RitualCrushing.class, remap = false)
public abstract class RitualCrushingMixin {
    @Shadow protected abstract FakePlayer getFakePlayer(WorldServer world);

    /**
     * @author Invadermonky
     * @reason Adding harvest event support to Blood Magic's Ritual of Crushing.<br>
     *
     * <p>Blood Magic's Ritual of the Crusher pulled block drops from a depreciated method and performed all its actions
     * without firing the {@link net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent}, resulting in it bypassing
     * many drop tweakers such as Dropt.</p>
     *
     * @return List of drops that has been modified through the {@link net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent}.
     */
    @Redirect(
            method = "performRitual",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/Block;getDrops(Lnet/minecraft/world/IBlockAccess;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/state/IBlockState;I)Ljava/util/List;"
            )
    )
    private List<ItemStack> performRitualMixin(Block block, IBlockAccess blockAccess, BlockPos pos, IBlockState state, int fortune, @Local(name = "masterRitualStone") IMasterRitualStone masterRitualStone, @Local(name = "isSilkTouch") boolean isSilkTouch) {
        World world = masterRitualStone.getWorldObj();
        NonNullList<ItemStack> drops = NonNullList.create();
        block.getDrops(drops, world, pos, state, fortune);
        if(!ConfigHandlerMI.integrations.blood_magic.crusherRitualDropEventFix) {
            return drops;
        }
        EntityPlayer player = masterRitualStone.getOwnerNetwork().getPlayer();
        float chance = ForgeEventFactory.fireBlockHarvesting(drops, world, pos, state, fortune, 1.0f, isSilkTouch, player != null ? player : this.getFakePlayer((WorldServer) world));
        if(world.rand.nextFloat() <= chance) {
            return drops;
        } else {
            return NonNullList.create();
        }
    }
}
