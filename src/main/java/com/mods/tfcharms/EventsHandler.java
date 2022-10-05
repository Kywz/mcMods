package com.mods.tfcharms;

import com.mods.tfcharms.capabilities.ITFCharms;
import com.mods.tfcharms.capabilities.TFCharmsProvider;
import com.mods.tfcharms.charms.ItemEntry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;

@Mod.EventBusSubscriber
public class EventsHandler {

    @SubscribeEvent
    public static void onDeath(LivingDeathEvent e) {

        if (e.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) e.getEntity();
            ItemStack itemToUse = null;
            short itemPriority = 10;

            for (ItemStack stack : player.inventory.mainInventory) {  //@TODO Try to use .contains() or something similar
                if ((stack.getItem() == ItemsRegistry.HPCHARMTWO) && itemPriority >= 1) {
                    itemToUse = stack;
                    itemPriority = 0;
                    break;
                }
                else if ((stack.getItem() == ItemsRegistry.HPCHARMONE) && itemPriority >= 2) {
                    itemToUse = stack;
                    itemPriority = 1;
                }
                else if ((stack.getItem() == ItemsRegistry.INVCHARMTHREE) && itemPriority >= 3) {
                    itemToUse = stack;
                    itemPriority = 2;
                }
                else if ((stack.getItem() == ItemsRegistry.INVCHARMTWO) && itemPriority >= 4) {
                    itemToUse = stack;
                    itemPriority = 3;
                }
                else if ((stack.getItem() == ItemsRegistry.INVCHARMONE) && itemPriority >= 5) {
                    itemToUse = stack;
                    itemPriority = 4;
                }
            }

            if (itemPriority != -1 && itemToUse != null) {
                ITFCharms itemsToRestore = player.getCapability(TFCharmsProvider.ITEMS_TO_RETURN, null);
                switch (itemPriority) {
                    case 0:
                        itemToUse.shrink(1);
                        player.setHealth(player.getMaxHealth());
                        player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 100, 1));
                        e.setCanceled(true);
                        break;

                    case 1:
                        itemToUse.shrink(1);
                        player.setHealth(player.getMaxHealth()/10);
                        e.setCanceled(true);
                        break;

                    case 2:
                        itemToUse.shrink(1);
                        itemsToRestore.clearItemsToRestore();
                        for (int i = 0; i<9; i++) {
                            if (player.inventory.mainInventory.get(i) != ItemStack.EMPTY) {
                                itemsToRestore.setItemEntry("INVENTORY", (byte) i, player.inventory.mainInventory.get(i));
                                player.inventory.mainInventory.get(i).setCount(0);
                            }
                        }

                        for (int i = 0; i<4; i++) {
                            if (player.inventory.armorInventory.get(i) != ItemStack.EMPTY) {
                                itemsToRestore.setItemEntry("ARMOR", (byte) i, player.inventory.armorInventory.get(i));
                                player.inventory.armorInventory.get(i).setCount(0);
                            }
                        }
                        break;

                    case 3:
                        itemToUse.shrink(1);
                        itemsToRestore.clearItemsToRestore();
                        for (int i = 0; i<9; i++) {
                            if (player.inventory.mainInventory.get(i) != ItemStack.EMPTY) {
                                itemsToRestore.setItemEntry("INVENTORY", (byte) i, player.inventory.mainInventory.get(i));
                                player.inventory.mainInventory.get(i).setCount(0);
                            }
                        }

                        break;

                    case 4:
                        itemToUse.shrink(1);
                        itemsToRestore.clearItemsToRestore();
                        if (player.getHeldItemMainhand() != ItemStack.EMPTY) {
                            itemsToRestore.setItemEntry("INVENTORY", (byte) 4, player.getHeldItemMainhand());
                        }
                        player.getHeldItemMainhand().setCount(0);
                        break;

                    default:
                        break;
                }
            }
        }
    }


    @SubscribeEvent
    public static void onRespawn(PlayerEvent.Clone e) {

        ITFCharms itemsToRestore = e.getOriginal().getCapability(TFCharmsProvider.ITEMS_TO_RETURN, null);

        ArrayList<ItemEntry> itemEntries = itemsToRestore.getItemEntry();

        for (ItemEntry itemEntry : itemEntries) {
           /* System.out.println("Item Entry: " + itemEntry);
            System.out.println("Type: " + itemEntry.getType().toString());
            System.out.println("Slot: " + itemEntry.getSlot());
            System.out.println("ItemStack: " + itemEntry.getItemStack());*/
            if (itemEntry.getType().toString() == "ARMOR") {
                e.getEntityPlayer().inventory.armorInventory.set(itemEntry.getSlot(), itemEntry.getItemStack());
            }

            else if (itemEntry.getType().toString() == "INVENTORY") {
                e.getEntityPlayer().inventory.setInventorySlotContents(itemEntry.getSlot(), itemEntry.getItemStack());
            }

        }

        return;
    }
}
