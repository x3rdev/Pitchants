package com.github.x3rmination.util.handlers;

import com.github.x3rmination.core.items.ItemBowEnchantments;
import com.github.x3rmination.init.EnchantmentInit;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class EventHandler {

    ItemBowEnchantments enchBow= new ItemBowEnchantments();


    @SubscribeEvent
    public void looseEvent77(ArrowNockEvent event) {
        if(!event.getWorld().isRemote) {
            ItemStack arrow = new ItemStack(Items.ARROW);
            arrow.setCount(3);
            EntityPlayer player = ((EntityPlayer) event.getEntityLiving());
            ItemStack bow = new ItemStack(event.getBow().getItem());


            ItemStack itemStackFromSlot = player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);
            World world = event.getWorld();
            EntityLivingBase entityLiving = event.getEntityLiving();



            try {
                if (EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.ARROW_ARMORY, itemStackFromSlot) > 0) {
                    System.out.println("eventhandlerchecked");

                    if (player.isCreative() || EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.ARROW_ARMORY, bow) > 0) {
                        System.out.println("event.getcharge" );
                        enchBow.onPlayerStoppedUsing(itemStackFromSlot, world, entityLiving, 1);

                    }
                }
            } catch (NullPointerException exception) {
                // ignore
            }
        }
    }
}
