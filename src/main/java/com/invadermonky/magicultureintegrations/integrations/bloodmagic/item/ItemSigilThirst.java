package com.invadermonky.magicultureintegrations.integrations.bloodmagic.item;

import WayofTime.bloodmagic.api.impl.BloodMagicAPI;
import WayofTime.bloodmagic.core.RegistrarBloodMagicItems;
import WayofTime.bloodmagic.item.sigil.ItemSigilToggleableBase;
import WayofTime.bloodmagic.util.helper.PlayerHelper;
import com.invadermonky.magicultureintegrations.MagicultureIntegrations;
import com.invadermonky.magicultureintegrations.api.mods.IAddition;
import com.invadermonky.magicultureintegrations.api.mods.bloodmagic.BloodMagicUtils;
import com.invadermonky.magicultureintegrations.api.mods.simpledifficulty.SimpleDifficultyUtils;
import com.invadermonky.magicultureintegrations.api.mods.toughasnails.ToughAsNailsUtils;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.util.ModIds;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ItemSigilThirst extends ItemSigilToggleableBase implements IAddition {
    public static final ItemSigilThirst THIRST_SIGIL = new ItemSigilThirst();
    private static final String NAME = "sigil_thirst";

    public ItemSigilThirst() {
        super(NAME, ConfigHandlerMI.blood_magic.survival_mods.sigil_of_hydration.cost);
        this.setRegistryName(MagicultureIntegrations.MOD_ID, NAME);
        this.setTranslationKey(this.getRegistryName().toString());
        this.addPropertyOverride(new ResourceLocation(MagicultureIntegrations.MOD_ID, "enabled"), (stack, world, entity) -> this.getActivated(stack) ? 1 : 0);
    }

    @Override
    public void onSigilUpdate(ItemStack stack, World world, EntityPlayer player, int itemSlot, boolean isSelected) {
        if(!PlayerHelper.isFakePlayer(player)) {
            if(player.ticksExisted % ConfigHandlerMI.blood_magic.survival_mods.sigil_of_hydration.delay != 0)
                return;

            if(ModIds.simpledifficulty.isLoaded) {
                SimpleDifficultyUtils.hydratePlayer(player, 1, 0.2f);
            }

            if(ModIds.tough_as_nails.isLoaded) {
                ToughAsNailsUtils.hydratePlayer(player, 1, 0.2f);
            }
        }
    }

    @Override
    public void registerRecipe() {
        BloodMagicUtils.addGuideEntry("architect", "sigil_thirst");

        BloodMagicAPI.INSTANCE.getRecipeRegistrar().addAlchemyArray(
                new ItemStack(ItemReagent.THIRST_REAGENT),
                new ItemStack(RegistrarBloodMagicItems.SLATE, 1, 2),
                new ItemStack(THIRST_SIGIL),
                new ResourceLocation(ModIds.bloodmagic.modId, "textures/models/AlchemyArrays/WaterSigil.png")
        );

    }

    @Override
    public boolean isEnabled() {
        return ConfigHandlerMI.blood_magic.survival_mods.sigil_of_hydration.enable;
    }
}
