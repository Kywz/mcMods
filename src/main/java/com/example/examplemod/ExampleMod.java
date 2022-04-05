package com.example.examplemod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = ExampleMod.MODID, name = ExampleMod.NAME, version = ExampleMod.VERSION)
public class ExampleMod
{
    public class EventsHandler {
        @SubscribeEvent
        public void onBlockBreak(BlockEvent.BreakEvent event){

        }

        @SubscribeEvent
        public void onJoin(EntityJoinWorldEvent e) {
            if (e.getEntity() instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) e.getEntity();
                player.sendMessage(new TextComponentString("Hello, %p!".replace("%p", player.getName())));
            }
        }
    }
    public static final String MODID = "examplemod";
    public static final String NAME = "Example Mod";
    public static final String VERSION = "1.0";

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        //logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

}
