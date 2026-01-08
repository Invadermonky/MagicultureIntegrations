package com.invadermonky.magicultureintegrations.core;

import com.google.common.collect.ImmutableMap;
import com.invadermonky.magicultureintegrations.config.MIConfigIntegrations;
import com.invadermonky.magicultureintegrations.util.StringHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.jetbrains.annotations.Nullable;
import zone.rong.mixinbooter.IEarlyMixinLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BooleanSupplier;

@IFMLLoadingPlugin.Name("MagicultureIntegrationsCore")
@IFMLLoadingPlugin.MCVersion(MinecraftForge.MC_VERSION)
public class EarlyMixinsMI implements IFMLLoadingPlugin, IEarlyMixinLoader {
    private static final Map<String, BooleanSupplier> mixinConfigs = ImmutableMap.copyOf(new HashMap<String, BooleanSupplier>() {{
        put(StringHelper.getVanillaMixinString("potion", "nutrition"), () -> MIConfigIntegrations.nutrition.autoFeeders.saturationPotion.enable);
    }});

    @Override
    public List<String> getMixinConfigs() {
        return new ArrayList<>(mixinConfigs.keySet());
    }

    @Override
    public boolean shouldMixinConfigQueue(String mixinConfig) {
        BooleanSupplier supplier = mixinConfigs.get(mixinConfig);
        return supplier == null || supplier.getAsBoolean();
    }

    @Override
    public String[] getASMTransformerClass() {
        return new String[0];
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public @Nullable String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {

    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
