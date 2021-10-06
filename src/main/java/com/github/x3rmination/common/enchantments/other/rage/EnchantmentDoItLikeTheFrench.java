package com.github.x3rmination.common.enchantments.other.rage;

import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.pitchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class EnchantmentDoItLikeTheFrench extends Enchantment {

    private static boolean handled = false;

    public EnchantmentDoItLikeTheFrench() {
        super(Rarity.VERY_RARE, EnumEnchantmentType.ARMOR_LEGS, new EntityEquipmentSlot[]{EntityEquipmentSlot.LEGS});
        this.setName("do_it_like_the_french");
        this.setRegistryName(new ResourceLocation(pitchants.MODID + ":do_it_like_the_french"));

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
        if(target instanceof EntityLivingBase && ((EntityLivingBase) target).getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem().equals(Items.GOLDEN_HELMET)) {
            target.attackEntityFrom(DamageSource.GENERIC, level);
        }
        handled = true;
    }
}
