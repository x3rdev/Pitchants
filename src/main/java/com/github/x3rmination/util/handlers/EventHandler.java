package com.github.x3rmination.util.handlers;

import com.github.x3rmination.core.items.ItemBowEnchantments;
import com.github.x3rmination.init.EnchantmentInit;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class EventHandler {



//    @SubscribeEvent
//    public void looseEvent77(ArrowLooseEvent event) {
//
//        ItemBowEnchantments enchBow = new ItemBowEnchantments();
//        if(!event.getWorld().isRemote) {
//
//
//
////            ItemStack arrow = new ItemStack(Items.ARROW);
////            arrow.setCount(3);
//
//            EntityPlayer player = ((EntityPlayer) event.getEntityLiving());
//
//            ItemStack bow = new ItemStack(event.getBow().getItem());
//            ItemStack itemStackFromSlot = player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);
//
//
//            try {
//                if (EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.ARROW_ARMORY, itemStackFromSlot) > 0) {
//
//
//                    if (player.isCreative() || EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.ARROW_ARMORY, bow) > 0) {
//
//                        enchBow.onPlayerStoppedUsing(itemStackFromSlot, player.world, event.getEntityLiving(), event.getCharge());
//                    }
//                }
//            } catch (NullPointerException exception) {
//                // ignore
//            }
//        }
//    }
}
