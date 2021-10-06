package com.github.x3rmination.common.enchantments.other.rage;

import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.pitchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class EnchantmentHeighHo extends Enchantment {

    public EnchantmentHeighHo() {
        super(Rarity.VERY_RARE, EnumEnchantmentType.ARMOR_LEGS, new EntityEquipmentSlot[]{EntityEquipmentSlot.LEGS});
        this.setName("heigh_ho");
        this.setRegistryName(new ResourceLocation(pitchants.MODID + ":heigh_ho"));

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
        if(target instanceof EntityLivingBase && EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.MIRROR, ((EntityLivingBase) target).getItemStackFromSlot(EntityEquipmentSlot.LEGS)) > 0) {
            target.attackEntityFrom(DamageSource.OUT_OF_WORLD, level);
        }
    }
}
