package com.invadermonky.magicultureintegrations.api.mods;

import com.invadermonky.magicultureintegrations.util.IntegrationList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public interface IIntegrationModule extends IModIntegration {
    void buildModIntegrations();

    @Nullable
    IntegrationList getModIntegrations();

    @Nullable
    default List<IAddition> getAdditions() {
        return null;
    }

    @SideOnly(Side.CLIENT)
    default void registerCustomRenders() {}
}
