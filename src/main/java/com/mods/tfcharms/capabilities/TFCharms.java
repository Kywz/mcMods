package com.mods.tfcharms.capabilities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class TFCharms implements ITFCharms {
    private NBTTagCompound itemsToReturn = new NBTTagCompound();
    private NBTTagList ItemsToRestoreList = new NBTTagList();

    public NBTTagCompound getItemsToRestore() {
        return this.itemsToReturn;
    }
    public void setItemsToRestore(NBTTagCompound ItemsToReturn) {
        this.itemsToReturn = ItemsToReturn;
        this.ItemsToRestoreList.appendTag(ItemsToReturn);
    }

    public NBTTagList getItemsToRestoreList() {
        return this.ItemsToRestoreList;
    }

    public void clearItemsToRestore() {
        this.ItemsToRestoreList = new NBTTagList();
        this.itemsToReturn = new NBTTagCompound();
    }
}
