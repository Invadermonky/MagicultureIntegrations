package com.invadermonky.magicultureintegrations.integrations.bloodmagic.events;

import WayofTime.bloodmagic.alchemyArray.AlchemyArrayEffectFurnaceFuel;
import WayofTime.bloodmagic.tile.TileAlchemyArray;
import WayofTime.bloodmagic.util.DamageSourceBloodMagic;
import com.invadermonky.magicultureintegrations.api.events.ITileTickEvent;
import com.invadermonky.magicultureintegrations.api.mods.bloodmagic.BloodMagicUtils;
import com.invadermonky.magicultureintegrations.api.tile.IHeatableTile;
import com.invadermonky.magicultureintegrations.util.LogHelper;
import com.invadermonky.magicultureintegrations.util.ReflectionHelper;
import gnu.trove.map.hash.THashMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BMFurnaceArrayHandler implements ITileTickEvent {
    public static THashMap<Class<? extends TileEntity>, Class<? extends IHeatableTile>> furnaceArrayHeatableMap = new THashMap<>();

    public static void registerFurnaceArrayHeatable(Class<? extends TileEntity> tileClass, Class<? extends IHeatableTile> heatableClass) {
        furnaceArrayHeatableMap.put(tileClass, heatableClass);
    }

    private static boolean isHeatableTile(TileEntity tile) {
        return tile != null && (furnaceArrayHeatableMap.containsKey(tile.getClass()) || furnaceArrayHeatableMap.containsKey(tile.getClass().getSuperclass()));
    }

    @Override
    public void onTileEntityTick(World world, TileEntity tile) {
        TileAlchemyArray arrayTile = (TileAlchemyArray) tile;
        BlockPos arrayPos = arrayTile.getPos();
        EntityPlayer sacrifice = BloodMagicUtils.getSacrificeTarget(world, arrayPos, 10.00);

        for(EnumFacing facing : EnumFacing.VALUES) {
            TileEntity facingTile = world.getTileEntity(arrayPos.offset(facing));
            if(!isHeatableTile(facingTile))
                continue;

            try {
                IHeatableTile heatable = ReflectionHelper.getIHeatableInstance(furnaceArrayHeatableMap, facingTile);

                if(heatable.canSmelt() && heatable.getBurnTime() <= 0) {
                    if(sacrifice == null || sacrifice.isDead)
                        return;

                    heatable.boostBurnTime(400);
                    heatable.updateTile();

                    if(!sacrifice.isCreative()) {
                        sacrifice.hurtResistantTime = 0;
                        sacrifice.attackEntityFrom(DamageSourceBloodMagic.INSTANCE, 1.0f);
                    }
                }
            } catch (Exception e) {
                LogHelper.error(e);
            }
        }
    }

    @Override
    public boolean checkTile(TileEntity tile) {
        return tile instanceof TileAlchemyArray && ((TileAlchemyArray) tile).arrayEffect instanceof AlchemyArrayEffectFurnaceFuel;
    }
}
