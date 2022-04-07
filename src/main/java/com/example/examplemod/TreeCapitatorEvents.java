package com.example.examplemod;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemTool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashSet;
import java.util.Iterator;

@Mod.EventBusSubscriber
public class TreeCapitatorEvents {

    @SubscribeEvent
        public static void onBlockBreaking(PlayerEvent.BreakSpeed event){
            event.getEntityPlayer().sendMessage(new TextComponentString(String.valueOf(event.getOriginalSpeed())));
                if (event.getEntityPlayer().getHeldItemMainhand().getItem() instanceof ItemAxe) {
                    event.getEntityPlayer().sendMessage(new TextComponentString("Axe have been used"));
                    if (event.getEntityPlayer().isSneaking() == true) {
                        event.getEntityPlayer().sendMessage(new TextComponentString("Player is sneaking"));
                        if ( event.getEntityPlayer().getEntityWorld().getBlockState(event.getPos()).getBlock() == Blocks.LOG ||  event.getEntityPlayer().getEntityWorld().getBlockState(event.getPos()).getBlock() == Blocks.LOG2) {

                            HashSet<BlockPos> blocksToBeBroken = new HashSet<BlockPos>();
                            blocksToBeBroken.add(event.getPos());
                            blocksToBeBroken = woodBreakingRecursionCount(event.getPos(),event.getEntityPlayer().getEntityWorld(),event.getState(),blocksToBeBroken);

                            event.setNewSpeed(event.getOriginalSpeed() / blocksToBeBroken.size());
                            event.getEntityPlayer().sendMessage(new TextComponentString("HashSet Size:"+blocksToBeBroken.size()));
                            event.getEntityPlayer().sendMessage(new TextComponentString("Current speed "+event.getNewSpeed()));

                            event.getEntityPlayer().sendMessage(new TextComponentString("Current result "+event.getResult()));

                            //event.

                            //woodBreaking(event.getPos(), event.getEntityPlayer().getEntityWorld(), event.getState(), event.getEntityPlayer(), blocksToBeBroken);
                            //event.getEntityPlayer().sendMessage(new TextComponentString("Block broken"));
                    }
                }
            }
        }


        @SubscribeEvent
        public static void onBlockBreak(BlockEvent.BreakEvent event) {
            //event.getEntityPlayer().sendMessage(new TextComponentString(String.valueOf(event.getOriginalSpeed())));
            if (event.getPlayer().getHeldItemMainhand().getItem() instanceof ItemAxe) {
                event.getPlayer().sendMessage(new TextComponentString("Axe have been used"));
                if (event.getPlayer().isSneaking() == true) {
                    event.getPlayer().sendMessage(new TextComponentString("Player is sneaking"));
                    if ( event.getWorld().getBlockState(event.getPos()).getBlock() == Blocks.LOG ||  event.getWorld().getBlockState(event.getPos()).getBlock() == Blocks.LOG2) {

                        //HashSet<BlockPos> blocksToBeBroken = new HashSet<BlockPos>();
                        //blocksToBeBroken.add(event.getPos());
                       //blocksToBeBroken = woodBreakingRecursionCount(event.getPos(),event.getEntityPlayer().getEntityWorld(),event.getState(),blocksToBeBroken);

                        //event.setNewSpeed(event.getOriginalSpeed() / blocksToBeBroken.size());
                        //event.getEntityPlayer().sendMessage(new TextComponentString("HashSet Size:"+blocksToBeBroken.size()));
                        //event.getEntityPlayer().sendMessage(new TextComponentString("Current speed"+event.getNewSpeed()));

                        HashSet<BlockPos> blocksToBeBroken = new HashSet<BlockPos>();
                        blocksToBeBroken.add(event.getPos());
                        //woodBreaking(event.getPos(), event.getWorld(), event.getState(), event.getPlayer(), woodBreakingRecursionCount(event.getPos(),event.getWorld(),event.getState(),blocksToBeBroken));
                        woodBreaking(event.getPos(), event.getWorld(), event.getState(), event.getPlayer(), 0);
                        event.getPlayer().sendMessage(new TextComponentString("Block broken"));

                    }
                }
            }
        }


    private static HashSet woodBreakingRecursionCount(BlockPos pos, World world, IBlockState state, HashSet<BlockPos> knownPos) {
        for (int y = 0; y <= 1; y++) {
            for (int x = -1; x <= 1; x++) {
                for (int z = -1; z <= 1; z++) {
                    if ((world.getBlockState(pos.add(x,y,z)).getBlock() == Blocks.LOG || world.getBlockState(pos.add(x,y,z)).getBlock() == Blocks.LOG2) && pos.add(x,y,z) != pos) {
                        if (knownPos.size() == 30) {
                            return knownPos;
                        }
                        if(!(knownPos.contains(pos.add(x,y,z)))) {
                            knownPos.add(pos.add(x,y,z));
                            knownPos = woodBreakingRecursionCount(pos.add(x,y,z), world, state, knownPos);
                        }
                    }
                }
            }
        }
        return knownPos;
    }

        //private static void woodBreaking(BlockPos pos, World world, IBlockState state, EntityPlayer player, HashSet knownPos, int step) {
        private static int woodBreaking(BlockPos pos, World world, IBlockState state, EntityPlayer player, int step) {
        step ++;
        //Iterator knownPosValue = knownPos.iterator();

        /*while (knownPosValue.hasNext()) {
            state.getBlock().dropBlockAsItem(world, (BlockPos) knownPosValue.next(), state, 1);
            world.setBlockToAir((BlockPos) knownPosValue.next());
            player.getHeldItemMainhand().damageItem(1, player);
        }*/

            for (int y = 0; y <= 1; y++) {
                for (int x = -1; x <= 1; x++) {
                    for (int z = -1; z <= 1; z++) {
                            if ((world.getBlockState(pos.add(x,y,z)).getBlock() == Blocks.LOG || world.getBlockState(pos.add(x,y,z)).getBlock() == Blocks.LOG2) && pos.add(x,y,z) != pos) {
                                if(step == 30) {
                                    return step;
                                }
                                state.getBlock().dropBlockAsItem(world, pos.add(x,y,z), state, 1);
                                world.setBlockToAir(pos.add(x,y,z));
                                player.getHeldItemMainhand().damageItem(1, player);
                                //woodBreaking(pos.add(x,y,z), world, state, player, step, knownPos);
                                step = woodBreaking(pos.add(x,y,z), world, state, player, step);
                            }
                        }
                    }
                }
            return step;
        }

        @SubscribeEvent
        public static void onJoin(EntityJoinWorldEvent e) {
            if (e.getEntity() instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) e.getEntity();
                player.sendMessage(new TextComponentString("Hello, %p!".replace("%p", player.getName())));
            }
        }
    }
