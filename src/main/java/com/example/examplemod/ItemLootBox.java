package com.example.examplemod;

import net.minecraft.client.tutorial.Tutorial;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;
import sun.security.util.Length;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemLootBox extends Item {
    int[] weightRandomList = {10, 24, 55, 87, 100};
    List<Item> itemList = new ArrayList<Item>();
    Random randomGenerator = new Random();
        //itemList.add(0, Items.ARROW);
        //itemList.add(1, Items.BED);
        //itemList.add(2, Items.BOW);
        //itemList.add(3, Items.BRICK);
    public ItemLootBox() {
        this.setCreativeTab(ExampleMod.CTAB);
        this.setRegistryName("lootbox");
        this.setUnlocalizedName("lootbox");
        //float[] weightRandomList = {10, 24, 55, 87};
        //List<Item> itemList = new ArrayList<Item>();
        itemList.add(0, Items.ARROW);
        itemList.add(1, Items.BED);
        itemList.add(2, Items.BOW);
        itemList.add(3, Items.BRICK);

    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {


        int generatedValue = randomGenerator.nextInt(weightRandomList[(itemList.size()-1)]);
        System.out.println(generatedValue);
        for(int itemIter = 1; itemIter <= itemList.size()-1; itemIter++) {
            if(weightRandomList[itemIter] <= generatedValue && generatedValue <= weightRandomList[itemIter+1]) {
                System.out.println(generatedValue);
                //playerIn.spawnRunningParticles();
                //playerIn.getPosition()
                playerIn.getActiveItemStack().setCount(5);
                break;
            }
        }
        //playerIn.jump();
        return super.onItemRightClick(worldIn, playerIn, hand);
    }
}