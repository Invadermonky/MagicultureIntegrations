package com.invadermonky.magicultureintegrations.api.mods.botania;

import com.google.common.collect.Lists;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import vazkii.botania.common.block.subtile.functional.SubTileExoflame;
import vazkii.botania.common.block.tile.TileSpecialFlower;

import java.util.List;

public class BotaniaUtils {
    public static AxisAlignedBB bounding = new AxisAlignedBB(-5, -2, -5, 5, 2, 5);

    public static List<BlockPos> getExoflameArea(World world, BlockPos pos) {
        AxisAlignedBB bounds = bounding.offset(pos);
        BlockPos start = new BlockPos(bounds.minX, bounds.minY, bounds.minZ);
        BlockPos stop = new BlockPos(bounds.maxX, bounds.maxY, bounds.maxZ);
        return Lists.newArrayList(BlockPos.getAllInBox(start, stop));
    }

    public static boolean isTileExoflame(TileEntity tile) {
        return tile instanceof TileSpecialFlower && ((TileSpecialFlower) tile).getSubTile() instanceof SubTileExoflame;
    }
}
