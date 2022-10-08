package com.mods.tfcharms.gui;

import com.mods.tfcharms.ItemsRegistry;
import com.mods.tfcharms.Main;
import com.mods.tfcharms.capabilities.ITFCharms;
import com.mods.tfcharms.capabilities.TFCharmsProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber
public class CharmsGui extends Gui {

    @SubscribeEvent
    public void guiDrawCharms (TickEvent.PlayerTickEvent e) {
        EntityPlayer player = e.player;
        ItemStack itemToUse = null;
        short itemPriority = 10;

        for (ItemStack stack : player.inventory.mainInventory) {
            if ((stack.getItem() == ItemsRegistry.HPCHARMTWO) && itemPriority >= 1) {
                itemToUse = stack;
                itemPriority = 0;
                break;
            }
            else if ((stack.getItem() == ItemsRegistry.HPCHARMONE) && itemPriority >= 2) {
                itemToUse = stack;
                itemPriority = 1;
            }
            else if ((stack.getItem() == ItemsRegistry.INVCHARMFOUR) && itemPriority >= 3) {
                itemToUse = stack;
                itemPriority = 2;
            }
            else if ((stack.getItem() == ItemsRegistry.INVCHARMTHREE) && itemPriority >= 4) {
                itemToUse = stack;
                itemPriority = 3;
            }
            else if ((stack.getItem() == ItemsRegistry.INVCHARMTWO) && itemPriority >= 5) {
                itemToUse = stack;
                itemPriority = 4;
            }
            else if ((stack.getItem() == ItemsRegistry.INVCHARMONE) && itemPriority >= 6) {
                itemToUse = stack;
                itemPriority = 5;
            }
        }

        if (itemPriority != -1 && itemToUse != null) {
            ITFCharms itemsToRestore = player.getCapability(TFCharmsProvider.ITEMS_TO_RETURN, null);
            switch (itemPriority) {
                case 0:
                    System.out.println("\nTick: Case 01");
                    //Minecraft mc = Minecraft.getMinecraft();
                    //mc.renderEngine.bindTexture(new ResourceLocation(Main.MODID, "textures/items/hpcharmtwo.png"));
                   //this.drawTexturedModalRect(0,0,0,0,16,16);
                    ////this.drawVerticalLine(400, 860,230,0);
                    break;

                case 1:

                    System.out.println("\nTick: Case 02");
                    break;

                case 2:

                    System.out.println("\nTick: Case 03");
                    break;

                case 4:

                    System.out.println("\nTick: Case 04");
                    break;

                case 5:

                    System.out.println("\nTick: Case 05");
                    break;

                default:

                    System.out.println("\nTick: Case 00");
                    return;
            }
        }
    }
}
