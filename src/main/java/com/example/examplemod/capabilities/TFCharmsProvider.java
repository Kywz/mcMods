package com.example.examplemod.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class TFCharmsProvider implements ICapabilitySerializable<NBTBase> {

    @CapabilityInject(ITFCharms.class)
    public static final Capability<ITFCharms> ITEMS_TO_RETURN = null;

    private ITFCharms instance = ITEMS_TO_RETURN.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        return capability == ITEMS_TO_RETURN;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        return capability == ITEMS_TO_RETURN ? ITEMS_TO_RETURN.<T> cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT()
    {
        return ITEMS_TO_RETURN.getStorage().writeNBT(ITEMS_TO_RETURN, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt)
    {
        ITEMS_TO_RETURN.getStorage().readNBT(ITEMS_TO_RETURN, this.instance, null, nbt);
    }
}
