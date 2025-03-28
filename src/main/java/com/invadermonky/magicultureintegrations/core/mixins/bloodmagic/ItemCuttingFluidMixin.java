package com.invadermonky.magicultureintegrations.core.mixins.bloodmagic;

import WayofTime.bloodmagic.item.alchemy.ItemCuttingFluid;
import com.invadermonky.magicultureintegrations.config.MIConfigTweaks;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = ItemCuttingFluid.class, remap = false)
public class ItemCuttingFluidMixin {
    /**
     * @author Invadermonky
     * @reason Add configurable Cutting Fluid/Explosive Powder max uses.
     */
    @Overwrite
    public int getMaxUsesForFluid(ItemStack stack) {
        int uses;
        switch (stack.getMetadata()) {
            case 0:
                uses = MIConfigTweaks.blood_magic.cuttingFluidMaxUses;
                break;
            case 1:
                uses = MIConfigTweaks.blood_magic.explosivePowderMaxUses;
                break;
            default:
                uses = 1;
        }
        return uses;
    }
}
