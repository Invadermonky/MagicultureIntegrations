package com.invadermonky.magicultureintegrations.additions.mods.naturesaura.item;

import baubles.api.BaubleType;
import com.invadermonky.magicultureintegrations.MagicultureIntegrations;
import com.invadermonky.magicultureintegrations.api.item.ItemBaubleMI;
import com.invadermonky.magicultureintegrations.api.mods.IModAddition;
import com.invadermonky.magicultureintegrations.api.mods.simpledifficulty.SimpleDifficultyUtils;
import com.invadermonky.magicultureintegrations.api.mods.toughasnails.ToughAsNailsUtils;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.util.ModIds;
import de.ellpeck.naturesaura.NaturesAura;
import de.ellpeck.naturesaura.api.NaturesAuraAPI;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemTemperatureAmulet extends ItemBaubleMI implements IModAddition {
    public static final ItemTemperatureAmulet TEMPERATURE_AMULET = new ItemTemperatureAmulet();
    private static final String ITEM_ID = "temperature_amulet";

    public ItemTemperatureAmulet() {
        this.setRegistryName(MagicultureIntegrations.MOD_ID, ITEM_ID);
        this.setTranslationKey(getRegistryName().toString());
        this.setCreativeTab(NaturesAura.CREATIVE_TAB);
        this.setMaxStackSize(1);
    }

    /*
        IBauble Methods
    */

    @Override
    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
        if(player.world.isRemote || !(player instanceof EntityPlayer) || ((EntityPlayer) player).isCreative())
            return;

        if(player.ticksExisted % ConfigHandlerMI.natures_aura.survival_mods.environmental_ring.delay == 0) {
            int cost = ConfigHandlerMI.natures_aura.survival_mods.environmental_ring.cost;

            if(NaturesAuraAPI.instance().extractAuraFromPlayer((EntityPlayer) player, cost, false)) {
                if (ModIds.simpledifficulty.isLoaded) {
                    SimpleDifficultyUtils.stabilizePlayerTemperature((EntityPlayer) player);
                }

                if (ModIds.tough_as_nails.isLoaded) {
                    ToughAsNailsUtils.stabilizePlayerTemperature((EntityPlayer) player);
                }
            }
        }
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemStack) {
        return BaubleType.AMULET;
    }

    /*
        IModAddition Methods
    */

    @Override
    public void registerRecipe() {
        //TODO: Find a way to load the new page into the NA patchouli guide.
    }

    @Override
    public boolean isEnabled() {
        return ConfigHandlerMI.natures_aura.survival_mods.environmental_ring.enable;
    }
}
