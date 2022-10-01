package com.example.examplemod.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class TFCharmsStorage implements Capability.IStorage<ITFCharms> {
    @Override
    public NBTBase writeNBT(Capability<ITFCharms> capability, ITFCharms instance, EnumFacing side)
    {
        final NBTTagCompound tag = new NBTTagCompound();
        tag.setTag("Items", instance.getItemsToRestore());
        return tag;
    }

    @Override
    public void readNBT(Capability<ITFCharms> capability, ITFCharms instance, EnumFacing side, NBTBase nbt)
    {
        instance.setItemsToRestore(instance.getItemsToRestore());
    }
}
