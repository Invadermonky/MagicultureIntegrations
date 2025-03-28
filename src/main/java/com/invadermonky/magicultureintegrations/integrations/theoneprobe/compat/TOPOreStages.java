package com.invadermonky.magicultureintegrations.integrations.theoneprobe.compat;

import com.invadermonky.magicultureintegrations.api.IConfigurable;
import com.invadermonky.magicultureintegrations.config.MIConfigIntegrations;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.ITheOneProbe;
import mcjty.theoneprobe.apiimpl.providers.DefaultProbeInfoProvider;
import mcjty.theoneprobe.config.Config;
import net.darkhax.gamestages.GameStageHelper;
import net.darkhax.orestages.api.OreTiersAPI;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public class TOPOreStages implements Function<ITheOneProbe, Void>, IConfigurable {
    @Override
    public Void apply(ITheOneProbe input) {
        //TODO: Harvest level still does not display correctly.

        input.registerBlockDisplayOverride((mode, probeInfo, player, world, state, data) -> {
            IBlockState checkState = state;
            Tuple<String, IBlockState> stageInfo = OreTiersAPI.getStageInfo(checkState);
            while(stageInfo != null) {
                if(GameStageHelper.hasStage(player, stageInfo.getFirst())) {
                    break;
                } else {
                    checkState = stageInfo.getSecond();
                    stageInfo = OreTiersAPI.getStageInfo(checkState);
                }
            }
            DefaultProbeInfoProvider.showStandardBlockInfo(
                    Config.getRealConfig(),
                    mode,
                    probeInfo,
                    checkState,
                    checkState.getBlock(),
                    world,
                    data.getPos(),
                    player,
                    new ProbeHitDataWrapper(data, checkState, world, player)
            );
            return true;
        });
        return null;
    }

    @Override
    public boolean isEnabled() {
        return MIConfigIntegrations.the_one_probe.ore_stages;
    }

    private static class ProbeHitDataWrapper implements IProbeHitData {
        private final IProbeHitData data;
        private final ItemStack pickBlock;

        public ProbeHitDataWrapper(IProbeHitData data, IBlockState state, World world, EntityPlayer player) {
            this.data = data;
            pickBlock = state.getBlock().getPickBlock(state, new RayTraceResult(data.getHitVec(), data.getSideHit(), data.getPos()), world, data.getPos(), player);
        }

        @Override
        public BlockPos getPos() {
            return data.getPos();
        }

        @Override
        public Vec3d getHitVec() {
            return data.getHitVec();
        }

        @Override
        public EnumFacing getSideHit() {
            return data.getSideHit();
        }

        @Nullable
        @Override
        public ItemStack getPickBlock() {
            return pickBlock;
        }
    }
}
