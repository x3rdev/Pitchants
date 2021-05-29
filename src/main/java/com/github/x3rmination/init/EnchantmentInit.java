package com.github.x3rmination.init;



import com.github.x3rmination.common.enchantments.EnchantmentPerun;
import com.github.x3rmination.pitchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;


public class EnchantmentInit {

    public static final List<Enchantment> ENCHANTMENTS = new ArrayList<Enchantment>();

    public static final Enchantment DISCOUNT_PERUN = new EnchantmentPerun();

//    @SubscribeEvent
//    public static void strikeLightning(LivingAttackEvent event) {
//
//        EntityLivingBase living = event.getEntityLiving();
//        Entity target = event.getEntity();
//        int level = EnchantmentHelper.getMaxEnchantmentLevel(DISCOUNT_PERUN, living);
//
//        if (level > 0) {
//
//        }
//
//    }

}
