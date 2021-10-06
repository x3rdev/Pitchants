package com.github.x3rmination.common.enchantments.sword.rare;

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

public class EnchantmentSpeedyHit extends Enchantment {
    private static boolean handled = false;
    private static boolean isReady = true;

    public EnchantmentSpeedyHit() {
        super(Rarity.VERY_RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
        this.setName("speedy_hit");
        this.setRegistryName(new ResourceLocation(pitchants.MODID + ":speedy_hit"));
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
        if(isReady && !(user.isPotionActive(PotionInit.VENOM) || EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.SOMBER, user.getItemStackFromSlot(EntityEquipmentSlot.LEGS)) > 0)) {
            isReady = false;
            user.addPotionEffect(new PotionEffect(MobEffects.SPEED, (40 * level) + 60, 0));
            new Thread(() -> {
                try {
                    Thread.sleep(8000-(level* 2000L));
                    isReady = true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }).start();
        }
        handled = true;
    }
}
