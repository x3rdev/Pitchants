package com.github.x3rmination.common.enchantments.other.dark;

import com.github.x3rmination.core.damagesources.TrueDamage;
import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.pitchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class EnchantmentMisery extends Enchantment {

    private static boolean handled = false;

    public EnchantmentMisery() {
        super(Rarity.VERY_RARE, EnumEnchantmentType.ARMOR_LEGS, new EntityEquipmentSlot[]{EntityEquipmentSlot.LEGS});
        this.setName("misery");
        this.setRegistryName(new ResourceLocation(pitchants.MODID + ":misery"));

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

        if(target instanceof EntityLivingBase && (((EntityLivingBase) target).getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem().equals(Items.LEATHER_HELMET) || ((EntityLivingBase) target).getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem().equals(Items.LEATHER_CHESTPLATE) || ((EntityLivingBase) target).getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem().equals(Items.LEATHER_LEGGINGS) || ((EntityLivingBase) target).getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem().equals(Items.LEATHER_BOOTS))) {
            target.attackEntityFrom(TrueDamage.TRUE_DAMAGE, 1);
            user.attackEntityFrom(TrueDamage.TRUE_DAMAGE, (float) 0.6);
        }
        handled = true;
    }
}
