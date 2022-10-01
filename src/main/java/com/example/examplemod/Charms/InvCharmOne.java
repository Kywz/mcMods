package com.example.examplemod.Charms;

import com.example.examplemod.ExampleMod;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;

public class InvCharmOne extends Item {
    public InvCharmOne() {
        this.setCreativeTab(ExampleMod.CTAB);
        this.setRegistryName("InvCharmOne");
        this.setUnlocalizedName("InvCharmOne");
        this.setMaxStackSize(1);
    }

}
