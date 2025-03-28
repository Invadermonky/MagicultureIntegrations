package com.invadermonky.magicultureintegrations.integrations.theoneprobe.compat;

import com.invadermonky.magicultureintegrations.MagicultureIntegrations;
import com.invadermonky.magicultureintegrations.api.IConfigurable;
import com.invadermonky.magicultureintegrations.config.MIConfigIntegrations;
import com.invadermonky.magicultureintegrations.util.ModIds;
import mcjty.theoneprobe.Tools;
import mcjty.theoneprobe.api.*;
import mcjty.theoneprobe.config.Config;
import net.fybertech.redstonepaste.BlockRedstonePaste;
import net.fybertech.redstonepaste.RedstonePasteMod;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.function.Function;

public class TOPRedstonePaste implements Function<ITheOneProbe,Void>, IConfigurable {
    @Override
    public Void apply(ITheOneProbe input) {
        input.registerProvider(new IProbeInfoProvider() {
            @Override
            public String getID() {
                return MagicultureIntegrations.MOD_ID + ":" + ModIds.redstone_paste.modId;
            }

            @Override
            public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, IBlockState state, IProbeHitData data) {
                if(Tools.show(mode, Config.getRealConfig().getShowRedstone())) {
                    if (state.getBlock() instanceof BlockRedstonePaste) {
                        int redstonePower = state.getBlock().shouldCheckWeakPower(state, world, data.getPos(), data.getSideHit()) ? state.getStrongPower(world, data.getPos(), data.getSideHit()) : state.getWeakPower(world, data.getPos(), data.getSideHit());
                        if (redstonePower > 0) {
                            probeInfo.horizontal().item(new ItemStack(RedstonePasteMod.instance.itemRedstonePaste), probeInfo.defaultItemStyle().width(14).height(14)).text(TextStyleClass.LABEL + "Power: " + TextStyleClass.INFO + redstonePower);
                        }
                    }
                }
            }
        });
        return null;
    }

    @Override
    public boolean isEnabled() {
        return MIConfigIntegrations.the_one_probe.redstone_paste;
    }
}
