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

public class ItemSigilTemperature extends ItemSigilToggleableBase implements IAddition {
    public static final ItemSigilTemperature TEMPERATURE_SIGIL = new ItemSigilTemperature();
    private static final String NAME = "sigil_temperature";

    public ItemSigilTemperature() {
        super(NAME, ConfigHandlerMI.blood_magic.survival_mods.temperature_sigil.cost);
        this.setRegistryName(MagicultureIntegrations.MOD_ID, NAME);
        this.setTranslationKey(this.getRegistryName().toString());
        this.addPropertyOverride(new ResourceLocation(MagicultureIntegrations.MOD_ID, "enabled"), ((stack, world, entity) -> this.getActivated(stack) ? 1 : 0));
    }

    @Override
    public void onSigilUpdate(ItemStack stack, World world, EntityPlayer player, int itemSlot, boolean isSelected) {
        if(!PlayerHelper.isFakePlayer(player)) {
            if(player.ticksExisted % ConfigHandlerMI.blood_magic.survival_mods.temperature_sigil.delay != 0)
                return;

            if(ModIds.simpledifficulty.isLoaded) {
                SimpleDifficultyUtils.stabilizePlayerTemperature(player);
            }

            if (ModIds.tough_as_nails.isLoaded) {
                ToughAsNailsUtils.stabilizePlayerTemperature(player);
            }
        }
    }

    @Override
    public void registerRecipe() {
        BloodMagicUtils.addGuideEntry("architect", "sigil_temperature");

        BloodMagicAPI.INSTANCE.getRecipeRegistrar().addAlchemyArray(
                new ItemStack(ItemReagent.TEMPERATURE_REAGENT),
                new ItemStack(RegistrarBloodMagicItems.SLATE, 1, 2),
                new ItemStack(TEMPERATURE_SIGIL),
                new ResourceLocation(ModIds.bloodmagic.modId, "textures/models/AlchemyArrays/ElementalAffinitySigil.png")
        );
    }

    @Override
    public boolean isEnabled() {
        return ConfigHandlerMI.blood_magic.survival_mods.temperature_sigil.enable;
    }
}
