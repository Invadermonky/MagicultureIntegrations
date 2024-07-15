package com.invadermonky.magicultureintegrations.events;

import com.invadermonky.magicultureintegrations.api.events.ITileTickEvent;
import gnu.trove.map.hash.THashMap;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import javax.annotation.ParametersAreNonnullByDefault;

@Mod.EventBusSubscriber
public class WorldEventHandler {
    public static final THashMap<Class<? extends TileEntity>, ITileTickEvent> tileTickMap = new THashMap<>();

    @SubscribeEvent
    public static void onTileEntityTick(TickEvent.WorldTickEvent event) {
        World world = event.world;
        for(TileEntity tile : world.tickableTileEntities) {
            if(tileTickMap.containsKey(tile.getClass()) && tileTickMap.get(tile.getClass()).checkTile(tile)) {
                tileTickMap.get(tile.getClass()).onTileEntityTick(world, tile);
            }
        }
    }

    @ParametersAreNonnullByDefault
    public static void registerTickableTile(Class<? extends TileEntity> tileClass, ITileTickEvent tickSubscriber) {
        tileTickMap.put(tileClass, tickSubscriber);
    }
}
