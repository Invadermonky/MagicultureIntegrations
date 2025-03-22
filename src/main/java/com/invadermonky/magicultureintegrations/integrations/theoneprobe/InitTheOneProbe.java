package com.invadermonky.magicultureintegrations.integrations.theoneprobe;

import com.invadermonky.magicultureintegrations.api.IConfigurable;
import com.invadermonky.magicultureintegrations.api.mods.IIntegrationModule;
import com.invadermonky.magicultureintegrations.integrations.theoneprobe.compat.TOPOreStages;
import com.invadermonky.magicultureintegrations.integrations.theoneprobe.compat.TOPRedstonePaste;
import com.invadermonky.magicultureintegrations.util.IntegrationList;
import com.invadermonky.magicultureintegrations.util.LogHelper;
import com.invadermonky.magicultureintegrations.util.ModIds;
import mcjty.theoneprobe.api.ITheOneProbe;
import net.minecraftforge.fml.common.event.FMLInterModComms;

import java.util.ArrayList;
import java.util.function.Function;

public class InitTheOneProbe implements IIntegrationModule {
    public ArrayList<Function<ITheOneProbe, Void>> topModules = new ArrayList<>();
    private final IntegrationList integrations = new IntegrationList("The One Probe");

    @Override
    public void buildModIntegrations() {
        registerTOPModule(ModIds.ore_stages, TOPOreStages.class);
        registerTOPModule(ModIds.redstone_paste, TOPRedstonePaste.class);
    }

    @Override
    public IntegrationList getModIntegrations() {
        return null;
    }

    @Override
    public void preInit() {
        topModules.forEach(module -> {
            if(module instanceof IConfigurable) {
                if(((IConfigurable) module).isEnabled()) {
                    FMLInterModComms.sendFunctionMessage("theoneprobe", "getTheOneProbe", module.getClass().getName());
                }
            } else {
                FMLInterModComms.sendFunctionMessage("theoneprobe", "getTheOneProbe", module.getClass().getName());
            }
        });
    }

    private void registerTOPModule(ModIds mod, Class<? extends Function<ITheOneProbe, Void>> moduleClass) {
        final String modName = "The One Probe";
        try {
            if(mod.isLoaded) {
                topModules.add(moduleClass.newInstance());
                LogHelper.info("Loaded " + modName + " integration module: " + mod.modId);
            }
        } catch (Exception e) {
            LogHelper.error("Failed to load " + modName + " integration module: " + mod.modId);
        }
    }
}
