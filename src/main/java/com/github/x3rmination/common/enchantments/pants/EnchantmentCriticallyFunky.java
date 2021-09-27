package com.github.x3rmination.common.enchantments.pants;

import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.pitchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Objects;

@Mod.EventBusSubscriber(modid=pitchants.MODID)
public class EnchantmentCriticallyFunky extends Enchantment {

    private static boolean empowered = false;
    public EnchantmentCriticallyFunky() {
        super(Rarity.RARE, EnumEnchantmentType.ARMOR_LEGS, new EntityEquipmentSlot[]{EntityEquipmentSlot.LEGS});
        this.setName("critically_funky");
        this.setRegistryName(new ResourceLocation(pitchants.MODID + ":critically_funky"));
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
    public void onHurt(LivingHurtEvent event) {
        if(event.getEntityLiving() != null) {
            EntityLivingBase entityLivingBase = event.getEntityLiving();
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.CRITICALLY_FUNKY, entityLivingBase.getItemStackFromSlot(EntityEquipmentSlot.LEGS));
            if (level > 0 && Objects.requireNonNull(event.getSource().getTrueSource()).isAirBorne) {
                event.setAmount(event.getAmount());
                empowered = true;
            }
        }
    }

    @SubscribeEvent
    public void onAttack(LivingAttackEvent event) {
        if(event.getEntityLiving() != null) {
            EntityLivingBase entityLivingBase = event.getEntityLiving();
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.CRITICALLY_FUNKY, entityLivingBase.getItemStackFromSlot(EntityEquipmentSlot.LEGS));
            if (level > 0 && empowered) {
                empowered = false;
                float percent = (float) ((Math.pow(level, 2) * 0.01) + (0.11 * level) - 0.12);
                event.getEntityLiving().attackEntityFrom(DamageSource.GENERIC, event.getAmount()*percent);

            }
        }
    }
}
