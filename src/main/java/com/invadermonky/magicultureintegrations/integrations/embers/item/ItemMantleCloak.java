package com.invadermonky.magicultureintegrations.integrations.embers.item;

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
import teamroots.embers.Embers;
import teamroots.embers.research.ResearchBase;
import teamroots.embers.research.ResearchManager;
import teamroots.embers.util.EmberInventoryUtil;

public class ItemMantleCloak extends ItemBaubleMI implements IAddition {
    public static final ItemMantleCloak MANTLE_CLOAK = new ItemMantleCloak();
    private static final String ITEM_ID = "mantle_cloak";

    public ItemMantleCloak() {
        this.setRegistryName(MagicultureIntegrations.MOD_ID, ITEM_ID);
        this.setTranslationKey(getRegistryName().toString());
        this.setCreativeTab(Embers.tab);
        this.setMaxStackSize(1);
    }

    /*
        IBauble Methods
    */

    @Override
    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
        if(player.world.isRemote || !(player instanceof EntityPlayer) || ((EntityPlayer) player).isCreative())
            return;

        if(player.ticksExisted % ConfigHandlerMI.embers.survival_mods.mantle_cloak.delay == 0) {
            int cost = ConfigHandlerMI.embers.survival_mods.mantle_cloak.cost;
            if(EmberInventoryUtil.getEmberTotal((EntityPlayer) player) >= cost) {
                if(ModIds.simpledifficulty.isLoaded) {
                    if(SimpleDifficultyUtils.stabilizePlayerTemperature((EntityPlayer) player)) {
                        SimpleDifficultyUtils.clearTemperatureDebuffs((EntityPlayer) player);
                    }
                }
                if(ModIds.tough_as_nails.isLoaded) {
                    if(ToughAsNailsUtils.stabilizePlayerTemperature((EntityPlayer) player)) {
                        ToughAsNailsUtils.clearTemperatureDebuffs((EntityPlayer) player);
                    }
                }
                EmberInventoryUtil.removeEmber((EntityPlayer) player, cost);
            }
        }
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemStack) {
        return BaubleType.BODY;
    }

    /*
        IModAddition Methods
    */

    @Override
    public void registerRecipe() {
        ResearchBase mantle_cloak = (new ResearchBase(ITEM_ID, new ItemStack(ItemMantleCloak.MANTLE_CLOAK), 6.0, 7.0)).addAncestor(ResearchManager.cost_reduction);
        ResearchManager.subCategoryBaubles.addResearch(mantle_cloak);
    }

    @Override
    public boolean isEnabled() {
        return ConfigHandlerMI.embers.survival_mods.mantle_cloak.enable;
    }
}
