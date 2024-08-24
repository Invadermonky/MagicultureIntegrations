package com.invadermonky.magicultureintegrations.integrations.bloodmagic.item;

import WayofTime.bloodmagic.BloodMagic;
import WayofTime.bloodmagic.api.impl.BloodMagicAPI;
import WayofTime.bloodmagic.api.impl.BloodMagicRecipeRegistrar;
import WayofTime.bloodmagic.core.RegistrarBloodMagicItems;
import com.charles445.simpledifficulty.api.SDItems;
import com.invadermonky.magicultureintegrations.MagicultureIntegrations;
import com.invadermonky.magicultureintegrations.api.mods.IAddition;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.util.ModIds;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import toughasnails.api.item.TANItems;

public class ItemReagent extends Item implements IAddition {
    public static final ItemReagent TEMPERATURE_REAGENT = new ItemReagent(ReagentType.TEMPERATURE);
    public static final ItemReagent THIRST_REAGENT = new ItemReagent(ReagentType.THIRST);

    private final ReagentType type;

    public ItemReagent(ReagentType type) {
        this.setRegistryName(MagicultureIntegrations.MOD_ID, "reagent_" + type.toString());
        this.setTranslationKey(getRegistryName().toString());
        this.setCreativeTab(BloodMagic.TAB_BM);
        this.type = type;
    }

    private ItemStack getItemStack() {
        switch(type) {
            case TEMPERATURE:
                return new ItemStack(TEMPERATURE_REAGENT);
            case THIRST:
                return new ItemStack(THIRST_REAGENT);
        }
        return ItemStack.EMPTY;
    }

    @Override
    public void registerRecipe() {
        BloodMagicRecipeRegistrar registrar = BloodMagicAPI.INSTANCE.getRecipeRegistrar();

        if(type == ReagentType.TEMPERATURE) {
            registrar.addTartaricForge(
                    this.getItemStack(),
                    300,
                    30,
                    new ItemStack(RegistrarBloodMagicItems.SIGIL_WATER),
                    new ItemStack(RegistrarBloodMagicItems.SIGIL_AIR),
                    new ItemStack(RegistrarBloodMagicItems.SIGIL_LAVA),
                    "ingotGold"
            );
        } else if(type == ReagentType.THIRST) {
            ItemStack canteenStack = new ItemStack(Items.GLASS_BOTTLE);
            ItemStack filterStack = new ItemStack(Items.COAL, 1, 1);

            if(ModIds.simpledifficulty.isLoaded) {
                canteenStack = new ItemStack(SDItems.canteen);
                filterStack = new ItemStack(SDItems.charcoalFilter);
            }
            if(ModIds.tough_as_nails.isLoaded) {
                canteenStack = new ItemStack(TANItems.canteen);
                filterStack = new ItemStack(TANItems.charcoal_filter);
            }

            registrar.addTartaricForge(
                    this.getItemStack(),
                    600,
                    50,
                    new ItemStack(RegistrarBloodMagicItems.SIGIL_WATER),
                    filterStack,
                    filterStack,
                    canteenStack
            );
        }
    }

    @Override
    public boolean isEnabled() {
        switch (this.type) {
            case TEMPERATURE:
                return ConfigHandlerMI.blood_magic.survival_mods.sigil_of_temperate_lands.enable;
            case THIRST:
                return ConfigHandlerMI.blood_magic.survival_mods.sigil_of_hydration.enable;
        }
        return false;
    }

    public enum ReagentType {
        TEMPERATURE,
        THIRST;

        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
    }
}
