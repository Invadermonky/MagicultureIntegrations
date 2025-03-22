package com.invadermonky.magicultureintegrations.integrations.astralsorcery.block.tile;

import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.integrations.astralsorcery.block.BlockCrystalSorter;
import com.invadermonky.magicultureintegrations.integrations.astralsorcery.block.capability.CrystalStackHandler;
import hellfirepvp.astralsorcery.common.item.crystal.CrystalProperties;
import hellfirepvp.astralsorcery.common.item.crystal.CrystalPropertyItem;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import org.jetbrains.annotations.Nullable;

public class TileCrystalSorter extends TileEntity implements ITickable {
    public final CrystalStackHandler input = new CrystalStackHandler();
    public final CrystalStackHandler output_impure = new CrystalStackHandler();
    public final CrystalStackHandler output_pure = new CrystalStackHandler();

    public int processingTime = 0;
    protected int ticksExisted = 0;
    public final int maxProcessingTime = 120;

    /**
     * Gets the crystal in the processing slot. Will return emtpy ItemStack if no crystal is being processed. <b>DO NOT MODIFY THIS VALUE!</b>
     */
    public ItemStack getProcessingCrystal() {
        return this.input.getStackInSlot(0);
    }

    public ItemStack insertProcessingCrystal(ItemStack stack, boolean simulate) {
        return this.input.insertItem(0, stack, simulate);
    }

    public ItemStack removeProcessingCrystal(boolean simulate) {
        return this.input.extractItem(0, this.input.getSlotLimit(0), simulate);
    }

    @Override
    public void update() {
        boolean did = false;
        ++this.ticksExisted;
        if(!this.world.isRemote) {
            EnumFacing impureFacing = this.getWorld().getBlockState(this.getPos()).getValue(BlockCrystalSorter.FACING).rotateY();
            did = this.handleOutputTransfer(this.output_impure, impureFacing) || this.handleOutputTransfer(this.output_pure, impureFacing.getOpposite());
            //Handle Crystal Processing
            ItemStack crystal = this.input.getStackInSlot(0);
            if (!crystal.isEmpty()) {
                if (this.processingTime < this.maxProcessingTime) {
                    this.processingTime++;
                    did = true;
                } else {
                    CrystalProperties properties = this.getCrystalProperties(crystal);
                    if (properties != null) {
                        boolean sizeFlag = !ConfigHandlerMI.integrations.astral_sorcery.crystal_sorter.sortSize || properties.getSize() >= CrystalProperties.getMaxSize(crystal);
                        boolean purityFlag  = properties.getPurity() >= this.getPurityThreshold();
                        IItemHandler outputHandler = purityFlag && sizeFlag ? this.output_pure : this.output_impure;
                        //Transfer into pure slot
                        if (ItemHandlerHelper.insertItem(outputHandler, crystal, true).isEmpty()) {
                            ItemHandlerHelper.insertItem(outputHandler, this.input.extractItem(0, this.input.getSlotLimit(0), false), false);
                            //Resetting processing time if crystal was transferred out
                            this.processingTime = 0;
                            did = true;
                        }
                    }
                }
            } else {
                if(this.processingTime > 0) {
                    this.processingTime = 0;
                    did = true;
                }
            }
        }

        if(did) {
            this.markDirty();
        }
    }

    private boolean handleOutputTransfer(IItemHandler outputHandler, EnumFacing outputSide) {
        ItemStack outputStack = outputHandler.getStackInSlot(0);
        BlockPos offsetPos = this.getPos().offset(outputSide);
        if(!outputStack.isEmpty()) {
            if(this.getWorld().isAirBlock(offsetPos)) {
                outputStack = outputHandler.extractItem(0, outputStack.getCount(), false);
                if(!this.world.isRemote) {
                    double x = offsetPos.getX() + 0.5;
                    double y = offsetPos.getY() + 0.1;
                    double z = offsetPos.getZ() + 0.5;
                    EntityItem entityItem = new EntityItem(this.getWorld(), x, y, z, outputStack);
                    entityItem.motionX = 0;
                    entityItem.motionY = 0.1;
                    entityItem.motionZ = 0;
                    this.getWorld().spawnEntity(entityItem);
                }
                return true;
            } else {
                TileEntity tile = this.getWorld().getTileEntity(offsetPos);
                if(tile != null && tile.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, outputSide.getOpposite())) {
                    IItemHandler handler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, outputSide.getOpposite());
                    if(ItemHandlerHelper.insertItem(handler, outputStack, true).isEmpty()) {
                        outputStack = outputHandler.extractItem(0, outputStack.getCount(), false);
                        ItemHandlerHelper.insertItem(handler, outputStack, false);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Nullable
    public CrystalProperties getCrystalProperties(ItemStack stack) {
        if(stack.getItem() instanceof CrystalPropertyItem) {
            return ((CrystalPropertyItem) stack.getItem()).provideCurrentPropertiesOrNull(stack);
        }
        return null;
    }

    public int getPurityThreshold() {
        int power = this.getWorld().getRedstonePowerFromNeighbors(this.getPos());
        return 100 - (int) ((double) power * 6.25);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.ticksExisted = compound.getInteger("ticksExisted");
        this.processingTime = compound.getInteger("process");
        this.input.deserializeNBT(compound.getCompoundTag("input"));
        this.output_impure.deserializeNBT(compound.getCompoundTag("output_impure"));
        this.output_pure.deserializeNBT(compound.getCompoundTag("output_pure"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("ticksExisted", this.ticksExisted);
        compound.setInteger("process", this.processingTime);
        compound.setTag("input", input.serializeNBT());
        compound.setTag("output_impure", output_impure.serializeNBT());
        compound.setTag("output_pure", output_pure.serializeNBT());
        return compound;
    }

    public boolean isProcessing() {
        return this.processingTime > 0 && this.processingTime < this.maxProcessingTime;
    }

    public int getTicksExisted() {
        return this.ticksExisted;
    }

    public void dropContentsIntoWorld() {
        this.spawnItemStack(this.input.getStackInSlot(0).copy());
        this.spawnItemStack(this.output_impure.getStackInSlot(0).copy());
        this.spawnItemStack(this.output_pure.getStackInSlot(0).copy());
    }

    private void spawnItemStack(ItemStack stack) {
        InventoryHelper.spawnItemStack(this.getWorld(), this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), stack);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        EnumFacing front = this.getWorld().getBlockState(this.getPos()).getValue(BlockCrystalSorter.FACING);
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY
                && (facing == front.getOpposite()               //Input
                || facing == EnumFacing.UP                      //Alt Input
                || facing == front.rotateY()                    //Impure Output
                || facing == front.rotateY().getOpposite());    //Pure Output
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        EnumFacing front = this.getWorld().getBlockState(this.getPos()).getValue(BlockCrystalSorter.FACING);
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && facing != null) {
            if(facing == front.getOpposite() || facing == EnumFacing.UP) {
                return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(this.input);
            } else if(facing == front.rotateY()) {
                return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(this.output_impure);
            } else if(facing == front.getOpposite().rotateY()) {
                return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(this.output_pure);
            }
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public void markDirty() {
        this.world.notifyBlockUpdate(this.getPos(), this.world.getBlockState(this.getPos()), this.world.getBlockState(this.getPos()), 3);
        super.markDirty();
    }

    @Override
    public @Nullable SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(this.getPos(), 0, this.writeToNBT(new NBTTagCompound()));
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return this.writeToNBT(new NBTTagCompound());
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        this.readFromNBT(pkt.getNbtCompound());
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return oldState.getBlock() != newSate.getBlock();
    }
}
