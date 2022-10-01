package com.example.examplemod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber
public class EventsHandler {
    @SubscribeEvent
    public static void onDeath(LivingHurtEvent e) {
        if (e.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) e.getEntity();
            if (player.getHealth() - e.getAmount() <= 0) {
            for (ItemStack stack : player.inventory.mainInventory) { //Look through Player inventory
                if (stack.getItem() == ForgeRegistries.ITEMS.getValue //if Item is HPCHARMTWO from mod tfcharms, then if == true
                        (new ResourceLocation("tfcharms", "HPCHARMTWO"))) {
                    player.setHealth(player.getMaxHealth());
                    player.addPotionEffect(new PotionEffect(Potion.getPotionById(10), 100,1));
                    stack.shrink(1);
                    e.setAmount(0);
                    return;
                } else if (stack.getItem() == ForgeRegistries.ITEMS.getValue
                        (new ResourceLocation("tfcharms", "HPCHARMONE"))) {
                    System.out.printf(player.getHealth() + "||||" + player.getMaxHealth());
                    player.setHealth(player.getMaxHealth()/10);
                    stack.shrink(1);
                    e.setAmount(0);
                    return;
                    }
                }
            }
        }
    }
}


/*
if (stack.getItem() == ForgeRegistries.ITEMS.getValue //if Item is HPCHARMTWO from mod tfcharms, then if == true
                        (new ResourceLocation("tfcharms", "HPCHARMTWO"))) {
                    System.out.println("DEATH HCT");
                    break;
                } else if (stack.getItem() == ForgeRegistries.ITEMS.getValue
                        (new ResourceLocation("tfcharms", "HPCHARMONE"))) {
                    player.setHealth(2);
                    System.out.println("DEATH HCO");
                    break;
                } else if (stack.getItem() == ForgeRegistries.ITEMS.getValue
                        (new ResourceLocation("tfcharms", "INVCHARMTHREE"))) {
                    System.out.println("DEATH ICTh");
                    break;
                } else if (stack.getItem() == ForgeRegistries.ITEMS.getValue
                        (new ResourceLocation("tfcharms", "INVCHARMTWO"))) {
                    System.out.println("DEATH ICTw");
                    break;
                } else if (stack.getItem() == ForgeRegistries.ITEMS.getValue
                        (new ResourceLocation("tfcharms", "INVCHARMONE"))) {
                    System.out.println("DEATH ICO");
                    break;
                }
*/