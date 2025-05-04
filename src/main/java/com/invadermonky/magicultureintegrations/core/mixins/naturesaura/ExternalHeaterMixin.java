package com.invadermonky.magicultureintegrations.core.mixins.naturesaura;

import com.invadermonky.magicultureintegrations.api.tile.HeatableUtils;
import com.invadermonky.magicultureintegrations.api.tile.IBoostableTile;
import com.invadermonky.magicultureintegrations.api.tile.IHeatableTile;
import de.ellpeck.naturesaura.api.aura.chunk.IAuraChunk;
import de.ellpeck.naturesaura.api.aura.type.IAuraType;
import de.ellpeck.naturesaura.blocks.BlockFurnaceHeater;
import de.ellpeck.naturesaura.blocks.tiles.TileEntityFurnaceHeater;
import de.ellpeck.naturesaura.blocks.tiles.TileEntityImpl;
import de.ellpeck.naturesaura.packet.PacketHandler;
import de.ellpeck.naturesaura.packet.PacketParticleStream;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = TileEntityFurnaceHeater.class, remap = false)
public abstract class ExternalHeaterMixin extends TileEntityImpl implements ITickable {
    @Shadow
    public boolean isActive;

    @Inject(method = "update", at = @At("TAIL"), remap = true)
    private void updateMixin(CallbackInfo ci) {
        if (this.world.isRemote || world.getTotalWorldTime() % 5L != 0)
            return;

        boolean did = false;
        BlockPos heaterPos = this.getPos();
        EnumFacing heaterFacing = this.world.getBlockState(heaterPos).getValue(BlockFurnaceHeater.FACING);
        BlockPos facingPos = heaterPos.offset(heaterFacing.getOpposite());
        TileEntity facingTile = this.world.getTileEntity(facingPos);

        if (facingTile instanceof IHeatableTile && !HeatableUtils.isHeatableBlacklisted(TileEntityFurnaceHeater.class, facingTile)) {
            IHeatableTile heatable = (IHeatableTile) facingTile;
            if (heatable.canSmeltHeatable()) {
                int time = heatable.getBurnTimeHeatable();
                heatable.boostBurnTimeHeatable(Math.max(0, 200 - time));
                heatable.boostCookTimeHeatable(5);
                heatable.updateTileHeatable();
                BlockPos spot = IAuraChunk.getHighestSpot(this.world, heaterPos, 20, heaterPos);
                IAuraChunk chunk = IAuraChunk.getAuraChunk(this.world, spot);
                chunk.drainAura(spot, MathHelper.ceil((float) (200 - time) * 16.6f));
                did = true;

            }
        } else if (facingTile instanceof IBoostableTile && !HeatableUtils.isHeatableBlacklisted(TileEntityFurnaceHeater.class, facingTile)) {
            IBoostableTile boostable = ((IBoostableTile) facingTile).getTrueBoostable();
            if (boostable.canSmeltBoostable()) {
                boostable.boostCookTimeBoostable(5);
                boostable.updateTileBoostable();
                BlockPos spot = IAuraChunk.getHighestSpot(this.world, heaterPos, 20, heaterPos);
                IAuraChunk chunk = IAuraChunk.getAuraChunk(this.world, spot);
                chunk.drainAura(spot, MathHelper.ceil(5.0 * 16.6f));
                did = true;
            }
        }

        if (did && world.getTotalWorldTime() % 15L == 0) {
            PacketHandler.sendToAllAround(world, heaterPos, 32, new PacketParticleStream(
                    (float) heaterPos.getX() + (float) world.rand.nextGaussian() * 5.0F,
                    (float) (heaterPos.getY() + 1) + world.rand.nextFloat() * 5.0F,
                    (float) heaterPos.getZ() + (float) world.rand.nextGaussian() * 5.0F,
                    (float) facingPos.getX() + world.rand.nextFloat(),
                    (float) facingPos.getY() + world.rand.nextFloat(),
                    (float) facingPos.getZ() + world.rand.nextFloat(),
                    world.rand.nextFloat() * 0.07F + 0.07F,
                    IAuraType.forWorld(world).getColor(),
                    world.rand.nextFloat() + 0.5F)
            );
        }

        if (did != this.isActive) {
            this.isActive = did;
            this.sendToClients();
        }
    }
}
