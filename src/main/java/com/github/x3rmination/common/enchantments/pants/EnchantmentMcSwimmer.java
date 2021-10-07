package com.github.x3rmination.common.enchantments.pants;

import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.init.PotionInit;
import com.github.x3rmination.Pitchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Objects;

@Mod.EventBusSubscriber(modid= Pitchants.MODID)
public class EnchantmentMcSwimmer extends Enchantment {

    public EnchantmentMcSwimmer() {
        super(Enchantment.Rarity.RARE, EnumEnchantmentType.ARMOR_LEGS, new EntityEquipmentSlot[]{EntityEquipmentSlot.LEGS});
        this.setName("mcswimmer");
        this.setRegistryName(new ResourceLocation(Pitchants.MODID + ":mcswimmer"));
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

    @SubscribeEvent
    public void onDamage(LivingHurtEvent event) {
        if(event.getEntityLiving() != null) {
            EntityLivingBase entityLiving = event.getEntityLiving();
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.MCSWIMMER, entityLiving.getItemStackFromSlot(EntityEquipmentSlot.LEGS));
            if ((entityLiving.isInLava() || entityLiving.isInWater()) && level > 0 && Objects.requireNonNull(entityLiving.getLastDamageSource()).isUnblockable() && !(entityLiving.isPotionActive(PotionInit.VENOM) || EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.SOMBER, entityLiving.getItemStackFromSlot(EntityEquipmentSlot.LEGS)) > 0)) {
                int percent = (int) (2.5 * Math.pow(level, 2) + (7.5 * level) + 15);
                event.setAmount((event.getAmount() * 100) - (event.getAmount() * percent));
            }
        }
    }
}
