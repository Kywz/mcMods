package com.example.examplemod.capabilities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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

/*public class TFCharms implements ITFCharms {
    private NBTTagList itemstoreturn = new NBTTagList();

    public NBTTagList getItemsToRestore() {
        return this.itemstoreturn;
    }
    public void setItemsToRestore(NBTTagCompound ItemsToReturn) {
        this.itemstoreturn.appendTag(ItemsToReturn);
    }
}*/
