package com.mods.tfcharms.capabilities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public interface ITFCharms {
    public NBTTagCompound getItemsToRestore();
    public void setItemsToRestore(NBTTagCompound ItemsToReturn);
    public NBTTagList getItemsToRestoreList();
    public void clearItemsToRestore();
}