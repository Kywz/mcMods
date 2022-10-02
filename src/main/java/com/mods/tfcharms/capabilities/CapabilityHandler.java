package com.mods.tfcharms.capabilities;

import com.mods.tfcharms.Main;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class CapabilityHandler
{
    public static final ResourceLocation ITEMS_TO_RETURN = new ResourceLocation(Main.MODID, "itemstoreturn");

    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent<Entity> event)
    {
        if (!(event.getObject() instanceof EntityPlayer)) return;
        event.addCapability(ITEMS_TO_RETURN, new TFCharmsProvider());
    }
}
