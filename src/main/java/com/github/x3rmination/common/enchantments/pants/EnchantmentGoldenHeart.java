package com.github.x3rmination.common.enchantments.pants;

import com.github.x3rmination.Pitchants;
import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.init.PotionInit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

 


public class EnchantmentGoldenHeart extends Enchantment {

    public EnchantmentGoldenHeart() {
        super(Rarity.RARE, EnumEnchantmentType.ARMOR_LEGS, new EntityEquipmentSlot[]{EntityEquipmentSlot.LEGS});
        this.setName("golden_heart");
        this.setRegistryName(new ResourceLocation(Pitchants.MODID + ":golden_heart"));
        EnchantmentInit.ENCHANTMENTS.add(this);
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

    @SubscribeEvent
    public void onKill(LivingDeathEvent event) {
        if(event.getSource().getTrueSource() instanceof EntityLivingBase) {
            EntityLivingBase entityLivingBase = (EntityLivingBase) event.getSource().getTrueSource();
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.GOLDEN_HEART, entityLivingBase.getItemStackFromSlot(EntityEquipmentSlot.LEGS));
            if (level > 0 && !(entityLivingBase.isPotionActive(PotionInit.VENOM) || EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.SOMBER, entityLivingBase.getItemStackFromSlot(EntityEquipmentSlot.LEGS)) > 0)) {
                float absorptionAmount = entityLivingBase.getAbsorptionAmount();
                int i = (int) (0.5 * Math.pow(level, 2) - (0.5 * level) + 1);
                int max = (2*level) + 6;
                if(absorptionAmount + i > max) {
                    entityLivingBase.setAbsorptionAmount(absorptionAmount);
                } else {
                    entityLivingBase.setAbsorptionAmount(absorptionAmount + i);
                }
            }
        }
    }
}
