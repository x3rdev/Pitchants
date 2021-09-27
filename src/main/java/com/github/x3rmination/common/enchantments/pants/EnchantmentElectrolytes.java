package com.github.x3rmination.common.enchantments.pants;

import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.pitchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Objects;

@Mod.EventBusSubscriber(modid=pitchants.MODID)
public class EnchantmentElectrolytes extends Enchantment {

    public EnchantmentElectrolytes() {
        super(Rarity.RARE, EnumEnchantmentType.ARMOR_LEGS, new EntityEquipmentSlot[]{EntityEquipmentSlot.LEGS});
        this.setName("electrolytes");
        this.setRegistryName(new ResourceLocation(pitchants.MODID + ":electrolytes"));
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
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.ELECTROLYTES, entityLivingBase.getItemStackFromSlot(EntityEquipmentSlot.LEGS));
            if (level > 0 && entityLivingBase.isPotionActive(MobEffects.SPEED) && Objects.requireNonNull(entityLivingBase.getActivePotionEffect(MobEffects.SPEED)).getDuration() <= (120*level) + 240) {
                int amplifier = Objects.requireNonNull(entityLivingBase.getActivePotionEffect(MobEffects.SPEED)).getAmplifier();
                if(amplifier == 0) {
                    int ticksAddedPerKill = level * 40;
                    entityLivingBase.addPotionEffect(new PotionEffect(MobEffects.SPEED, (Objects.requireNonNull(entityLivingBase.getActivePotionEffect(MobEffects.SPEED)).getDuration() + ticksAddedPerKill), (Objects.requireNonNull(entityLivingBase.getActivePotionEffect(MobEffects.SPEED))).getAmplifier(), true, true));
                }
                if(amplifier > 1) {
                    int ticksAddedPerKill = level * 20;
                    entityLivingBase.addPotionEffect(new PotionEffect(MobEffects.SPEED, (Objects.requireNonNull(entityLivingBase.getActivePotionEffect(MobEffects.SPEED)).getDuration() + ticksAddedPerKill), (Objects.requireNonNull(entityLivingBase.getActivePotionEffect(MobEffects.SPEED))).getAmplifier(), true, true));
                }
            }
        }
    }
}
