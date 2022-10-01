package com.example.examplemod;

import com.example.examplemod.Charms.*;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@GameRegistry.ObjectHolder("examplemod")
@Mod.EventBusSubscriber
public class ItemsRegistry {

    @GameRegistry.ObjectHolder("LOOTBOX")
    public static final Item LOOTBOX = null;
    @GameRegistry.ObjectHolder("INVCHARMONE")
    public static final Item INVCHARMONE = null;
    @GameRegistry.ObjectHolder("INVCHARMTWO")
    public static final Item INVCHARMTWO = null;
    @GameRegistry.ObjectHolder("INVCHARMTHREE")
    public static final Item INVCHARMTHREE = null;
    @GameRegistry.ObjectHolder("HPCHARMONE")
    public static final Item HPCHARMONE = null;
    @GameRegistry.ObjectHolder("HPCHARMTWO")
    public static final Item HPCHARMTWO = null;


    @SubscribeEvent
    public static void onRegistryItem(RegistryEvent.Register<Item> e) {
        e.getRegistry().registerAll(new ItemLootBox(), new InvCharmOne(), new InvCharmTwo(),
                new InvCharmThree(), new HpCharmOne(), new HpCharmTwo());
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onRegistryModel(ModelRegistryEvent e) {
    }

}