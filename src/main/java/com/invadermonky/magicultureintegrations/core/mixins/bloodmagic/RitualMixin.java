package com.invadermonky.magicultureintegrations.core.mixins.bloodmagic;

import WayofTime.bloodmagic.ritual.Ritual;
import WayofTime.bloodmagic.tile.TileMasterRitualStone;
import net.minecraft.nbt.NBTTagCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = Ritual.class, remap = false)
public class RitualMixin {

    /**
     * @author Invadermonky
     * @reason Fixing Ritual settings resetting on chunk unload/world reload.<br><br>
     * <p>
     * The default ritual range is stored within the ritual {@link Ritual#modableRangeMap} field. If the NBTTagCompound
     * contains area data, the default settings are overwritten with the individual ritual configuration.<br><br>
     * <p>
     * Overwriting this method prevents the default Ritual range from being overwritten whenever a player sets the
     * range on their own ritual. This overwriting is necessary in order to allow rituals to modify the tile entity NBT
     * via overloading the {@link Ritual#readFromNBT(NBTTagCompound)} method.<br><br>
     * <p>
     * This fix is the second part of the fix found in {@link TileMasterRitualStoneMixin#customRitualReadFromNBT(Ritual, NBTTagCompound)}.
     */
    @Overwrite
    public void readFromNBT(NBTTagCompound tag) {

    }

    /**
     * @author Invadermonky
     * @reason Fixing Ritual settings resetting on chunk unload/world reload.<br><br>
     * <p>
     * Attempting to write the ritual ranges from the {@link Ritual} class will result in an empty "areas" tag being written
     * to the {@link TileMasterRitualStone} as the modable range map is not yet initialized prior to this method call. When
     * the empty area tag data is queried, it will cause the ritual to revert to its default settings.<br><br>
     * <p>
     * Overwriting this method prevents the ritual from writing data from the non-initialized ritual {@link Ritual#modableRangeMap},
     * resulting in the ritual falling back to its default values. This overwriting is necessary in order to allow rituals
     * to write from tile entity NBT via overloading the {@link Ritual#writeToNBT(NBTTagCompound)} method.<br><br>
     * <p>
     * This fix is the second part of the fix found in {@link TileMasterRitualStoneMixin#customRitualWriteToNBT(Ritual, NBTTagCompound)}.
     */
    @Overwrite
    public void writeToNBT(NBTTagCompound tag) {

    }
}
