package com.invadermonky.magicultureintegrations.integrations.botania.item.lens;

import com.invadermonky.magicultureintegrations.config.MIConfigAdditions;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import thaumcraft.api.damagesource.DamageSourceThaumcraft;
import thaumcraft.api.potions.PotionFluxTaint;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.fx.PacketFXPollute;
import thaumcraft.common.world.aura.AuraHandler;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.mana.IManaReceiver;
import vazkii.botania.common.item.lens.Lens;

import java.util.List;

public class LensFlux extends Lens {
    @Override
    public boolean collideBurst(IManaBurst burst, EntityThrowable entity, RayTraceResult rayTrace, boolean isManaBlock, boolean dead, ItemStack stack) {
        if (!entity.world.isRemote && !burst.isFake()) {
            BlockPos sourcePos = burst.getBurstSourceBlockPos();

            if (rayTrace.entityHit == null && !isManaBlock && (rayTrace.getBlockPos() == null || !sourcePos.equals(rayTrace.getBlockPos()))) {
                createFluxExplosion(entity, (float) burst.getMana() / 50.0f, false);
            }
        } else {
            dead = false;
        }

        return dead;
    }

    @Override
    public void updateBurst(IManaBurst burst, EntityThrowable entity, ItemStack stack) {
        if (entity.world.isRemote)
            return;

        AxisAlignedBB axis = (new AxisAlignedBB(entity.posX, entity.posY, entity.posZ, entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ)).grow(1.0);
        List<EntityLivingBase> entities = entity.world.getEntitiesWithinAABB(EntityLivingBase.class, axis);

        for (EntityLivingBase living : entities) {
            if (living instanceof EntityPlayer)
                continue;

            if (living.hurtTime == 0) {
                int mana = burst.getMana();
                int toDrain = Math.min(48, mana);
                burst.setMana(mana - 16);
                if (!burst.isFake()) {
                    float damage = MIConfigAdditions.botania.auromeria.fluxBurstDamage * toDrain / 48;
                    living.attackEntityFrom(DamageSourceThaumcraft.taint, damage);
                    living.addPotionEffect(new PotionEffect(PotionFluxTaint.instance, 160));
                    if (burst.getMana() <= 0) {
                        entity.setDead();
                    }
                }
                break;
            }
        }
    }

    @Override
    public int getManaToTransfer(IManaBurst burst, EntityThrowable entity, ItemStack stack, IManaReceiver receiver) {
        return super.getManaToTransfer(burst, entity, stack, receiver);
    }

    public void createFluxExplosion(EntityThrowable entity, float explosionStrength, boolean damageTerrain) {
        float flux = (float) (entity.world.rand.nextFloat() < MIConfigAdditions.botania.auromeria.pollutionChance ? MIConfigAdditions.botania.auromeria.pollutionAmount : 0);
        entity.world.createExplosion(entity, entity.posX, entity.posY, entity.posZ, explosionStrength, damageTerrain);
        if (flux > 0) {
            float fluxDisplay = MathHelper.clamp(flux * 10.0f, 0, Byte.MAX_VALUE);
            BlockPos pos = entity.getPosition();
            AuraHandler.addFlux(entity.world, pos, flux);
            PacketHandler.INSTANCE.sendToAllAround(new PacketFXPollute(pos, fluxDisplay), new NetworkRegistry.TargetPoint(entity.world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 32.0));
        }
    }
}
