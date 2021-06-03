package com.github.x3rmination.core.items;

import com.github.x3rmination.init.EnchantmentInit;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.common.Mod;

public class ItemBowEnchantments extends ItemBow {


//    @Override
//    public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityLivingBase entityLivingBase, int duration) {
//        super.onPlayerStoppedUsing(itemStack, world, entityLivingBase, duration);
//        if (entityLivingBase instanceof EntityPlayer) {
//            EntityPlayer entityplayer = (EntityPlayer) entityLivingBase;
//            ItemStack itemstack = this.findAmmo(entityplayer);
//            boolean flag = entityplayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, itemStack) > 0;
//            int i = this.getMaxItemUseDuration(itemStack) - duration;
//            //when feeding method duration make sure you get a good number, removed temporarily
//            i = ForgeEventFactory.onArrowLoose(itemStack, world, entityplayer, i, !itemstack.isEmpty() || flag);
//            if (i < 0) {
//                return;
//            }
//
//            System.out.println("playerStoppedUsing");
//
//            if (!itemstack.isEmpty() || flag) {
//                if (itemstack.isEmpty()) {
//                    itemstack = new ItemStack(Items.ARROW);
//                }
//
//                float f = getArrowVelocity(i);
//                System.out.println("arrow velocity: " + f);
//                if ((double) f >= 0.1D) {
//                    if (!world.isRemote) {
//                        System.out.println("remoteworld");
//                        ItemArrow itemarrow = (ItemArrow) (itemstack.getItem() instanceof ItemArrow ? itemstack.getItem() : Items.ARROW);
//                        EntityArrow entityarrow = itemarrow.createArrow(world, itemstack, entityplayer);
//                        entityarrow = this.customizeArrow(entityarrow);
//                        entityarrow.shoot(entityplayer, entityplayer.rotationPitch, entityplayer.rotationYaw, 0.0F, f * 3.0F, 1.0F);
//
//
//                        int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.ARROW_ARMORY, entityplayer.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND));
//                        System.out.println("level: " + level);
//                        if (level > 0) {
//                            System.out.print("reached level>0 ");
//                            System.out.print("percent: " + (entityarrow.getDamage() * (11 * (Math.pow(level, 2))) - (20 * level) + 21));
//                            entityarrow.setDamage(entityarrow.getDamage() + (entityarrow.getDamage() * (11 * (Math.pow(level, 2))) - (20 * level) + 21));
//
//                        }
//                    }
//                }
//            }
//        }
//
//    }




}
