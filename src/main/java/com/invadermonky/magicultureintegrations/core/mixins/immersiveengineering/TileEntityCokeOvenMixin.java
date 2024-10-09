package com.invadermonky.magicultureintegrations.core.mixins.immersiveengineering;

import blusunrize.immersiveengineering.api.crafting.CokeOvenRecipe;
import blusunrize.immersiveengineering.common.blocks.IEBlockInterfaces;
import blusunrize.immersiveengineering.common.blocks.TileEntityMultiblockPart;
import blusunrize.immersiveengineering.common.blocks.stone.TileEntityCokeOven;
import blusunrize.immersiveengineering.common.util.inventory.IIEInventory;
import com.invadermonky.magicultureintegrations.api.tile.IBoostableTile;
import net.minecraft.tileentity.TileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = TileEntityCokeOven.class, remap = false)
public abstract class TileEntityCokeOvenMixin extends TileEntityMultiblockPart<TileEntityCokeOven> implements IIEInventory, IEBlockInterfaces.IActiveState, IEBlockInterfaces.IGuiTile, IEBlockInterfaces.IProcessTile, IBoostableTile {
    @Shadow public int process;
    @Shadow public int processMax;
    @Shadow public abstract boolean isDummy();
    @Shadow public abstract CokeOvenRecipe getRecipe();

    @Shadow public abstract boolean getIsActive();

    protected TileEntityCokeOvenMixin(int[] structureDimensions) {
        super(structureDimensions);
    }

    @Override
    public boolean isTrueBoostable() {
        return !this.isDummy();
    }

    @Override
    public IBoostableTile getTrueBoostable() {
        TileEntity master = this.master();
        return master instanceof IBoostableTile ? (IBoostableTile) master : this;
    }

    @Override
    public boolean canSmeltBoostable() {
        return this.formed && this.getRecipe() != null && this.process > 0;
    }

    @Override
    public int getCookTimeBoostable() {
        return this.process;
    }

    @Override
    public int getCookTimeMaxBoostable() {
        return this.processMax;
    }

    @Override
    public void boostCookTimeBoostable(int boostAmount) {
        this.process = Math.max(1, this.process - boostAmount);
    }

    @Override
    public void updateTileBoostable() {
        this.markDirty();
    }
}
