package com.mods.tfcharms.charms;

import com.mods.tfcharms.Main;
import net.minecraft.item.Item;

public class HpCharmOne extends Item {
    public HpCharmOne() {
        this.setCreativeTab(Main.CTAB);
        this.setRegistryName("hpcharmone");
        this.setUnlocalizedName("hpcharmone");
        this.setMaxStackSize(1);
    }
}
