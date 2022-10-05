package com.mods.tfcharms.charms;

import net.minecraft.item.ItemStack;



public final class ItemEntry {

    public enum TypeOfInventory {ARMOR,INVENTORY};
    private TypeOfInventory typeOfInventory;
    private byte slotId;
    private ItemStack itemStack;

    public ItemEntry(TypeOfInventory typeOfInventory, byte slotId, ItemStack itemStack) { //OK
        this.typeOfInventory = typeOfInventory;
        this.slotId = slotId;
        this.itemStack = itemStack;
    }


    public TypeOfInventory getType() {
        return this.typeOfInventory;
    }

    public byte getSlot() {
        return this.slotId;
    }

    public ItemStack getItemStack() {
        return this.itemStack;
    }



}

