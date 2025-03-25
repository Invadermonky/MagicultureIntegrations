package com.invadermonky.magicultureintegrations.integrations.astralsorcery.block;

import com.invadermonky.magicultureintegrations.MagicultureIntegrations;
import com.invadermonky.magicultureintegrations.api.IAddition;
import com.invadermonky.magicultureintegrations.api.IProxy;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.integrations.astralsorcery.InitAstralSorcery;
import com.invadermonky.magicultureintegrations.integrations.astralsorcery.block.tile.TileCrystalSorter;
import com.invadermonky.magicultureintegrations.integrations.astralsorcery.client.RenderCrystalSorter;
import com.invadermonky.magicultureintegrations.util.ModIds;
import com.invadermonky.magicultureintegrations.util.StringHelper;
import hellfirepvp.astralsorcery.client.gui.journal.page.JournalPageConstellationRecipe;
import hellfirepvp.astralsorcery.client.gui.journal.page.JournalPageText;
import hellfirepvp.astralsorcery.common.crafting.altar.AltarRecipeRegistry;
import hellfirepvp.astralsorcery.common.crafting.altar.recipes.ConstellationRecipe;
import hellfirepvp.astralsorcery.common.crafting.helper.ShapedRecipe;
import hellfirepvp.astralsorcery.common.crafting.helper.ShapedRecipeSlot;
import hellfirepvp.astralsorcery.common.data.research.ResearchNode;
import hellfirepvp.astralsorcery.common.data.research.ResearchProgression;
import hellfirepvp.astralsorcery.common.item.ItemCraftingComponent;
import hellfirepvp.astralsorcery.common.item.base.IWandInteract;
import hellfirepvp.astralsorcery.common.lib.BlocksAS;
import hellfirepvp.astralsorcery.common.registry.RegistryItems;
import hellfirepvp.astralsorcery.common.util.OreDictAlias;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
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
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

import static hellfirepvp.astralsorcery.common.block.BlockMarble.MarbleBlockType;
import static hellfirepvp.astralsorcery.common.crafting.altar.recipes.AttunementRecipe.AttunementAltarSlot;
import static hellfirepvp.astralsorcery.common.crafting.altar.recipes.ConstellationRecipe.ConstellationAtlarSlot;

@Optional.Interface(modid = ModIds.ConstIds.astral_sorcery, iface = "hellfirepvp.astralsorcery.common.item.base.IWandInteract", striprefs = true)
public class BlockCrystalSorter extends BlockContainer implements IAddition, IProxy, IWandInteract {
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public static final PropertyBool INVERTED = PropertyBool.create("inverted");

    public static ConstellationRecipe crystalSorterRecipe;

    public BlockCrystalSorter() {
        super(Material.IRON, MapColor.GOLD);
        this.setRegistryName(new ResourceLocation(MagicultureIntegrations.MOD_ID, "crystal_sorter"));
        this.setTranslationKey(this.getRegistryName().toString());
        this.setHardness(2.0f);
        this.setSoundType(SoundType.METAL);
        this.setResistance(15.0f);
        this.setHarvestLevel("pickaxe", 1);
        this.setLightLevel(0.3f);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(INVERTED, false));
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

            world.setBlockState(pos, state.withProperty(FACING, enumfacing).withProperty(INVERTED, false), 2);
        }
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack heldStack = playerIn.getHeldItem(hand);
        TileEntity tile = worldIn.getTileEntity(pos);
        if(tile instanceof TileCrystalSorter) {
            if(heldStack.isEmpty() && playerIn.isSneaking()) {
                if(!((TileCrystalSorter) tile).getProcessingCrystal().isEmpty()) {
                    ItemStack extract = ((TileCrystalSorter) tile).removeProcessingCrystal(false);
                    if(!playerIn.addItemStackToInventory(extract)) {
                        playerIn.dropItem(extract, true);
                    }
                    return true;
                }
            } else if(!playerIn.isSneaking()) {
                if(((TileCrystalSorter) tile).insertProcessingCrystal(heldStack, true).isEmpty()) {
                    ((TileCrystalSorter) tile).insertProcessingCrystal(heldStack.splitStack(heldStack.getCount()), false);
                    return true;
                }
            }
        }
        return false;
    }

    @Optional.Method(modid = ModIds.ConstIds.astral_sorcery)
    @Override
    public void onInteract(World world, BlockPos blockPos, EntityPlayer entityPlayer, EnumFacing enumFacing, boolean isSneaking) {
        if(!world.isRemote) {
            IBlockState oldState = world.getBlockState(blockPos);
            IBlockState newState = this.getDefaultState()
                    .withProperty(FACING, oldState.getValue(FACING))
                    .withProperty(INVERTED, !oldState.getValue(INVERTED));
            world.setBlockState(blockPos, newState, 2);
        }
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileCrystalSorter();
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()).withProperty(INVERTED, false);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()).withProperty(INVERTED, false), 2);
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
    public boolean canConnectRedstone(IBlockState state, IBlockAccess world, BlockPos pos, @Nullable EnumFacing side) {
        return false;
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
    public boolean isTopSolid(IBlockState state) {
        return false;
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
        return this.getDefaultState()
                .withProperty(INVERTED, (meta & 4) != 0)
                .withProperty(FACING, EnumFacing.byHorizontalIndex(meta & -5));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return (state.getValue(INVERTED) ? 4 : 0) | state.getValue(FACING).getHorizontalIndex();
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot) {
        return state.withProperty(FACING, rot.rotate(state.getValue(FACING))).withProperty(INVERTED, state.getValue(INVERTED));
    }

    @Override
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
        return state.withRotation(mirrorIn.toRotation(state.getValue(FACING))).withProperty(INVERTED, state.getValue(INVERTED));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, INVERTED);
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
        crystalSorterRecipe = AltarRecipeRegistry.registerConstellationRecipe(
                ShapedRecipe.Builder.newShapedRecipe("internal/altar/crystalsorter", InitAstralSorcery.crystal_sorter)
                        .addPart(BlocksAS.attunementRelay, ShapedRecipeSlot.UPPER_CENTER)
                        .addPart(ItemCraftingComponent.MetaType.GLASS_LENS.asStack(), ShapedRecipeSlot.LEFT, ShapedRecipeSlot.RIGHT)
                        .addPart(BlocksAS.fluidLiquidStarlight, ShapedRecipeSlot.CENTER)
                        .addPart(OreDictAlias.ITEM_GOLD_INGOT, ShapedRecipeSlot.LOWER_LEFT, ShapedRecipeSlot.LOWER_RIGHT)
                        .addPart(MarbleBlockType.RUNED.asStack(), ShapedRecipeSlot.LOWER_CENTER)
                        .unregisteredAccessibleShapedRecipe()
        );
        crystalSorterRecipe.setCstItem(OreDictAlias.ITEM_AQUAMARINE, ConstellationAtlarSlot.UP_UP_LEFT, ConstellationAtlarSlot.UP_UP_RIGHT);
        crystalSorterRecipe.setCstItem(ItemCraftingComponent.MetaType.RESO_GEM.asStack(), ConstellationAtlarSlot.UP_LEFT_LEFT, ConstellationAtlarSlot.UP_RIGHT_RIGHT);
        crystalSorterRecipe.setAttItem(MarbleBlockType.PILLAR.asStack(), AttunementAltarSlot.LOWER_LEFT, AttunementAltarSlot.LOWER_RIGHT);
        crystalSorterRecipe.setCstItem(MarbleBlockType.PILLAR.asStack(), ConstellationAtlarSlot.DOWN_RIGHT_RIGHT, ConstellationAtlarSlot.DOWN_LEFT_LEFT, ConstellationAtlarSlot.DOWN_DOWN_RIGHT, ConstellationAtlarSlot.DOWN_DOWN_LEFT);
        crystalSorterRecipe.setPassiveStarlightRequirement(2000);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerModels(ModelRegistryEvent event) {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(this.getRegistryName(), "inventory"));
        ClientRegistry.bindTileEntitySpecialRenderer(TileCrystalSorter.class, new RenderCrystalSorter());
    }

    @Override
    public void postInit() {
        //Guide entry
        ResearchProgression.Registry regConstellation = ResearchProgression.CONSTELLATION.getRegistry();
        ResearchNode crystalSorterNode = new ResearchNode(new ItemStack(InitAstralSorcery.crystal_sorter), "CRYSTALSORTER", 4, 3);
        crystalSorterNode.addPage(new JournalPageText(StringHelper.getTranslationKey("CRYSTALSORTER", "journal", "text1")));
        crystalSorterNode.addPage(new JournalPageConstellationRecipe(crystalSorterRecipe));
        crystalSorterNode.addPage(new JournalPageText(StringHelper.getTranslationKey("CRYSTALSORTER", "journal", "text2")));

        ResearchNode infusionNode = ResearchProgression.findNode("INFUSER");
        if(infusionNode != null) {
            crystalSorterNode.addSourceConnectionFrom(infusionNode);
        }
        regConstellation.register(crystalSorterNode);
    }
}
