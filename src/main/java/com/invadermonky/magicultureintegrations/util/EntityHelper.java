package com.invadermonky.magicultureintegrations.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

public class EntityHelper {
    /**
     * A copy of {@link net.minecraft.entity.Entity#rayTrace(double, float)} used for EntityPlayers to avoid an occasional
     * concurrent modification exception that occurred in some modpacks.
     *
     * @param player The player to get the look raytrace from
     * @return A ray trace result based on the player's look vector and reach distance
     */
    public static @Nullable RayTraceResult getPlayerRayTrace(EntityPlayer player) {
        Vec3d eyes = player.getPositionEyes(0);
        Vec3d look = player.getLook(0);
        double reachDistance = player.getAttributeMap().getAttributeInstance(EntityPlayer.REACH_DISTANCE).getAttributeValue();
        Vec3d reach = eyes.add(look.x * reachDistance, look.y * reachDistance, look.z * reachDistance);
        return player.world.rayTraceBlocks(eyes, reach, false, false, false);
    }
}
