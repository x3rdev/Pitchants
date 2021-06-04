package com.github.x3rmination.init;



import com.github.x3rmination.common.enchantments.EnchantmentArrowArmory;
import com.github.x3rmination.common.enchantments.EnchantmentBottomlessQuiver;
import com.github.x3rmination.common.enchantments.EnchantmentPerun;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAppleGold;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class EnchantmentInit {

    public static final List<Enchantment> ENCHANTMENTS = new ArrayList<Enchantment>();
    public static final DamageSource TRUE_DAMAGE = (new DamageSource("trueDamage")).setDamageBypassesArmor().setDamageIsAbsolute();




    public static final Enchantment PERUN = new EnchantmentPerun();
    public static final Enchantment ARROW_ARMORY = new EnchantmentArrowArmory();
    public static final Enchantment BOTTOMLESS_QUIVER = new EnchantmentBottomlessQuiver();

}
