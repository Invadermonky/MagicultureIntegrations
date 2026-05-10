package com.invadermonky.magicultureintegrations.core.mixins.betterquesting;

import betterquesting.advancement.BqsAdvListener;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.lang.reflect.Field;

@Mixin(value = BqsAdvListener.class, remap = false)
public class BqsAdvListenerMixin {
    @Redirect
            (method = "<clinit>",
                    at = @At(
                            value = "INVOKE",
                            target = "Lnet/minecraftforge/fml/relauncher/ReflectionHelper;findField(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/reflect/Field;"
                    )
            )
    private static Field findPlayerFieldFix(Class<?> aClass, String fieldObfName, String fieldName) {
        return ObfuscationReflectionHelper.findField(PlayerAdvancements.class, "field_192762_j");
    }
}
