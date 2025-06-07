package com.invadermonky.magicultureintegrations.core.mixins.thaumcraft;

import com.invadermonky.magicultureintegrations.config.MIConfigIntegrations;
import net.darkhax.gamestages.GameStageHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import thaumcraft.client.lib.events.RenderEventHandler;

@Mixin(value = RenderEventHandler.class, remap = false)
public class RenderEventHandlerStageMixin {

    @Inject(
            method = "tooltipEvent(Lnet/minecraftforge/event/entity/player/ItemTooltipEvent;)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void hideStagedAspects(ItemTooltipEvent event, CallbackInfo ci) {
        EntityPlayer player = event.getEntityPlayer();
        String stageName = MIConfigIntegrations.thaumcraft.stageTooltipAspects;
        if (!stageName.isEmpty() && !GameStageHelper.hasStage(player, stageName)) {
            ci.cancel();
        }
    }
}
