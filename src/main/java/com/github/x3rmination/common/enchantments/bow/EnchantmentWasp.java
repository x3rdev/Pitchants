package com.github.x3rmination.common.enchantments.bow;

import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.pitchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

import java.util.Objects;

public class EnchantmentWasp extends Enchantment{

    private static boolean handled = false;
    public EnchantmentWasp() {
        super(Enchantment.Rarity.UNCOMMON, EnumEnchantmentType.BOW, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND});
        this.setName("wasp");
        this.setRegistryName(new ResourceLocation(pitchants.MODID + ":wasp"));

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
        if(target instanceof EntityLiving) {
            if((Objects.requireNonNull(((EntityLiving) target).getLastDamageSource())).isProjectile()) {
                if (handled) {
                    handled = false;
                    return;
                }

                int duration = (5*level) + 1;
                int potency = level +1;
                ((EntityLiving) target).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, duration*20, potency -1, true, true));
                handled = true;
            }
        }

    }
}
