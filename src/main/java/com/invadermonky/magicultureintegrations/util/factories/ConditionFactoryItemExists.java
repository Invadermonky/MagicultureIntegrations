package com.invadermonky.magicultureintegrations.util.factories;

import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.IConditionFactory;
import net.minecraftforge.common.crafting.JsonContext;

import java.util.function.BooleanSupplier;

public class ConditionFactoryItemExists implements IConditionFactory {
    @Override
    public BooleanSupplier parse(JsonContext context, JsonObject json) {
        String itemName = json.get("item").getAsString();
        return () -> Item.REGISTRY.containsKey(new ResourceLocation(itemName));
    }
}
