package com.mods.tfcharms.capabilities;

import com.mods.tfcharms.charms.ItemEntry;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.*;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class TFCharmsStorage implements Capability.IStorage<ITFCharms> {
    @Override
    public NBTBase writeNBT(Capability<ITFCharms> capability, ITFCharms instance, EnumFacing side)
    {
        final NBTTagCompound tag = new NBTTagCompound();

        for (ItemEntry itemEntry : instance.getItemEntry()) {
            tag.setTag("Item", itemEntry.getItemStack().serializeNBT());
            tag.setString("Type", itemEntry.getType().toString());
            tag.setByte("Slot", itemEntry.getSlot());
        }
        return tag;
    }

    @Override
    public void readNBT(Capability<ITFCharms> capability, ITFCharms instance, EnumFacing side, NBTBase nbt)
    {
        NBTTagCompound tag = (NBTTagCompound) nbt;
        for (ItemEntry itemEntry : instance.getItemEntry()){

            instance.setItemEntry(tag.getString("Type"),tag.getByte("Slot"), new ItemStack((NBTTagCompound) tag.getTag("Item")));
            /*tag.setTag("Item", itemEntry.getItemStack().serializeNBT());
            tag.setString("Type", itemEntry.getType().toString());
            tag.setByte("Slot", itemEntry.getSlot());*/
        }

        nbt = tag;

        //instance.setItemEntry(instance.getItemEntry());
    }
}
