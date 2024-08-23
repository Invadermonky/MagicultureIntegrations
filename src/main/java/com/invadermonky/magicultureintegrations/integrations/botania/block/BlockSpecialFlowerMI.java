package com.invadermonky.magicultureintegrations.integrations.botania.block;

import com.invadermonky.magicultureintegrations.MagicultureIntegrations;
import com.invadermonky.magicultureintegrations.api.mods.IAddition;
import com.invadermonky.magicultureintegrations.integrations.botania.block.subtile.SubTileAuromeria;
import com.invadermonky.magicultureintegrations.integrations.botania.block.subtile.SubTileGryllzalia;
import com.invadermonky.magicultureintegrations.util.ModIds;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import thaumcraft.api.items.ItemsTC;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.BotaniaAPIClient;
import vazkii.botania.api.recipe.RecipePetals;
import vazkii.botania.api.subtile.SubTileEntity;
import vazkii.botania.common.block.BlockSpecialFlower;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;
import vazkii.botania.common.lexicon.BasicLexiconEntry;
import vazkii.botania.common.lexicon.page.PagePetalRecipe;
import vazkii.botania.common.lexicon.page.PageText;

public class BlockSpecialFlowerMI extends BlockSpecialFlower implements IAddition {
    public static final BlockSpecialFlowerMI BLOCK_SPECIAL_FLOWER = new BlockSpecialFlowerMI();
    public boolean isAuromeriaEnabled;
    public boolean isGryllzaliaEnabled;

    public BlockSpecialFlowerMI() {
        isAuromeriaEnabled = ModIds.thaumcraft.isLoaded && SubTileAuromeria.AUROMERIA.isEnabled();
        isGryllzaliaEnabled = (ModIds.simpledifficulty.isLoaded || ModIds.tough_as_nails.isLoaded) && SubTileGryllzalia.GRYLLZALIA.isEnabled();
    }

    @Override
    public void getSubBlocks(CreativeTabs tab, @NotNull NonNullList<ItemStack> stacks) {
        if(isAuromeriaEnabled) {
            stacks.add(ItemBlockSpecialFlower.ofType(SubTileAuromeria.NAME));
            stacks.add(ItemBlockSpecialFlower.ofType(new ItemStack(ModBlocks.floatingSpecialFlower), SubTileAuromeria.NAME));
        }

        if(isGryllzaliaEnabled) {
            stacks.add(ItemBlockSpecialFlower.ofType(SubTileGryllzalia.NAME));
            stacks.add(ItemBlockSpecialFlower.ofType(new ItemStack(ModBlocks.floatingSpecialFlower), SubTileGryllzalia.NAME));
        }
    }

    @Override
    public void registerRecipe() {
        //TODO:
        if(isAuromeriaEnabled) {
            RecipePetals auromeriaRecipe = new RecipePetals(ItemBlockSpecialFlower.ofType(SubTileAuromeria.NAME), "petalPurple", "petalPurple", "petalRed", "petalWhite", "runeManaB", new ItemStack(ItemsTC.visResonator), "redstoneRoot");
            BotaniaAPI.registerPetalRecipe(auromeriaRecipe.getOutput(), auromeriaRecipe.getInputs().toArray());
            SubTileAuromeria.AUROMERIA_ENTRY = new BasicLexiconEntry("auromeria", BotaniaAPI.categoryFunctionalFlowers);
            SubTileAuromeria.AUROMERIA_ENTRY.setLexiconPages(
                    new PageText("0"),
                    new PageText("1"),
                    new PageText("2"),
                    new PageText("3"),
                    new PagePetalRecipe<>("4", auromeriaRecipe));
        }

        if(isGryllzaliaEnabled) {
            RecipePetals gryllzaliaRecipe = new RecipePetals(ItemBlockSpecialFlower.ofType(SubTileGryllzalia.NAME), "petalWhite", "petalWhite", "petalRed", "petalRed", "runeSpringB", "redstoneRoot");
            BotaniaAPI.registerPetalRecipe(gryllzaliaRecipe.getOutput(), gryllzaliaRecipe.getInputs().toArray());
            SubTileGryllzalia.GRYLLZALIA_ENTRY = new BasicLexiconEntry("gryllzalia", BotaniaAPI.categoryFunctionalFlowers);
            SubTileGryllzalia.GRYLLZALIA_ENTRY.setLexiconPages(new PageText("0"), new PagePetalRecipe<>("1", gryllzaliaRecipe));
        }
    }

    @Override
    public boolean isEnabled() {
        boolean enabled = false;
        if(isAuromeriaEnabled) {
            BotaniaAPI.addSubTileToCreativeMenu(SubTileAuromeria.NAME);
            BotaniaAPI.registerSubTile(SubTileAuromeria.NAME, SubTileAuromeria.class);
            registerRenders(SubTileAuromeria.class, SubTileAuromeria.NAME);
            enabled = true;
        }

        if(SubTileGryllzalia.GRYLLZALIA.isEnabled()) {
            BotaniaAPI.addSubTileToCreativeMenu(SubTileGryllzalia.NAME);
            BotaniaAPI.registerSubTile(SubTileGryllzalia.NAME, SubTileGryllzalia.class);
            registerRenders(SubTileGryllzalia.class, SubTileGryllzalia.NAME);
            enabled = true;
        }
        return enabled;
    }

    @SideOnly(Side.CLIENT)
    public void registerRenders(Class<? extends SubTileEntity> clazz, String name) {
        BotaniaAPIClient.registerSubtileModel(clazz, new ModelResourceLocation(MagicultureIntegrations.MOD_ID + ":" + name));
    }

}
