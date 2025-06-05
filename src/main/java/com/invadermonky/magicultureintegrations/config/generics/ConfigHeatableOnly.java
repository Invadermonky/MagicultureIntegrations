package com.invadermonky.magicultureintegrations.config.generics;

import com.bewitchment.common.block.tile.entity.TileEntityWitchesOven;
import com.blakebr0.mysticalagriculture.tileentity.furnace.TileEssenceFurnace;
import com.invadermonky.magicultureintegrations.api.tile.HeatableUtils;
import com.invadermonky.magicultureintegrations.util.ModIds;
import net.blay09.mods.cookingforblockheads.tile.TileOven;
import net.minecraftforge.common.config.Config;
import rustic.common.tileentity.TileEntityCondenserBase;
import slimeknights.tconstruct.smeltery.tileentity.TileHeatingStructure;
import thaumcraft.common.tiles.essentia.TileSmelter;
import thedarkcolour.futuremc.tile.TileFurnaceAdvanced;
import wile.engineersdecor.blocks.BlockDecorFurnace;

public class ConfigHeatableOnly {
    @Config.RequiresMcRestart
    @Config.Name("Bewitchment Witches' Oven")
    public boolean witches_oven = true;
    @Config.RequiresMcRestart
    @Config.Name("Cooking for Blockheads Oven")
    public boolean cfb_oven = true;
    @Config.RequiresMcRestart
    @Config.Name("Engineer's Decor Laboratory Furnace")
    public boolean ed_laboratory_furnace = true;
    @Config.RequiresMcRestart
    @Config.Name("Future MC Blast Furnace/Smoker")
    public boolean fmc_advanced_furnaces = true;
    @Config.RequiresMcRestart
    @Config.Name("Mystical Agriculture Essence Furnace")
    public boolean ma_essence_furnaces = true;
    @Config.RequiresMcRestart
    @Config.Name("Rustic Basic/Advanced Alchemic Condenser")
    public boolean rustic_condenser = true;
    @Config.RequiresMcRestart
    @Config.Name("Thaumcraft Essentia Smelters")
    public boolean tc_essentia_smelter = true;
    @Config.RequiresMcRestart
    @Config.Name("Thaumic Additions Essentia Smelters")
    public boolean ta_essentia_smelter = true;

    public void registerHeatableBlacklists(Class<?> heatProvider) {
        if (ModIds.bewitchment.isLoaded && !this.witches_oven)
            HeatableUtils.blacklistHeatable(heatProvider, TileEntityWitchesOven.class);
        if (ModIds.cooking_for_blockheads.isLoaded && !this.cfb_oven)
            HeatableUtils.blacklistHeatable(heatProvider, TileOven.class);
        if (ModIds.engineers_decor.isLoaded && !this.ed_laboratory_furnace)
            HeatableUtils.blacklistHeatable(heatProvider, BlockDecorFurnace.BTileEntity.class);
        if (ModIds.futuremc.isLoaded && !this.fmc_advanced_furnaces)
            HeatableUtils.blacklistHeatable(heatProvider, TileFurnaceAdvanced.class);
        if (ModIds.mystical_agriculture.isLoaded && !this.ma_essence_furnaces)
            HeatableUtils.blacklistHeatable(heatProvider, TileEssenceFurnace.class);
        if (ModIds.rustic.isLoaded && !this.rustic_condenser)
            HeatableUtils.blacklistHeatable(heatProvider, TileEntityCondenserBase.class);
        if (ModIds.thaumcraft.isLoaded && !this.tc_essentia_smelter)
            HeatableUtils.blacklistHeatable(heatProvider, TileSmelter.class);
        if (ModIds.thaumic_additions.isLoaded && !this.ta_essentia_smelter)
            HeatableUtils.blacklistHeatable(heatProvider, TileHeatingStructure.class);
    }
}
