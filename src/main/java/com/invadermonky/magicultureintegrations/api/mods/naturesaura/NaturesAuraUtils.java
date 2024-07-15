package com.invadermonky.magicultureintegrations.api.mods.naturesaura;

import de.ellpeck.naturesaura.api.aura.type.IAuraType;
import de.ellpeck.naturesaura.packet.PacketHandler;
import de.ellpeck.naturesaura.packet.PacketParticleStream;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class NaturesAuraUtils {
    public static void spawnHeaterParticles(World world, BlockPos heaterPos, BlockPos facingPos) {
        if(world.getTotalWorldTime() % 15L == 0) {
            PacketHandler.sendToAllAround(world, heaterPos, 32, new PacketParticleStream(
                    (float)heaterPos.getX() + (float)world.rand.nextGaussian() * 5.0F,
                    (float)(heaterPos.getY() + 1) + world.rand.nextFloat() * 5.0F,
                    (float)heaterPos.getZ() + (float)world.rand.nextGaussian() * 5.0F,
                    (float)facingPos.getX() + world.rand.nextFloat(),
                    (float)facingPos.getY() + world.rand.nextFloat(),
                    (float)facingPos.getZ() + world.rand.nextFloat(),
                    world.rand.nextFloat() * 0.07F + 0.07F,
                    IAuraType.forWorld(world).getColor(),
                    world.rand.nextFloat() + 0.5F)
            );
        }
    }
}
