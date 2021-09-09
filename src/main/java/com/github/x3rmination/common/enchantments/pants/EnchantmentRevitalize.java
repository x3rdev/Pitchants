package com.github.x3rmination.common.enchantments.pants;

import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.pitchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class EnchantmentRevitalize extends Enchantment {

    private boolean isReady = true;
    private static boolean handled = false;

    public EnchantmentRevitalize() {
        super(Enchantment.Rarity.RARE, EnumEnchantmentType.ARMOR_LEGS, new EntityEquipmentSlot[]{EntityEquipmentSlot.LEGS});
        this.setName("revitalize");
        this.setRegistryName(new ResourceLocation(pitchants.MODID + ":revitalize"));
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
    public void onUserHurt(EntityLivingBase user, Entity attacker, int level) {
        if(user.getHealth() <= 6 && isReady) {
            if (handled) {
                handled = false;
                return;
            }
            isReady = false;
            double v1 = (Math.pow(level, 2) * -0.5) + (2.5 * level);
            double v = (Math.pow(level, 2) * 20) - (60 * level);
            int rPotency = (int) (v1 - 1);
            int rDuration = (int) (v + 100);
            int sPotency = (int) (v1 - 2);
            int sDuration = (int) (v + 140);
            user.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, rDuration, rPotency));
            user.addPotionEffect(new PotionEffect(MobEffects.SPEED, sDuration, sPotency));
            new Thread(() -> {
                try {
                    Thread.sleep(4500);
                    isReady = true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }).start();
            handled = true;
        }
    }
}
