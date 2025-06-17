package com.invadermonky.magicultureintegrations.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;

public class EntityHelper {
    public static RayTraceResult getPlayerRayTrace(EntityPlayer player) {
        Vec3d eyes = player.getPositionEyes(0);
        Vec3d look = player.getLook(0);
        double reachDistance = player.getAttributeMap().getAttributeInstance(EntityPlayer.REACH_DISTANCE).getAttributeValue();
        Vec3d reach = eyes.add(look.x * reachDistance, look.y * reachDistance, look.z * reachDistance);
        return player.world.rayTraceBlocks(eyes, reach, false, false, false);
    }
}
