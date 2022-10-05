package com.mods.tfcharms.capabilities;

import com.mods.tfcharms.charms.ItemEntry;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.util.ArrayList;

public interface ITFCharms {
    public ArrayList<ItemEntry> getItemEntry();
    public void setItemEntry(String type,  byte slotId, ItemStack itemStack);
    public void setItemEntry(ArrayList<ItemEntry> itemEntry);
    public void clearItemsToRestore();
}