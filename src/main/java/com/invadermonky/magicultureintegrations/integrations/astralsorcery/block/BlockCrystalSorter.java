package com.invadermonky.magicultureintegrations.integrations.astralsorcery.block;

import com.invadermonky.magicultureintegrations.MagicultureIntegrations;
import com.invadermonky.magicultureintegrations.api.IAddition;
import com.invadermonky.magicultureintegrations.api.IProxy;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.integrations.astralsorcery.block.tile.TileCrystalSorter;
import com.invadermonky.magicultureintegrations.integrations.astralsorcery.client.RenderCrystalSorter;
import hellfirepvp.astralsorcery.common.registry.RegistryItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class BlockCrystalSorter extends BlockContainer implements IAddition, IProxy {
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    //TODO: Player interaction inserts the held crystal into the machine. Side does not matter.
    //TODO: Redstone signal is deactivating connected hoppers.
    public BlockCrystalSorter() {
        super(Material.IRON, MapColor.GOLD);
        this.setRegistryName(new ResourceLocation(MagicultureIntegrations.MOD_ID, "crystal_sorter"));
        this.setTranslationKey(this.getRegistryName().toString());
        this.setHardness(2.0f);
        this.setSoundType(SoundType.METAL);
        this.setResistance(15.0f);
        this.setHarvestLevel("pickaxe", 1);
        this.setLightLevel(0.3f);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
        this.setCreativeTab(RegistryItems.creativeTabAstralSorcery);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(this);
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        this.setDefaultFacing(worldIn, pos, state);
    }

    private void setDefaultFacing(World world, BlockPos pos, IBlockState state) {
        if (!world.isRemote) {
            IBlockState iblockstate = world.getBlockState(pos.north());
            IBlockState iblockstate1 = world.getBlockState(pos.south());
            IBlockState iblockstate2 = world.getBlockState(pos.west());
            IBlockState iblockstate3 = world.getBlockState(pos.east());
            EnumFacing enumfacing = state.getValue(FACING);

            if (enumfacing == EnumFacing.NORTH && iblockstate.isFullBlock() && !iblockstate1.isFullBlock()) {
                enumfacing = EnumFacing.SOUTH;
            } else if (enumfacing == EnumFacing.SOUTH && iblockstate1.isFullBlock() && !iblockstate.isFullBlock()) {
                enumfacing = EnumFacing.NORTH;
            } else if (enumfacing == EnumFacing.WEST && iblockstate2.isFullBlock() && !iblockstate3.isFullBlock()) {
                enumfacing = EnumFacing.EAST;
            } else if (enumfacing == EnumFacing.EAST && iblockstate3.isFullBlock() && !iblockstate2.isFullBlock()) {
                enumfacing = EnumFacing.WEST;
            }

            world.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
        }
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack heldStack = playerIn.getHeldItem(hand);
        TileEntity tile = worldIn.getTileEntity(pos);
        if(tile instanceof TileCrystalSorter) {
            if(heldStack.isEmpty() && !playerIn.isSneaking()) {
                if(!((TileCrystalSorter) tile).getProcessingCrystal().isEmpty()) {
                    ItemStack extract = ((TileCrystalSorter) tile).removeProcessingCrystal(false);
                    if(!playerIn.addItemStackToInventory(extract)) {
                        playerIn.dropItem(extract, true);
                    }
                    return true;
                }
            } else if(playerIn.isSneaking()) {
                if(((TileCrystalSorter) tile).insertProcessingCrystal(heldStack, true).isEmpty()) {
                    ((TileCrystalSorter) tile).insertProcessingCrystal(heldStack, false);
                    heldStack.shrink(heldStack.getCount());
                    return true;
                }
            }
        }
        return false;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileCrystalSorter();
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileEntity tile = worldIn.getTileEntity(pos);
        if(tile instanceof TileCrystalSorter) {
            ((TileCrystalSorter) tile).dropContentsIntoWorld();
            worldIn.updateComparatorOutputLevel(pos, this);
        }
        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(this);
    }

    @Override
    public boolean hasComparatorInputOverride(IBlockState state) {
        return true;
    }

    @Override
    public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos) {
        TileEntity tile = worldIn.getTileEntity(pos);
        if(tile instanceof TileCrystalSorter) {
            return ((TileCrystalSorter) tile).getProcessingCrystal().isEmpty() ? 0 : 15;
        }
        return 0;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        return true;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing facing = EnumFacing.byIndex(meta);
        if(facing.getAxis() == EnumFacing.Axis.Y) {
            facing = EnumFacing.NORTH;
        }
        return this.getDefaultState().withProperty(FACING, facing);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getIndex();
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot) {
        return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
        return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }

    @Override
    public boolean isEnabled() {
        return ConfigHandlerMI.integrations.astral_sorcery.crystal_sorter.enable;
    }

    @Override
    public void registerBlocks(IForgeRegistry<Block> registry) {
        registry.register(this);
        GameRegistry.registerTileEntity(TileCrystalSorter.class, this.getRegistryName());
    }

    @Override
    public void registerItems(IForgeRegistry<Item> registry) {
        registry.register(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public void registerRecipes(IForgeRegistry<IRecipe> registry) {
        //TODO
    }

    @Override
    public void registerModels(ModelRegistryEvent event) {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(this.getRegistryName(), "inventory"));
        ClientRegistry.bindTileEntitySpecialRenderer(TileCrystalSorter.class, new RenderCrystalSorter());
    }

    @Override
    public void postInit() {
        //Guide entry
    }
}
