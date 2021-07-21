package com.github.x3rmination.common.enchantments;

import com.github.x3rmination.common.potions.PinDownPotion;
import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.init.PotionInit;
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
import net.minecraft.util.ResourceLocation;

import java.util.Objects;

public class EnchantmentPinDown extends Enchantment {
    private static boolean handled = false;

    public EnchantmentPinDown() {
        super(Rarity.UNCOMMON, EnumEnchantmentType.BOW, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND});
        this.setName("pin_down");
        this.setRegistryName(new ResourceLocation(pitchants.MODID + ":pin_down"));

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
                int potionDuration = (int) (((1.5 * Math.pow(level, 2)) - (2.5*level) + 4)*20);
                ((EntityLiving) target).addPotionEffect(new PotionEffect(PotionInit.PIN_DOWN, potionDuration));
                handled = true;
            }
        }

    }
}
