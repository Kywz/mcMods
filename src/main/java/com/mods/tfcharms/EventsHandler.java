package com.mods.tfcharms;

import com.mods.tfcharms.capabilities.ITFCharms;
import com.mods.tfcharms.capabilities.TFCharmsProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

@Mod.EventBusSubscriber
public class EventsHandler {
    @SubscribeEvent
    public static void onDeathDamage(LivingHurtEvent e) {
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
                }

                else if (stack.getItem() == ForgeRegistries.ITEMS.getValue
                        (new ResourceLocation("tfcharms", "HPCHARMONE"))) {
                    player.setHealth(player.getMaxHealth()/10);
                    stack.shrink(1);
                    e.setAmount(0);
                    return;
                    }

                else if (stack.getItem() == ForgeRegistries.ITEMS.getValue
                        (new ResourceLocation("tfcharms", "INVCHARMTHREE"))) { // Bug with inv clearence before death, sometimes give doesnt work
                    stack.shrink(1);
                    ITFCharms itemsToRestore = player.getCapability(TFCharmsProvider.ITEMS_TO_RETURN, null);
                    itemsToRestore.clearItemsToRestore();
                    for (int i = 0; i<9; i++) {
                        itemsToRestore.setItemsToRestore(player.inventory.mainInventory.get(i).serializeNBT());
                        player.inventory.mainInventory.get(i).setCount(0);
                    }

                    for (int i = 0; i<4; i++) {
                        itemsToRestore.setItemsToRestore(player.inventory.armorInventory.get(i).serializeNBT());
                        player.inventory.armorInventory.get(i).setCount(0);
                    }
                    return;
                }

                else if (stack.getItem() == ForgeRegistries.ITEMS.getValue
                        (new ResourceLocation("tfcharms", "INVCHARMTWO"))) {
                    stack.shrink(1);
                    ITFCharms itemsToRestore = player.getCapability(TFCharmsProvider.ITEMS_TO_RETURN, null);
                    itemsToRestore.clearItemsToRestore();

                    for (int i = 0; i<9; i++) {
                        itemsToRestore.setItemsToRestore(player.inventory.mainInventory.get(i).serializeNBT());
                        player.inventory.mainInventory.get(i).setCount(0);
                    }

                    return;
                }

                else if (stack.getItem() == ForgeRegistries.ITEMS.getValue
                        (new ResourceLocation("tfcharms", "INVCHARMONE"))) {
                    stack.shrink(1);
                    ITFCharms itemsToRestore = player.getCapability(TFCharmsProvider.ITEMS_TO_RETURN, null);
                    itemsToRestore.clearItemsToRestore();
                    itemsToRestore.setItemsToRestore(player.getHeldItemMainhand().serializeNBT());
                    player.getHeldItemMainhand().setCount(0);
                    return;
                }
                }
            }
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
