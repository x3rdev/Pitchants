package com.github.x3rmination.common.enchantments.sword;

import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.init.PotionInit;
import com.github.x3rmination.pitchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class EnchantmentCounterJanitor extends Enchantment {

    private static boolean handled = false;

    public EnchantmentCounterJanitor() {
        super(Enchantment.Rarity.RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
        this.setName("counter_janitor");
        this.setRegistryName(new ResourceLocation(pitchants.MODID + ":counter_janitor"));
        EnchantmentInit.ENCHANTMENTS.add(this);
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 20 * enchantmentLevel;
    }

    @Override
    public int getMaxEnchantability(int enchantmentLevel) {
        return this.getMinEnchantability(enchantmentLevel) + 10;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public void onEntityDamaged(EntityLivingBase user, Entity target, int level) {
        if (handled){
            handled = false;
            return;
        }
        if(target.isDead && !(user.isPotionActive(PotionInit.VENOM) || EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.SOMBER, user.getItemStackFromSlot(EntityEquipmentSlot.LEGS)) > 0)) {
            user.addPotionEffect(new PotionEffect(MobEffects.SPEED , (int) ((Math.pow(level, 2) * 10) - (10 * level) +40), 0));
        }
        handled = true;
    }
}
