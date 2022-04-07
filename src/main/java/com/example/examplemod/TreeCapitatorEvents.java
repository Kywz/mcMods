package com.example.examplemod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemAxe;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.HashSet;


@Mod.EventBusSubscriber
public class TreeCapitatorEvents {

    @SubscribeEvent
        public static void onBlockBreaking(PlayerEvent.BreakSpeed event){
            event.getEntityPlayer().sendMessage(new TextComponentString(String.valueOf(event.getOriginalSpeed())));
                if (event.getEntityPlayer().getHeldItemMainhand().getItem() instanceof ItemAxe) {
                    if (event.getEntityPlayer().isSneaking() == true) {
                        if ( event.getEntityPlayer().getEntityWorld().getBlockState(event.getPos()).getBlock() == Blocks.LOG ||  event.getEntityPlayer().getEntityWorld().getBlockState(event.getPos()).getBlock() == Blocks.LOG2) {
                            HashSet<BlockPos> blocksToBeBroken = new HashSet<BlockPos>();
                            blocksToBeBroken.add(event.getPos());
                            blocksToBeBroken = woodBreakingRecursionCount(event.getPos(), event.getEntityPlayer().getEntityWorld(), blocksToBeBroken);
                            event.setNewSpeed(event.getOriginalSpeed() / blocksToBeBroken.size());
                    }
                }
            }
        }


        @SubscribeEvent
        public static void onBlockBreak(BlockEvent.BreakEvent event) {
            if (event.getPlayer().getHeldItemMainhand().getItem() instanceof ItemAxe) {
                if (event.getPlayer().isSneaking() == true) {
                    if ( event.getWorld().getBlockState(event.getPos()).getBlock() == Blocks.LOG ||  event.getWorld().getBlockState(event.getPos()).getBlock() == Blocks.LOG2) {
                        HashSet<BlockPos> blocksToBeBroken = new HashSet<BlockPos>();
                        blocksToBeBroken.add(event.getPos());
                        woodBreaking(event.getWorld(), event.getPlayer(), woodBreakingRecursionCount(event.getPos(),event.getWorld(), blocksToBeBroken));
                    }
                }
            }
        }


    private static HashSet woodBreakingRecursionCount(BlockPos pos, World world, HashSet<BlockPos> knownPos) {
        for (int y = 0; y <= 1; y++) {
            for (int x = -1; x <= 1; x++) {
                for (int z = -1; z <= 1; z++) {
                    if ((world.getBlockState(pos.add(x,y,z)).getBlock() == Blocks.LOG || world.getBlockState(pos.add(x,y,z)).getBlock() == Blocks.LOG2) && pos.add(x,y,z) != pos) {
                        if (knownPos.size() == 30) {
                            return knownPos;
                        }
                        if(!(knownPos.contains(pos.add(x,y,z)))) {
                            knownPos.add(pos.add(x,y,z));
                            knownPos = woodBreakingRecursionCount(pos.add(x,y,z), world, knownPos);
                        }
                    }
                }
            }
        }
        return knownPos;
    }

        private static void woodBreaking(World world, EntityPlayer player, HashSet<BlockPos> knownPos) {
            for (BlockPos blocPos: knownPos) {
                world.destroyBlock(blocPos, true);
                player.getHeldItemMainhand().damageItem(1, player);
            }
        }
    }
