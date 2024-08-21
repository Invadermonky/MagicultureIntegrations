package com.invadermonky.magicultureintegrations.integrations.botania.item;

import baubles.api.BaubleType;
import com.invadermonky.magicultureintegrations.MagicultureIntegrations;
import com.invadermonky.magicultureintegrations.api.item.ItemBaubleMI;
import com.invadermonky.magicultureintegrations.api.mods.IAddition;
import com.invadermonky.magicultureintegrations.api.mods.simpledifficulty.SimpleDifficultyUtils;
import com.invadermonky.magicultureintegrations.api.mods.toughasnails.ToughAsNailsUtils;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.util.ModIds;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.LexiconCategory;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.core.BotaniaCreativeTab;
import vazkii.botania.common.lexicon.BasicLexiconEntry;
import vazkii.botania.common.lexicon.page.PageCraftingRecipe;
import vazkii.botania.common.lexicon.page.PageText;

public class ItemTemperatureRing extends ItemBaubleMI implements IManaUsingItem, IAddition {
    public static final ItemTemperatureRing TEMPERATURE_RING = new ItemTemperatureRing();

    public ItemTemperatureRing() {
        this.setRegistryName(MagicultureIntegrations.MOD_ID, "temperature_ring");
        this.setTranslationKey(getRegistryName().toString());
        this.setCreativeTab(BotaniaCreativeTab.INSTANCE);
        this.setMaxStackSize(1);
    }

    /*
        Bauble Methods
    */

    @Override
    public void onWornTick(ItemStack stack, EntityLivingBase player) {
        super.onWornTick(stack, player);
        if(player.world.isRemote || !(player instanceof EntityPlayer) || ((EntityPlayer) player).isCreative())
            return;

        int manaCost = ConfigHandlerMI.botania.survival_mods.mana_regulator.cost;
        boolean hasMana = ManaItemHandler.requestManaExact(stack, (EntityPlayer) player, manaCost, false);

        if(hasMana && player.ticksExisted % ConfigHandlerMI.botania.survival_mods.mana_regulator.delay == 0) {
            if(ModIds.simpledifficulty.isLoaded) {
                SimpleDifficultyUtils.stabilizePlayerTemperature((EntityPlayer) player);
            }

            if(ModIds.tough_as_nails.isLoaded) {
                ToughAsNailsUtils.stabilizePlayerTemperature((EntityPlayer) player);
            }

            ManaItemHandler.requestManaExact(stack, (EntityPlayer) player, manaCost, true);
        }
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemStack) {
        return BaubleType.RING;
    }

    /*
        IManaUsingItem Methods
    */

    @Override
    public boolean usesMana(ItemStack itemStack) {
        return true;
    }

    /*
        IModAddition Methods
    */

    @Override
    public void registerRecipe() {
        LexiconCategory categoryBaubles = BotaniaAPI.categoryBaubles;
        LexiconEntry temperatureRing = new BasicLexiconEntry("temperatureRing", categoryBaubles);
        temperatureRing.setLexiconPages(new PageText("0"), new PageCraftingRecipe("1", TEMPERATURE_RING.getRegistryName()));
    }

    @Override
    public boolean isEnabled() {
        return ConfigHandlerMI.botania.survival_mods.mana_regulator.enable;
    }
}
