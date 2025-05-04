package com.invadermonky.magicultureintegrations.integrations.animania;

import com.animania.common.blocks.BlockSeeds;
import com.invadermonky.magicultureintegrations.api.mods.IntegrationModule;
import com.invadermonky.magicultureintegrations.integrations.animania.dispenser.SeedDispenserBehavior;
import com.invadermonky.magicultureintegrations.util.ModIds;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class InitAnimania extends IntegrationModule {
    public InitAnimania() {
        super("Animania");
    }

    @Override
    public void buildModIntegrations() {
    }

    @Override
    public void postInit() {
        if (ModIds.botania.isLoaded || ModIds.quark.isLoaded) {
            registerSeedDispenserBehavior(Items.WHEAT_SEEDS, Blocks.WHEAT, BlockSeeds.EnumType.WHEAT);
            registerSeedDispenserBehavior(Items.PUMPKIN_SEEDS, Blocks.PUMPKIN_STEM, BlockSeeds.EnumType.PUMPKIN);
            registerSeedDispenserBehavior(Items.MELON_SEEDS, Blocks.MELON_STEM, BlockSeeds.EnumType.MELON);
            registerSeedDispenserBehavior(Items.BEETROOT_SEEDS, Blocks.BEETROOTS, BlockSeeds.EnumType.BEETROOT);
        }
    }

    private void registerSeedDispenserBehavior(Item seedItem, Block cropBlock, BlockSeeds.EnumType seedType) {
        BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(seedItem, new SeedDispenserBehavior(cropBlock, seedType));
    }
}
