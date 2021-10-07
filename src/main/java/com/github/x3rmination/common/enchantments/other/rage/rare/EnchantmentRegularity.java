package com.github.x3rmination.common.enchantments.other.rage.rare;

import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.Pitchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.jetbrains.annotations.NotNull;

public class EnchantmentRegularity extends Enchantment {

    public EnchantmentRegularity() {
        super(Rarity.VERY_RARE, EnumEnchantmentType.ARMOR_LEGS, new EntityEquipmentSlot[]{EntityEquipmentSlot.LEGS});
        this.setName("regularity");
        this.setRegistryName(new ResourceLocation(Pitchants.MODID + ":regularity"));

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

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onDamage(LivingHurtEvent event) {
        if(event.getEntityLiving() != null && event.getSource().getTrueSource() instanceof EntityLivingBase) {
            EntityLivingBase entityLiving = (EntityLivingBase) event.getSource().getTrueSource();
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.REGULARITY, entityLiving.getItemStackFromSlot(EntityEquipmentSlot.LEGS));
            if(level > 0 && event.getAmount() < ((0.4 * level) + 1.8)) {
                new Thread(() -> {
                    try {
                        Thread.sleep(100);
                        event.getEntityLiving().attackEntityFrom(DamageSource.GENERIC, (float) (event.getAmount() * ((0.15 * level) + 0.3)));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                }).start();
            }
        }
    }
}
