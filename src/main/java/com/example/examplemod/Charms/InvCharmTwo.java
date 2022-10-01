package com.example.examplemod.Charms;

import com.example.examplemod.ExampleMod;
import net.minecraft.item.Item;

public class InvCharmTwo extends Item {
    public InvCharmTwo() {
        this.setCreativeTab(ExampleMod.CTAB);
        this.setRegistryName("InvCharmTwo");
        this.setUnlocalizedName("InvCharmTwo");
        this.setMaxStackSize(1);
    }

}