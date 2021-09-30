package com.github.x3rmination.common.enchantments.pants;

import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.init.PotionInit;
import com.github.x3rmination.pitchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class EnchantmentLastStand extends Enchantment {

    private boolean ready = true;
    private static boolean handled = false;
    public EnchantmentLastStand() {
        super(Rarity.RARE, EnumEnchantmentType.ARMOR_LEGS, new EntityEquipmentSlot[]{EntityEquipmentSlot.LEGS});
        this.setName("last_stand");
        this.setRegistryName(new ResourceLocation(pitchants.MODID + ":last_stand"));
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
        if (handled) {
            handled = false;
            return;
        }
        if(!user.isPotionActive(PotionInit.VENOM)){
            if (user.getHealth() <= 6 && ready) {
                ready = false;
                user.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, (int) (((-0.5 * Math.pow(level, 2)) + (2.5 * level) + 1) * 20), level - 1, true, true));
            }
            if (user.getHealth() > 6) {
                ready = true;
            }
        }
        handled = true;
    }
}
