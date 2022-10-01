package com.example.examplemod.capabilities;

import net.minecraft.nbt.*;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class TFCharmsStorage implements Capability.IStorage<ITFCharms> {
    @Override
    public NBTBase writeNBT(Capability<ITFCharms> capability, ITFCharms instance, EnumFacing side)
    {
        final NBTTagCompound tag = new NBTTagCompound();
        tag.setTag("Items", instance.getItemsToRestore());
        return tag;

        /*final NBTTagCompound tag = new NBTTagCompound();
        final NBTTagList tagList = new NBTTagList();
        tag.setTag("Items", instance.getItemsToRestore());
        tagList.appendTag(tag);
        return tagList;*/
    }

    @Override
    public void readNBT(Capability<ITFCharms> capability, ITFCharms instance, EnumFacing side, NBTBase nbt)
    {
        instance.setItemsToRestore(instance.getItemsToRestore());

    }
}
