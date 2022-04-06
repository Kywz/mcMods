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

@Mod.EventBusSubscriber
public class TreeCapitatorEvents {

    @SubscribeEvent
        public static void onBlockBreak(PlayerEvent.BreakSpeed event){
            event.getEntityPlayer().sendMessage(new TextComponentString(String.valueOf(event.getOriginalSpeed())));
            event.getEntityPlayer().sendMessage(new TextComponentString(String.valueOf(woodBreakingRecursionCount(event.getPos(), event.getEntityPlayer().getEntityWorld(), event.getState(), 0))));
                //event.setNewSpeed(event.getOriginalSpeed() - woodBreakingRecursionCount(event.getPos(), event.getEntityPlayer().getEntityWorld(), event.getState()) * 0.1f);
                //event.getEntityPlayer().sendMessage(new TextComponentString(String.valueOf(woodBreakingRecursionCount(event.getPos(), event.getEntityPlayer().getEntityWorld(), event.getState()))));
                //event.getEntityPlayer().sendMessage(new TextComponentString(String.valueOf(event.getNewSpeed())));

                if (event.getEntityPlayer().getHeldItemMainhand().getItem() instanceof ItemAxe) { //вот тут кринж, работает только с объектом в мэйн хенде, по другому я не знаю как лист преобразовать в один элемент, .get() не работает
                //if (event.getPlayer().getHeldEquipment() instanceof ItemAxe){
                event.getEntityPlayer().sendMessage(new TextComponentString("Axe have been used"));

                //if(event.)

                if (event.getEntityPlayer().isSneaking() == true) {
                    event.getEntityPlayer().sendMessage(new TextComponentString("Player is sneaking"));
                    if ( event.getEntityPlayer().getEntityWorld().getBlockState(event.getPos()).getBlock() == Blocks.LOG ||  event.getEntityPlayer().getEntityWorld().getBlockState(event.getPos()).getBlock() == Blocks.LOG2) {
                        event.getEntityPlayer().sendMessage(new TextComponentString("Block broken"));
                        woodBreakingRecursion(event.getPos(),  event.getEntityPlayer().getEntityWorld(), event.getState(), event.getEntityPlayer(), 0);

                        //System.out.println("hello block");
                    }
                }
            }
        }

        //Не работает, бесконечный цикл
    /*private static float woodBreakingRecursionCount(BlockPos pos, World world, IBlockState state, float step) {
        step++;
        for (int y = 0; y <= 1; y++) { //stackoverflow
            for (int x = -1; x <= 1; x++) {
                for (int z = -1; z <= 1; z++) {
                    if ((world.getBlockState(pos.add(x,y,z)).getBlock() == Blocks.LOG || world.getBlockState(pos.add(x,y,z)).getBlock() == Blocks.LOG2) && pos.add(x,y,z) != pos) {
                        step = woodBreakingRecursionCount(pos.add(x,y,z), world, state, step);
                        if(step >= 30) {
                            return step;
                        }
                    }
                }
            }
        }
        return step;
    }*/

        private static void woodBreakingRecursion(BlockPos pos, World world, IBlockState state, EntityPlayer player, int step) {
            step++;

            for (int y = 0; y <= 1; y++) {
                for (int x = -1; x <= 1; x++) {
                    for (int z = -1; z <= 1; z++) {
                            if ((world.getBlockState(pos.add(x,y,z)).getBlock() == Blocks.LOG || world.getBlockState(pos.add(x,y,z)).getBlock() == Blocks.LOG2) && pos.add(x,y,z) != pos) {
                                //System.out.println(pos.add(x,y,z).toString());

                                state.getBlock().dropBlockAsItem(world, pos.add(x,y,z), state, 1);

                                //world.getBlockState(pos.add(x,y,z)).getBlock().breakBlock(world, pos.add(x,y,z), state);

                                world.setBlockToAir(pos.add(x,y,z));
                                player.getHeldItemMainhand().damageItem(1, player);
                                woodBreakingRecursion(pos.add(x,y,z), world, state, player, step);
                                if(step == 30) {
                                    return;
                                }
                            }
                        }
                    }
                }
        }

        @SubscribeEvent
        public static void onJoin(EntityJoinWorldEvent e) {
            if (e.getEntity() instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) e.getEntity();
                player.sendMessage(new TextComponentString("Hello, %p!".replace("%p", player.getName())));
            }
        }
    }
