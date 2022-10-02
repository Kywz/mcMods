package com.mods.tfcharms.capabilities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public interface ITFCharms {
    public NBTTagCompound getItemsToRestore();
    public void setItemsToRestore(NBTTagCompound ItemsToReturn);
    public NBTTagList getItemsToRestoreList();
    public void clearItemsToRestore();
}


/*public interface ITFCharms {
    public NBTTagList getItemsToRestore();

    public void setItemsToRestore(NBTTagCompound ItemsToReturn);
}*/