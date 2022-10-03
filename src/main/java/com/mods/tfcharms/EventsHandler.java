package com.mods.tfcharms;

import com.mods.tfcharms.capabilities.ITFCharms;
import com.mods.tfcharms.capabilities.TFCharmsProvider;
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
                            itemsToRestore.setItemsToRestore(player.inventory.mainInventory.get(i).serializeNBT());
                            player.inventory.mainInventory.get(i).setCount(0);
                        }

                        for (int i = 0; i<4; i++) {
                            itemsToRestore.setItemsToRestore(player.inventory.armorInventory.get(i).serializeNBT());
                            player.inventory.armorInventory.get(i).setCount(0);
                        }
                        break;

                    case 3:
                        itemToUse.shrink(1);

                        itemsToRestore.clearItemsToRestore();
                        for (int i = 0; i<9; i++) {
                            itemsToRestore.setItemsToRestore(player.inventory.mainInventory.get(i).serializeNBT());
                            player.inventory.mainInventory.get(i).setCount(0);
                        }
                        break;

                    case 4:
                        itemToUse.shrink(1);
                        itemsToRestore.clearItemsToRestore();
                        itemsToRestore.setItemsToRestore(player.getHeldItemMainhand().serializeNBT());
                        player.getHeldItemMainhand().setCount(0);
                        break;
                    default:
                        break;
                }
            }
            /*

            if (player.inventory.mainInventory.contains(new ItemStack(ItemsRegistry.HPCHARMTWO))) {
                    player.inventory.mainInventory.remove(ItemsRegistry.HPCHARMTWO);
                    player.setHealth(player.getMaxHealth());
                    player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 100, 1));
                    e.setCanceled(true);
                } else if (stack.getItem() == ItemsRegistry.HPCHARMONE) {
                    player.inventory.mainInventory.remove(ItemsRegistry.HPCHARMONE);
                    player.setHealth(player.getMaxHealth() / 10);
                    e.setCanceled(true);
                }

            * */
        }
    }


    @SubscribeEvent
    public static void onRespawn(PlayerEvent.Clone e) { //Проверка на нулл в e.getOriginal или что-то типа того
        //попробовать все через e.getOriginal без capabilities
        ITFCharms itemsToRestore = e.getOriginal().getCapability(TFCharmsProvider.ITEMS_TO_RETURN, null);
        if (itemsToRestore.getItemsToRestoreList().tagCount() == 13) {
            for (int i = 0; i < 9; i++) {
                e.getEntityPlayer().addItemStackToInventory(new ItemStack(itemsToRestore.getItemsToRestoreList().getCompoundTagAt(i)));
            }

            for (int i = 0; i < 4; i++) {
                e.getEntityPlayer().inventory.armorInventory.set(i, new ItemStack(itemsToRestore.getItemsToRestoreList().getCompoundTagAt(i+9)));

            }
            return;
        }
        else if (itemsToRestore.getItemsToRestoreList().tagCount() == 9) {
            for (int i = 0; i < 9; i++) {
                e.getEntityPlayer().addItemStackToInventory(new ItemStack(itemsToRestore.getItemsToRestoreList().getCompoundTagAt(i)));
            }
            return;
        }
        else {
            e.getEntityPlayer().addItemStackToInventory(new ItemStack(itemsToRestore.getItemsToRestore()));
        }
        return;
    }
}
