package com.github.x3rmination.common.enchantments.sword;

import com.github.x3rmination.Pitchants;
import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.init.PotionInit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class EnchantmentCrush extends Enchantment {

    private static boolean handled = false;
    private boolean isReady = true;

    public EnchantmentCrush() {
        super(Enchantment.Rarity.RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
        this.setName("crush");
        this.setRegistryName(new ResourceLocation(Pitchants.MODID + ":crush"));
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
            isReady=false;
            user.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, (int) (Math.pow(level, 2) * -1) + (7 * level) - 2, level + 3));
            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                    isReady=true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }).start();
        }
        handled = true;
    }
}
