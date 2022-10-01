package com.example.examplemod.Charms;

import com.example.examplemod.ExampleMod;
import net.minecraft.item.Item;

public class HpCharmOne extends Item {
    public HpCharmOne() {
        this.setCreativeTab(ExampleMod.CTAB);
        this.setRegistryName("HpCharmOne");
        this.setUnlocalizedName("HpCharmOne");
        this.setMaxStackSize(1);
    }
}
