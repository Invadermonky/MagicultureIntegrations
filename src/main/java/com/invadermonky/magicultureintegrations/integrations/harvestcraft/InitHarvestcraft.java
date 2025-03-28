package com.invadermonky.magicultureintegrations.integrations.harvestcraft;

import com.invadermonky.magicultureintegrations.api.mods.IIntegrationModule;
import com.invadermonky.magicultureintegrations.config.MIConfigFixes;
import com.invadermonky.magicultureintegrations.util.IntegrationList;
import com.pam.harvestcraft.blocks.BlockRegistry;
import gnu.trove.set.hash.THashSet;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class InitHarvestcraft implements IIntegrationModule {
    private final IntegrationList integrations = new IntegrationList("Pam's Harvestcraft");

    private THashSet<Item> harvestcraftMachines = new THashSet<>(

    );

    @Override
    public void buildModIntegrations() {

    }

    @Override
    public IntegrationList getModIntegrations() {
        return this.integrations;
    }

    @Override
    public void preInit() {
        if(MIConfigFixes.harvestcraft.fix_machine_burntime)
            MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void postInit() {
        harvestcraftMachines.add(Item.getItemFromBlock(BlockRegistry.pamMarket));
        harvestcraftMachines.add(Item.getItemFromBlock(BlockRegistry.pamShippingbin));
        harvestcraftMachines.add(Item.getItemFromBlock(BlockRegistry.groundtrap));
        harvestcraftMachines.add(Item.getItemFromBlock(BlockRegistry.watertrap));
        harvestcraftMachines.add(Item.getItemFromBlock(BlockRegistry.grinder));
        harvestcraftMachines.add(Item.getItemFromBlock(BlockRegistry.apiary));
        harvestcraftMachines.add(Item.getItemFromBlock(BlockRegistry.beehive));
    }

    @SubscribeEvent
    public void onFurnaceFuelBurnTime(FurnaceFuelBurnTimeEvent event) {
        ItemStack stack = event.getItemStack();
        if(harvestcraftMachines.contains(stack.getItem())) {
            event.setBurnTime(0);
        }
    }
}
