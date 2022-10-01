package com.example.examplemod.capabilities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public interface ITFCharms {
    public NBTTagCompound getItemsToRestore();

    public void setItemsToRestore(NBTTagCompound ItemsToReturn);
}
