package com.example.examplemod.capabilities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TFCharms implements ITFCharms {
    private NBTTagCompound itemstoreturn = new NBTTagCompound();

    public NBTTagCompound getItemsToRestore() {
        return this.itemstoreturn;
    }
    public void setItemsToRestore(NBTTagCompound ItemsToReturn) {
        this.itemstoreturn = ItemsToReturn;
    }
}
