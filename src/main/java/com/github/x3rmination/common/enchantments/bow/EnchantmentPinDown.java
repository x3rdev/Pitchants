package com.github.x3rmination.common.enchantments.bow;

import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.init.PotionInit;
import com.github.x3rmination.Pitchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

import java.util.Objects;

public class EnchantmentPinDown extends Enchantment {
    private static boolean handled = false;

    public EnchantmentPinDown() {
        super(Rarity.RARE, EnumEnchantmentType.BOW, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND});
        this.setName("pin_down");
        this.setRegistryName(new ResourceLocation(Pitchants.MODID + ":pin_down"));

        EnchantmentInit.ENCHANTMENTS.add(this);
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

    @Override
    public void onEntityDamaged(EntityLivingBase user, Entity target, int level) {
        if(Objects.requireNonNull(((EntityLivingBase) target).getLastDamageSource()).isProjectile() && !(user.isPotionActive(PotionInit.VENOM) || EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.SOMBER, user.getItemStackFromSlot(EntityEquipmentSlot.LEGS)) > 0)) {
            if (handled) {
                handled = false;
                return;
            }
            int potionDuration = (int) (((1.5 * Math.pow(level, 2)) - (2.5*level) + 4)*20);
            PotionEffect pinDown = new PotionEffect(PotionInit.PIN_DOWN, potionDuration*20, 0, true, true);

            ((EntityLivingBase) target).addPotionEffect(pinDown);
            handled = true;
        }
    }
}
