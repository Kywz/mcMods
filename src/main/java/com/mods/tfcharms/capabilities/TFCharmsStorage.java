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
        NBTTagList tagList = new NBTTagList();

        for (ItemEntry itemEntry : instance.getItemEntry()) {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setTag("Item", itemEntry.getItemStack().serializeNBT());
            tag.setString("Type", itemEntry.getType().toString());
            tag.setByte("Slot", itemEntry.getSlot());
            tagList.appendTag(tag);
        }
        return tagList;
    }

    @Override
    public void readNBT(Capability<ITFCharms> capability, ITFCharms instance, EnumFacing side, NBTBase nbt)
    {

        NBTTagList tagList = (NBTTagList) nbt;
        NBTTagCompound tag;

        for (int i = 0; i < tagList.tagCount(); i++) {
            tag = tagList.getCompoundTagAt(i);
            instance.setItemEntry(tag.getString("Type"), tag.getByte("Slot"), new ItemStack((NBTTagCompound) tag.getTag("Item")));
        }
    }
}
