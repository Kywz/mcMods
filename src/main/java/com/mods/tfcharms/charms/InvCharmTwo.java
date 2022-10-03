package com.mods.tfcharms.charms;

import com.mods.tfcharms.Main;
import net.minecraft.item.Item;

public class InvCharmTwo extends Item {
    public InvCharmTwo() {
        this.setCreativeTab(Main.CTAB);
        this.setRegistryName("invcharmtwo");
        this.setUnlocalizedName("invcharmtwo");
        this.setMaxStackSize(1);
    }

}