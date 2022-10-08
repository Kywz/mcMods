package com.mods.tfcharms;

import com.mods.tfcharms.charms.*;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@GameRegistry.ObjectHolder(Main.MODID)
@Mod.EventBusSubscriber(modid = Main.MODID)
public class ItemsRegistry {

    @GameRegistry.ObjectHolder("invcharmone")
    public static final Item INVCHARMONE = null;
    @GameRegistry.ObjectHolder("invcharmtwo")
    public static final Item INVCHARMTWO = null;
    @GameRegistry.ObjectHolder("invcharmthree")
    public static final Item INVCHARMTHREE = null;
    @GameRegistry.ObjectHolder("invcharmfour")
    public static final Item INVCHARMFOUR = null;
    @GameRegistry.ObjectHolder("hpcharmone")
    public static final Item HPCHARMONE = null;
    @GameRegistry.ObjectHolder("hpcharmtwo")
    public static final Item HPCHARMTWO = null;



    @SubscribeEvent
    public static void onRegistryItem(RegistryEvent.Register<Item> e) {
        e.getRegistry().registerAll(new InvCharmOne(), new InvCharmTwo(),
                new InvCharmThree(), new InvCharmFour(), new HpCharmOne(), new HpCharmTwo());

    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onRegistryModel(ModelRegistryEvent e) {
        registryModel(HPCHARMONE);
        registryModel(HPCHARMTWO);
        registryModel(INVCHARMONE);
        registryModel(INVCHARMTWO);
        registryModel(INVCHARMTHREE);
        registryModel(INVCHARMFOUR);

    }

    @SideOnly(Side.CLIENT)
    private static void registryModel(Item item) {
        final ResourceLocation regName = item.getRegistryName();
        final ModelResourceLocation mrl = new ModelResourceLocation(regName, "inventory");
        ModelBakery.registerItemVariants(item,mrl);
        ModelLoader.setCustomModelResourceLocation(item, 0, mrl);
    }
}