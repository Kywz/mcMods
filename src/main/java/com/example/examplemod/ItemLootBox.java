package com.example.examplemod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemLootBox extends Item {
    float[] weightRandomList = {0.8f, 2.0f, 0.7f, 0.2f};
    List<Item> itemList = new ArrayList<Item>();
    Random randomGenerator = new Random();
    public ItemLootBox() {
        this.setCreativeTab(ExampleMod.CTAB);
        this.setRegistryName("lootbox");
        this.setUnlocalizedName("lootbox");
        this.setMaxStackSize(1);
        itemList.add(0, Items.ARROW);
        itemList.add(1, Items.BED);
        itemList.add(2, Items.BOW);
        itemList.add(3, Items.BRICK);
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
        float weightSum = 0;
        float currentItem = 0;

        for (float currentWeight: weightRandomList) {
                weightSum += currentWeight;
        }

        float generatedValue = randomGenerator.nextFloat();
        weightSum *= generatedValue;

        for(int itemIter = 0; itemIter < itemList.size(); itemIter++) {
            currentItem += weightRandomList[itemIter];
            if (currentItem > weightSum) {
                worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE  ,(double) playerIn.getPosition().getX(),(double) playerIn.getPosition().getY(),(double) playerIn.getPosition().getZ(), 0.25,0.15,0);
                worldIn.playSound(playerIn, playerIn.getPosition(),SoundEvent.REGISTRY.getObject(new ResourceLocation("entity.experience_orb.pickup")), SoundCategory.PLAYERS, 1.0f, 1.0f);
                playerIn.getHeldItem(hand).shrink(1);
                playerIn.addItemStackToInventory(new ItemStack(itemList.get(itemIter)));
                break;
            }
        }
        return super.onItemRightClick(worldIn, playerIn, hand);
    }
}