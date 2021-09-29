package com.github.x3rmination.common.enchantments.sword.rare;

import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.pitchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class EnchantmentThePunch extends Enchantment {
    private static boolean handled = false;
    private static boolean isReady = true;

    public EnchantmentThePunch() {
        super(Rarity.VERY_RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
        this.setName("the_punch");
        this.setRegistryName(new ResourceLocation(pitchants.MODID + ":the_punch"));
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
        if(isReady) {
            isReady = false;
            if(target instanceof EntityLivingBase) {
                EntityLivingBase entityLivingBase = (EntityLivingBase) target;
                entityLivingBase.addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 10, 19));
            }
            new Thread(() -> {
                try {
                    Thread.sleep(1000 * (35-(5L *level)));
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
