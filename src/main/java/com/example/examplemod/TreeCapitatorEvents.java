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
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class TreeCapitatorEvents {

        @SubscribeEvent
        public static void onBlockBreak(BlockEvent.BreakEvent event){
            //event.getPlayer().addExperience(500);
            //event.getPlayer().sendMessage(new TextComponentString(event.getPlayer().getHeldEquipment().toString()));
            if (event.getPlayer().getHeldItemMainhand().getItem() instanceof ItemAxe) { //вот тут кринж, работает только с объектом в мэйн хенде, по другому я не знаю как лист преобразовать в один элемент, .get() не работает
                //if (event.getPlayer().getHeldEquipment() instanceof ItemAxe){
                event.getPlayer().sendMessage(new TextComponentString("Axe have been used"));

                if (event.getWorld().getBlockState(event.getPos()).getBlock() == Blocks.LOG) {
                    event.getPlayer().sendMessage(new TextComponentString("Block broken"));
                    woodBreakingRecursion(event.getPos(), event.getWorld(), event.getState());

                    //System.out.println("hello block");
                }
            }
        }

        private static void woodBreakingRecursion(BlockPos pos, World world, IBlockState state) {
            for (int y = 0; y <= 1; y++) {
                for (int x = -1; x <= 1; x++) {
                    for (int z = -1; z <= 1; z++) {
                            if (world.getBlockState(pos.add(x,y,z)).getBlock() == Blocks.LOG && pos.add(x,y,z) != pos) {
                                //System.out.println(pos.add(x,y,z).toString());

                                world.getBlockState(pos.add(x,y,z)).getBlock().breakBlock(world, pos.add(x,y,z), state);
                                world.getBlockState(pos.add((x,y,z)).

                                world.setBlockToAir(pos.add(x,y,z));

                                woodBreakingRecursion(pos.add(x,y,z), world, state);
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
