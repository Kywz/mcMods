package com.mods.tfcharms;

import com.mods.tfcharms.capabilities.CapabilityHandler;
import com.mods.tfcharms.capabilities.ITFCharms;
import com.mods.tfcharms.capabilities.TFCharms;
import com.mods.tfcharms.capabilities.TFCharmsStorage;
import com.mods.tfcharms.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = Main.MODID, name = Main.NAME, version = Main.VERSION)
public class Main
{
    public static final String MODID = "tfcharms";
    public static final String NAME = "TF Charms";
    public static final String VERSION = "1.0";

    private static Logger logger;

    @Mod.Instance(Main.MODID)
    public static Main instance;

    @SidedProxy(clientSide = "com.mods.tfcharms.proxy.ClientProxy", serverSide = "com.mods.tfcharms.proxy.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        this.logger = event.getModLog();

        CapabilityManager.INSTANCE.register(ITFCharms.class, new TFCharmsStorage(), TFCharms::new);
        MinecraftForge.EVENT_BUS.register(new CapabilityHandler());

        MinecraftForge.EVENT_BUS.register(new EventsHandler());

        this.proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        this.proxy.init(event);
    }

    public static final CreativeTabs CTAB = new CreativeTabs("examplemod") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ItemsRegistry.HPCHARMONE);
        }
    };

}
