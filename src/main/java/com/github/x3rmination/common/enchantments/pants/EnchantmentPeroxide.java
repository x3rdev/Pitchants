package com.github.x3rmination.common.enchantments.pants;

import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.init.PotionInit;
import com.github.x3rmination.Pitchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class EnchantmentPeroxide extends Enchantment {

    private static boolean handled = false;

    public EnchantmentPeroxide() {
        super(Enchantment.Rarity.RARE, EnumEnchantmentType.ARMOR_LEGS, new EntityEquipmentSlot[]{EntityEquipmentSlot.LEGS});
        this.setName("peroxide");
        this.setRegistryName(new ResourceLocation(Pitchants.MODID + ":peroxide"));
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
        if(!(user.isPotionActive(PotionInit.VENOM) || EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.SOMBER, user.getItemStackFromSlot(EntityEquipmentSlot.LEGS)) > 0)) {
            user.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, (int) ((Math.pow(level, 2) * -30) + (150 * level) - 20), (int) ((Math.pow(level, 2) * 0.25) - (1.5 * level) + 1)));
        }
        handled = true;
    }
}
