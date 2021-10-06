package com.github.x3rmination.common.enchantments.other.dark.rare;

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
import org.jetbrains.annotations.NotNull;

public class EnchantmentComboVenom extends Enchantment {

    private static boolean handled = false;
    private int hitCount = 0;

    public EnchantmentComboVenom() {
        super(Rarity.VERY_RARE, EnumEnchantmentType.ARMOR_LEGS, new EntityEquipmentSlot[]{EntityEquipmentSlot.LEGS});
        this.setName("combo_venom");
        this.setRegistryName(new ResourceLocation(pitchants.MODID + ":combo_venom"));

        EnchantmentInit.DARK_ENCHANTMENTS.add(this);
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
        return 1;
    }

    @Override
    protected boolean canApplyTogether(@NotNull Enchantment ench) {
        return super.canApplyTogether(ench) && (!EnchantmentInit.ENCHANTMENTS.contains(ench) || !EnchantmentInit.RAGE_ENCHANTMENTS.contains(ench));
    }

    @Override
    public void onEntityDamaged(EntityLivingBase user, Entity target, int level) {
        if (handled){
            handled = false;
            return;
        }
        hitCount += 1;
        if(target instanceof EntityLivingBase && hitCount >= 3) {
            hitCount = 0;
            ((EntityLivingBase) target).addPotionEffect(new PotionEffect(MobEffects.POISON, 240, 0));
            ((EntityLivingBase) target).addPotionEffect(new PotionEffect(PotionInit.VENOM, 240, 0));
        }
        handled = true;
    }
}
