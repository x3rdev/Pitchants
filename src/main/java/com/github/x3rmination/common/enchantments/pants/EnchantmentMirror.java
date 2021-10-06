package com.github.x3rmination.common.enchantments.pants;

import com.github.x3rmination.core.damagesources.TrueDamage;
import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.init.PotionInit;
import com.github.x3rmination.pitchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Objects;

@Mod.EventBusSubscriber(modid=pitchants.MODID)
public class EnchantmentMirror extends Enchantment{

    public EnchantmentMirror() {
        super(Enchantment.Rarity.RARE, EnumEnchantmentType.ARMOR_LEGS, new EntityEquipmentSlot[]{EntityEquipmentSlot.LEGS});
        this.setName("mirror");
        this.setRegistryName(new ResourceLocation(pitchants.MODID + ":mirror"));
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
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.MIRROR, entityLiving.getItemStackFromSlot(EntityEquipmentSlot.LEGS));
            if(event.getSource().getDamageType().equals(TrueDamage.TRUE_DAMAGE.getDamageType()) && !(entityLiving.isPotionActive(PotionInit.VENOM) || EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.SOMBER, entityLiving.getItemStackFromSlot(EntityEquipmentSlot.LEGS)) > 0) && level > 0) {
                event.setCanceled(true);
                float reflected = (float) (event.getAmount() * ((0.25 * level) - 0.25));
                if(level > 1) {
                    Objects.requireNonNull(event.getSource().getTrueSource()).attackEntityFrom(TrueDamage.TRUE_DAMAGE, reflected);
                }
            }
        }
    }
}
