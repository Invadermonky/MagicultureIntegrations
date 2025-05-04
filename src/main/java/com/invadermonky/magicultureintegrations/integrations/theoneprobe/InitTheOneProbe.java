package com.invadermonky.magicultureintegrations.integrations.theoneprobe;

import com.invadermonky.magicultureintegrations.api.IConfigurable;
import com.invadermonky.magicultureintegrations.api.mods.IntegrationModule;
import com.invadermonky.magicultureintegrations.integrations.theoneprobe.compat.TOPOreStages;
import com.invadermonky.magicultureintegrations.integrations.theoneprobe.compat.TOPRedstonePaste;
import com.invadermonky.magicultureintegrations.util.LogHelper;
import com.invadermonky.magicultureintegrations.util.ModIds;
import mcjty.theoneprobe.api.ITheOneProbe;
import net.minecraftforge.fml.common.event.FMLInterModComms;

import java.util.ArrayList;
import java.util.function.Function;

public class InitTheOneProbe extends IntegrationModule {
    public ArrayList<Function<ITheOneProbe, Void>> topModules = new ArrayList<>();

    public InitTheOneProbe() {
        super("The One Probe");
    }

    @Override
    public void buildModIntegrations() {
        registerTOPModule(ModIds.ore_stages, TOPOreStages.class);
        registerTOPModule(ModIds.redstone_paste, TOPRedstonePaste.class);
    }

    @Override
    public void preInit() {
        topModules.forEach(module -> {
            if (module instanceof IConfigurable) {
                if (((IConfigurable) module).isEnabled()) {
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
            if (mod.isLoaded) {
                topModules.add(moduleClass.newInstance());
                LogHelper.info("Loaded " + modName + " integration module: " + mod.modId);
            }
        } catch (Exception e) {
            LogHelper.error("Failed to load " + modName + " integration module: " + mod.modId);
        }
    }
}
