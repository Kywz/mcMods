package com.mods.tfcharms.proxy;

import com.mods.tfcharms.capabilities.*;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class CommonProxy {



    @EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        //CapabilityManager.INSTANCE.register(ITFCharms.class, new TFCharmsStorage(), TFCharms::new);
    }

    public void preInit(FMLPreInitializationEvent event) {
        //MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
    }

    public void init(FMLInitializationEvent event) {

    }
}
