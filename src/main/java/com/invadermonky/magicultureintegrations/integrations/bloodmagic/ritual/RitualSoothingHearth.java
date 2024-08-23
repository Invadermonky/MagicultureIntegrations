package com.invadermonky.magicultureintegrations.integrations.bloodmagic.ritual;

import WayofTime.bloodmagic.BloodMagic;
import WayofTime.bloodmagic.ritual.*;
import com.invadermonky.magicultureintegrations.api.mods.IAddition;
import com.invadermonky.magicultureintegrations.api.mods.bloodmagic.BloodMagicUtils;
import com.invadermonky.magicultureintegrations.api.mods.simpledifficulty.SimpleDifficultyUtils;
import com.invadermonky.magicultureintegrations.api.mods.toughasnails.ToughAsNailsUtils;
import com.invadermonky.magicultureintegrations.config.ConfigHandlerMI;
import com.invadermonky.magicultureintegrations.util.ModIds;
import com.invadermonky.magicultureintegrations.util.StringHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.Consumer;

@RitualRegister(RitualSoothingHearth.NAME)
public class RitualSoothingHearth extends Ritual implements IAddition {
    public static final String NAME = "soothing_hearth";
    public static final String TEMP_CONTROL_RANGE = "tempControlRange";
    private final int refreshCost;
    private final int refreshInterval;

    public RitualSoothingHearth() {
        super(NAME, 0, ConfigHandlerMI.blood_magic.ritual_configs.soothing_hearth.activationCost, StringHelper.getTranslationKey(NAME, "ritual"));
        this.addBlockRange(TEMP_CONTROL_RANGE, new AreaDescriptor.Rectangle(new BlockPos(-25, 0, -25), new BlockPos(25, 30, 25)));
        this.setMaximumVolumeAndDistanceOfRange(TEMP_CONTROL_RANGE, 0, 200, 200);
        this.refreshCost = ConfigHandlerMI.blood_magic.ritual_configs.soothing_hearth.refreshCost;
        this.refreshInterval = ConfigHandlerMI.blood_magic.ritual_configs.soothing_hearth.refreshInterval;
    }


    @Override
    public void performRitual(IMasterRitualStone masterRitualStone) {
        World world = masterRitualStone.getWorldObj();
        int currentEssence = masterRitualStone.getOwnerNetwork().getCurrentEssence();
        BlockPos pos = masterRitualStone.getBlockPos();
        double x = pos.getX();
        double y = pos.getY();
        double z = pos.getZ();
        if(currentEssence < this.getRefreshCost()) {
            masterRitualStone.getOwnerNetwork().causeNausea();
        } else {
            int maxEffects = currentEssence / this.getRefreshCost();
            int totalEffects = 0;
            if(masterRitualStone.getCooldown() > 0) {

            }

            AreaDescriptor tempControlRange = masterRitualStone.getBlockRange(TEMP_CONTROL_RANGE);
            AxisAlignedBB tempControlBB = tempControlRange.getAABB(masterRitualStone.getBlockPos());
            List<EntityPlayer> players = world.getEntitiesWithinAABB(EntityPlayer.class, tempControlBB);


            for(EntityPlayer player : players) {
                if(player.isCreative())
                    continue;

                if(totalEffects >= maxEffects)
                    break;

                if(ModIds.simpledifficulty.isLoaded) {
                    SimpleDifficultyUtils.stabilizePlayerTemperature(player);
                }
                if(ModIds.tough_as_nails.isLoaded) {
                    ToughAsNailsUtils.stabilizePlayerTemperature(player);
                }
                totalEffects++;
            }

            masterRitualStone.getOwnerNetwork().syphon(masterRitualStone.ticket(this.getRefreshCost() * totalEffects));
        }
    }

    @Override
    public int getRefreshTime() {
        return this.refreshInterval;
    }

    @Override
    public int getRefreshCost() {
        return this.refreshCost;
    }

    @Override
    public void gatherComponents(Consumer<RitualComponent> components) {
        this.addParallelRunes(components, 1, 0, EnumRuneType.EARTH);
        this.addCornerRunes(components, 2, 0, EnumRuneType.WATER);
        this.addParallelRunes(components, 2, 1, EnumRuneType.AIR);
        this.addCornerRunes(components, 2, 1, EnumRuneType.FIRE);
    }

    @Override
    public Ritual getNewCopy() {
        return new RitualSoothingHearth();
    }

    @Override
    public void registerRecipe() {
        BloodMagicUtils.addGuideEntry("ritual", NAME);
    }

    @Override
    public boolean isEnabled() {
        return BloodMagic.RITUAL_MANAGER.getConfig().getBoolean(NAME, "rituals", true, "Enable the " + NAME + " ritual.");
    }
}
