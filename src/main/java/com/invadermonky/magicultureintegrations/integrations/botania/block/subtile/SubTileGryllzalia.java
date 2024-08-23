package com.invadermonky.magicultureintegrations.integrations.botania.block.subtile;

import com.invadermonky.magicultureintegrations.api.mods.IConfigurable;
import com.invadermonky.magicultureintegrations.api.mods.simpledifficulty.SimpleDifficultyUtils;
import com.invadermonky.magicultureintegrations.api.mods.toughasnails.ToughAsNailsUtils;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.util.ModIds;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileFunctional;

import java.util.List;

public class SubTileGryllzalia extends SubTileFunctional implements IConfigurable {
    public static final SubTileGryllzalia GRYLLZALIA = new SubTileGryllzalia();
    public static final String NAME = "gryllzalia";
    public static LexiconEntry GRYLLZALIA_ENTRY;
    private static final int RANGE = 8;

    /*
        SubTileFunctional Methods
    */
    @Override
    public void onUpdate() {
        super.onUpdate();
        if(!this.supertile.getWorld().isRemote && this.redstoneSignal <= 0) {
            boolean did = false;
            int cost = ConfigHandlerMI.botania.flowers.gryllzalia.cost;
            int delay = ConfigHandlerMI.botania.flowers.gryllzalia.delay;
            List<EntityPlayer> players = this.supertile.getWorld().getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(this.supertile.getPos().add(-RANGE, -RANGE, -RANGE), this.supertile.getPos().add(RANGE,RANGE,RANGE)));
            for(EntityPlayer player : players) {
                if(player.ticksExisted % delay != 0)
                    continue;

                if(ModIds.simpledifficulty.isLoaded && SimpleDifficultyUtils.stabilizePlayerTemperature(player)) {
                    this.mana -= cost;
                    did = true;
                }
                if(ModIds.tough_as_nails.isLoaded && ToughAsNailsUtils.stabilizePlayerTemperature(player)) {
                    this.mana -= cost;
                    did = true;
                }
            }
            if(did) {
                this.sync();
            }
        }
    }

    @Override
    public boolean acceptsRedstone() {
        return true;
    }

    @Override
    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(this.toBlockPos(), RANGE);
    }

    @Override
    public int getColor() {
        return super.getColor();
    }

    @Override
    public int getMaxMana() {
        return 100;
    }

    @Override
    public LexiconEntry getEntry() {
        return GRYLLZALIA_ENTRY;
    }

    @Override
    public boolean isEnabled() {
        return ConfigHandlerMI.botania.flowers.gryllzalia.enable;
    }
}
