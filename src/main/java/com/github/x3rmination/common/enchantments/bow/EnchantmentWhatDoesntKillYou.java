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

public class EnchantmentWhatDoesntKillYou extends Enchantment {

    private static boolean handled = false;
    public EnchantmentWhatDoesntKillYou() {
        super(Rarity.UNCOMMON, EnumEnchantmentType.BOW, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND});
        this.setName("what_doesn't_kill_you");
        this.setRegistryName(new ResourceLocation(pitchants.MODID + ":what_doesn't_kill_you"));

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
                if(user == target) {
                    user.heal((float) (1 + (level * 0.5)));
                }
                handled = true;
            }
        }

    }
}