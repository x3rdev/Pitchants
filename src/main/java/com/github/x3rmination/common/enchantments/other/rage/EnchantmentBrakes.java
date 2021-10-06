package com.github.x3rmination.common.enchantments.other.rage;

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
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class EnchantmentBrakes extends Enchantment {

    private static boolean handled = false;

    public EnchantmentBrakes() {
        super(Rarity.VERY_RARE, EnumEnchantmentType.ARMOR_LEGS, new EntityEquipmentSlot[]{EntityEquipmentSlot.LEGS});
        this.setName("brakes");
        this.setRegistryName(new ResourceLocation(pitchants.MODID + ":brakes"));

        EnchantmentInit.RAGE_ENCHANTMENTS.add(this);
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
    protected boolean canApplyTogether(@NotNull Enchantment ench) {
        return super.canApplyTogether(ench) && (!EnchantmentInit.ENCHANTMENTS.contains(ench) || !EnchantmentInit.DARK_ENCHANTMENTS.contains(ench));
    }

    @Override
    public void onEntityDamaged(EntityLivingBase user, Entity target, int level) {
        if (handled){
            handled = false;
            return;
        }
        if(target instanceof EntityLivingBase && Objects.requireNonNull(((EntityLivingBase) target).getActivePotionEffect(MobEffects.SPEED)).getAmplifier() >= 1) {
            int potDur = Objects.requireNonNull(((EntityLivingBase) target).getActivePotionEffect(MobEffects.SPEED)).getDuration();
            ((EntityLivingBase) target).removeActivePotionEffect(MobEffects.SPEED);
            ((EntityLivingBase) target).addPotionEffect(new PotionEffect(MobEffects.SPEED, potDur, 0));
        }
        handled = true;
    }
}
