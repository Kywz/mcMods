package com.mods.tfcharms.capabilities;

import com.mods.tfcharms.charms.ItemEntry;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.util.ArrayList;

public class TFCharms implements ITFCharms {
    private NBTTagCompound itemsToReturn = new NBTTagCompound();
    private NBTTagList ItemsToRestoreList = new NBTTagList();
    private ArrayList<ItemEntry> itemEntryList = new ArrayList<ItemEntry>();


    public ArrayList<ItemEntry> getItemEntry() { // NOT OK
        return this.itemEntryList;
    }

    public void setItemEntry(String type,  byte slotId, ItemStack itemStack) { //OK++
        this.itemEntryList.add(new ItemEntry (ItemEntry.TypeOfInventory.valueOf(type), slotId, itemStack));
    }

    public void setItemEntry(ArrayList<ItemEntry> itemEntry) {
        this.itemEntryList = itemEntry;
    }


    public void clearItemsToRestore() {
        itemEntryList.clear();
        this.itemEntryList = new ArrayList<ItemEntry>();
    }
}
