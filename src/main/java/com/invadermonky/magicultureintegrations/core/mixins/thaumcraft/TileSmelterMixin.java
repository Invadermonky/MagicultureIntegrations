package com.invadermonky.magicultureintegrations.core.mixins.thaumcraft;

import com.invadermonky.magicultureintegrations.api.tile.IHeatableTile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import thaumcraft.common.blocks.essentia.BlockSmelter;
import thaumcraft.common.tiles.TileThaumcraftInventory;
import thaumcraft.common.tiles.essentia.TileSmelter;

@Mixin(value = TileSmelter.class, remap = false)
public abstract class TileSmelterMixin extends TileThaumcraftInventory implements IHeatableTile {
    @Shadow public int furnaceBurnTime;
    @Shadow public int furnaceCookTime;
    @Shadow public int currentItemBurnTime;
    @Shadow public int smeltTime;

    public TileSmelterMixin(int size) {
        super(size);
    }

    @Shadow protected abstract boolean canSmelt();

    @Override
    public boolean canSmeltHeatable() {
        return this.canSmelt();
    }

    @Override
    public int getBurnTimeHeatable() {
        return this.furnaceBurnTime;
    }

    @Override
    public int getBurnTimeMaxHeatable() {
        return this.currentItemBurnTime;
    }

    @Override
    public int getCookTimeHeatable() {
        return this.furnaceCookTime;
    }

    @Override
    public int getCookTimeMaxHeatable() {
        return this.smeltTime;
    }

    @Override
    public void setBurnTimeMaxHeatable(int burnTimeMax) {
        this.currentItemBurnTime = burnTimeMax;
    }

    @Override
    public void boostBurnTimeHeatable(int boostAmount) {
        this.furnaceBurnTime += boostAmount;
        setBurnTimeMaxHeatable(getBurnTimeHeatable());
    }

    @Override
    public void boostCookTimeHeatable(int boostAmount) {
        this.furnaceCookTime = Math.min(getCookTimeMaxHeatable() - 1, getCookTimeHeatable() + boostAmount);
    }

    @Override
    public void updateTileHeatable() {
        BlockSmelter.setFurnaceState(this.world, this.getPos(), true);
        this.markDirty();
    }
}
