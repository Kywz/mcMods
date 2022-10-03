package com.mods.tfcharms.charms;

import com.mods.tfcharms.Main;
import net.minecraft.item.Item;

public class InvCharmOne extends Item {
    public InvCharmOne() {
        this.setCreativeTab(Main.CTAB);
        this.setRegistryName("invcharmone");
        this.setUnlocalizedName("invcharmone");
        this.setMaxStackSize(1);
    }

}
