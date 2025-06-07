package com.invadermonky.magicultureintegrations.core.mixins.bloodmagic;

import WayofTime.bloodmagic.item.gear.ItemPackSelfSacrifice;
import WayofTime.bloodmagic.util.helper.ItemHelper;
import WayofTime.bloodmagic.util.helper.NetworkHelper;
import com.invadermonky.magicultureintegrations.config.MIConfigTweaks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = ItemPackSelfSacrifice.class, remap = false)
public abstract class ItemPackSelfSacrificeMixin {
    @Shadow
    public abstract int getStoredLP(ItemStack stack);

    @Shadow
    public abstract void setStoredLP(ItemStack stack, int lp);

    /**
     * @author Invadermonky
     * @reason Allow the configuration of the LP capacity of the Coat of Arms
     */
    @Overwrite
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
        if (!world.isRemote && !player.capabilities.isCreativeMode) {
            boolean shouldSyphon = player.getHealth() / player.getMaxHealth() > 0.5F && this.getStoredLP(stack) < this.getCapacity();
            if (shouldSyphon & world.getTotalWorldTime() % 20L == 0L) {
                NetworkHelper.getSoulNetwork(player).hurtPlayer(player, 1.0F);
                ItemHelper.LPContainer.addLPToItem(stack, MIConfigTweaks.blood_magic.selfSacrificePackGain, this.getCapacity());
            }

            if (this.getStoredLP(stack) > this.getCapacity()) {
                this.setStoredLP(stack, this.getCapacity());
            }
        }
    }

    /**
     * @author Invadermonky
     * @reason Allow the configuration of the LP capacity of the Coat of Arms
     */
    @Overwrite
    public int getCapacity() {
        return MIConfigTweaks.blood_magic.selfSacrificePackCapacity;
    }
}
