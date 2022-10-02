package com.mods.tfcharms.capabilities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class TFCharms implements ITFCharms {
    private NBTTagCompound itemstoreturn = new NBTTagCompound();
    private NBTTagList ItemsToRestoreList = new NBTTagList();

    public NBTTagCompound getItemsToRestore() {
        return this.itemstoreturn;
    }
    public void setItemsToRestore(NBTTagCompound ItemsToReturn) {
        this.itemstoreturn = ItemsToReturn;
        this.ItemsToRestoreList.appendTag(ItemsToReturn);
    }

    public NBTTagList getItemsToRestoreList() {
        return this.ItemsToRestoreList;
    }

    public void clearItemsToRestore() {
        this.ItemsToRestoreList = new NBTTagList();
        this.itemstoreturn = new NBTTagCompound();
    }
}
