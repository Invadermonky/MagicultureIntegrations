package com.invadermonky.magicultureintegrations.core.mixins.immersiveengineering;

import blusunrize.immersiveengineering.api.crafting.BlastFurnaceRecipe;
import blusunrize.immersiveengineering.common.blocks.IEBlockInterfaces;
import blusunrize.immersiveengineering.common.blocks.TileEntityMultiblockPart;
import blusunrize.immersiveengineering.common.blocks.stone.TileEntityBlastFurnace;
import blusunrize.immersiveengineering.common.blocks.stone.TileEntityBlastFurnaceAdvanced;
import blusunrize.immersiveengineering.common.util.inventory.IIEInventory;
import com.invadermonky.magicultureintegrations.api.tile.IBoostableTile;
import com.invadermonky.magicultureintegrations.config.MIConfigIntegrations;
import net.minecraft.tileentity.TileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import javax.annotation.Nullable;

@Mixin(value = TileEntityBlastFurnace.class, remap = false)
public abstract class TileEntityBlastFurnaceBoostableMixin extends TileEntityMultiblockPart<TileEntityBlastFurnace> implements IIEInventory, IEBlockInterfaces.IActiveState, IEBlockInterfaces.IGuiTile, IEBlockInterfaces.IProcessTile, IBoostableTile {
    @Shadow
    public int process;
    @Shadow
    public int processMax;
    @Shadow
    public int burnTime;

    protected TileEntityBlastFurnaceBoostableMixin(int[] structureDimensions) {
        super(structureDimensions);
    }

    @Shadow
    public abstract boolean isDummy();

    @Shadow
    @Nullable
    public abstract BlastFurnaceRecipe getRecipe();

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
        if (!MIConfigIntegrations.immersive_engineering.boostableBlastFurnaceImproved && this.master() instanceof TileEntityBlastFurnaceAdvanced)
            return false;
        return this.formed && this.getRecipe() != null && this.burnTime > 0 && this.process > 0;
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
        this.process = Math.max(1, getCookTimeBoostable() - boostAmount);
    }

    @Override
    public void updateTileBoostable() {
    }
}
