package com.invadermonky.magicultureintegrations.config.modules;

import net.minecraftforge.common.config.Config;

public class ConfigMixins {
    @Config.Comment("Force disables Attained Drops mixins. Only use this if you are encountering crashes.")
    public boolean disableAttainedDropsMixins = false;
    @Config.Comment("Force disables Bewitchment mixins. Only use this if you are encountering crashes.")
    public boolean disableBewitchmentMixins = false;
    @Config.Comment("Force disables Blood Magic mixins. Only use this if you are encountering crashes.")
    public boolean disableBloodMagicMixins = false;
    @Config.Comment("Force disables Botania mixins. Only use this if you are encountering crashes.")
    public boolean disableBotaniaMixins = false;
    @Config.Comment("Force disables Cooking For Blockheads mixins. Only use this if you are encountering crashes.")
    public boolean disableCookingForBlockheadsMixins = false;
    @Config.Comment("Force disables Engineers Decor mixins. Only use this if you are encountering crashes.")
    public boolean disableEngineersDecorMixins = false;
    @Config.Comment("Force disables Future MC mixins. Only use this if you are encountering crashes.")
    public boolean disableFutureMcMixins = false;
    @Config.Comment("Force disables Immersive Engineering mixins. Only use this if you are encountering crashes.")
    public boolean disableImmersiveEngineeringMixins = false;
    @Config.Comment("Force disables Industrial Craft mixins. Only use this if you are encountering crashes.")
    public boolean disableIndustrialCraftMixins = false;
    @Config.Comment("Force disables Mystical Agriculture mixins. Only use this if you are encountering crashes.")
    public boolean disableMysticalAgricultureMixins = false;
    @Config.Comment("Force disables Natures Aura mixins. Only use this if you are encountering crashes.")
    public boolean disableNaturesAuraMixins = false;
    @Config.Comment("Force disables Rustic mixins. Only use this if you are encountering crashes.")
    public boolean disableRusticMixins = false;
    @Config.Comment("Force disables Thaumcraft mixins. Only use this if you are encountering crashes.")
    public boolean disableThaumcraftMixins = false;
    @Config.Comment("Force disables Thaumic Additions mixins. Only use this if you are encountering crashes.")
    public boolean disableThaumicAdditionsMixins = false;
    @Config.Comment("Force disables Tinker's Construct mixins. Only use this if you are encountering crashes.")
    public boolean disableTinkersConstructMixins = false;
    @Config.Comment("Force disables Twilight Forest mixins. Only use this if you are encountering crashes.")
    public boolean disableTwilightForestMixins = false;
}
