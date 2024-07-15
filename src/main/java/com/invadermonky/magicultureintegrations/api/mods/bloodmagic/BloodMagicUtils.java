package com.invadermonky.magicultureintegrations.api.mods.bloodmagic;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class BloodMagicUtils {
    @Nullable
    public static EntityPlayer getSacrificeTarget(World world, BlockPos pos, double radius) {
        AxisAlignedBB bb = (new AxisAlignedBB(pos)).grow(radius);
        List<EntityPlayer> playerList = world.getEntitiesWithinAABB(EntityPlayer.class, bb);
        for(EntityPlayer player : playerList) {
            if(!player.isDead) {
                return player;
            }
        }
        return null;
    }
}
