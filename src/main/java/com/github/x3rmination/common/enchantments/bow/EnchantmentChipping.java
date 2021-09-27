package com.github.x3rmination.common.enchantments.bow;

import com.github.x3rmination.core.damagesources.TrueDamage;
import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.pitchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;

import java.util.Objects;

public class EnchantmentChipping extends Enchantment {


    private static boolean handled = false;

    public EnchantmentChipping() {
        super(Rarity.RARE, EnumEnchantmentType.BOW, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
        this.setName("chipping");
        this.setRegistryName(new ResourceLocation(pitchants.MODID + ":chipping"));

        EnchantmentInit.ENCHANTMENTS.add(this);
    }

    @Override
    public void onEntityDamaged(EntityLivingBase user, Entity target, int level) {
        if((Objects.requireNonNull(((EntityLivingBase) target).getLastDamageSource())).isProjectile()) {
            if (handled) {
                handled = false;
                return;
            }

            target.attackEntityFrom(TrueDamage.TRUE_DAMAGE, level);
            handled = true;
        }
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
}
