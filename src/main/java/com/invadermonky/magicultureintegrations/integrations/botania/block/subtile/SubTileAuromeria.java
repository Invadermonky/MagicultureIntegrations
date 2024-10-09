package com.invadermonky.magicultureintegrations.integrations.botania.block.subtile;

import com.invadermonky.magicultureintegrations.api.mods.IConfigurable;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.integrations.botania.item.ItemLensFlux;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import thaumcraft.api.aura.AuraHelper;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.SubTileFunctional;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.entity.EntityManaBurst;

public class SubTileAuromeria extends SubTileFunctional implements IConfigurable {
    public static final SubTileAuromeria AUROMERIA = new SubTileAuromeria();
    public static final String NAME = "auromeria";
    public static LexiconEntry AUROMERIA_ENTRY;

    //If you want to change these values you can do so with Groovyscript
    public static int visPowerCost = 10;
    public static int manaCost = 40;

    protected int visPower = 0;

    @Override
    public void onUpdate() {
        super.onUpdate();

        //Slight delay before this flower activates.
        if(this.ticksExisted <= 60)
            return;

        World world = supertile.getWorld();
        if(!world.isRemote && this.redstoneSignal <= 0 && this.ticksExisted % 20 == 0) {
            boolean did = false;
            BlockPos flowerPos = supertile.getPos();
            float flux = AuraHelper.getFlux(world, flowerPos);
            float vis = AuraHelper.getVis(world, flowerPos);
            int base = AuraHelper.getAuraBase(world, flowerPos);
            float fluxPercent = flux / base;
            boolean canDrain = vis >= 1.0f && ((vis / base) > 0.4 || (flux + vis) >= base);
            boolean drainedFlux = false;

            if(canDrain && this.visPower <= 0) {
                AuraHelper.drainVis(world, flowerPos, 1.0f, false);
                this.visPower += 100;
            }

            if(this.mana >= manaCost && this.visPower > 0) {
                if(flux > 0 && world.rand.nextFloat() < (ConfigHandlerMI.integrations.botania.auromeria.fluxDrainChance + fluxPercent)) {
                    AuraHelper.drainFlux(world, flowerPos, (float) ConfigHandlerMI.integrations.botania.auromeria.fluxDrainAmount, false);
                    drainedFlux = true;
                }
                this.mana -= manaCost;
                this.visPower -= visPowerCost;
                spawnFluxBurst(world, flowerPos, drainedFlux);
                did = true;
            }

            if (did) {
                this.sync();
            }
        }
    }

    public void spawnFluxBurst(World world, BlockPos pos, boolean drainedFlux) {
        EntityManaBurst burst = new EntityManaBurst(world);
        burst.setBurstSourceCoords(pos);
        burst.setPosition(pos.getX() + 0.5, pos.getY() + 0.8, pos.getZ() + 0.5);
        float motionModifier = 0.5f;
        burst.setColor(16711808);
        burst.setMana(120 * (drainedFlux ? 2 : 1));
        burst.setStartingMana(340);
        burst.setMinManaLoss(50);
        burst.setManaLossPerTick(1.0f);
        burst.setGravity(0.0f);
        ItemStack lens = new ItemStack(ItemLensFlux.FLUX_LENS);
        burst.setSourceLens(lens);
        Vector3 motion = (new Vector3(Math.random() - 0.5, Math.random() - 0.5, Math.random() - 0.5)).normalize().multiply(motionModifier);
        burst.setMotion(motion.x, motion.y, motion.z);
        world.spawnEntity(burst);
    }

    @Override
    public boolean acceptsRedstone() {
        return true;
    }

    @Override
    public int getColor() {
        return 16711808;
    }

    @Override
    public int getMaxMana() {
        return manaCost * 5;
    }

    @Override
    public LexiconEntry getEntry() {
        return AUROMERIA_ENTRY;
    }

    @Override
    public boolean isEnabled() {
        return ConfigHandlerMI.integrations.botania.auromeria.enableAuromeria;
    }
}
