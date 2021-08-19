package com.github.x3rmination.common.enchantments.bow;

import akka.japi.Effect;
import com.github.x3rmination.core.damagesources.TrueDamage;
import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.pitchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.init.PotionTypes;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.potion.PotionType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;

import java.util.Objects;

public class EnchantmentFasterThanTheirShadow extends Enchantment {


    private static boolean handled = false;
    private static int attackCount = 0;

    public EnchantmentFasterThanTheirShadow() {
        super(Rarity.RARE, EnumEnchantmentType.BOW, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
        this.setName("faster_than_their_shadow");
        this.setRegistryName(new ResourceLocation(pitchants.MODID + ":faster_than_their_shadow"));

        EnchantmentInit.ENCHANTMENTS.add(this);
    }

    @Override
    public void onEntityDamaged(EntityLivingBase user, Entity target, int level) {
        if(Objects.requireNonNull(((EntityLiving) target).getLastDamageSource()).isProjectile()) {
            if (handled) {
                handled = false;
                return;
            }
            attackCount += 1;
            int reqattack = (int) ((Math.pow(level, 2))*0.5 - (2.5*level) + 5);
            if (attackCount >= reqattack) {
                user.addPotionEffect(new PotionEffect(MobEffects.SPEED, 80, level));
                attackCount = 0;
            }
            handled = true;
        }
    }


    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 8 * enchantmentLevel;
    }

    @Override
    public int getMaxEnchantability(int enchantmentLevel) {
        return this.getMinEnchantability(enchantmentLevel) + 10;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }
}
