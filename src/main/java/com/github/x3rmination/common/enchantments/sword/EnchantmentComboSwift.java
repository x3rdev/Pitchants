package com.github.x3rmination.common.enchantments.sword;

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

public class EnchantmentComboSwift extends Enchantment {

    private static boolean handled = false;
    private int hitCount = 0;

    public EnchantmentComboSwift() {
        super(Enchantment.Rarity.RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
        this.setName("combo_swift");
        this.setRegistryName(new ResourceLocation(pitchants.MODID + ":combo_swift"));
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
        if(!(user.isPotionActive(PotionInit.VENOM) || EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.SOMBER, user.getItemStackFromSlot(EntityEquipmentSlot.LEGS)) > 0)) {
            hitCount += 1;
            int hitReq = (int) ((Math.pow(level, 2) * 0.5) - (2.5 * level) + 6);
            if (hitCount >= hitReq) {
                user.addPotionEffect(new PotionEffect(MobEffects.SPEED, (20 * level) + 40, (int) ((Math.pow(level, 2) * -0.5) + (2.5 * level) - 2)));
            }
        }
        handled = true;
    }
}
