package com.github.x3rmination.common.enchantments.other.dark;

import com.github.x3rmination.Pitchants;
import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.init.PotionInit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.jetbrains.annotations.NotNull;


public class EnchantmentSomber extends Enchantment {

    public EnchantmentSomber() {
        super(Rarity.VERY_RARE, EnumEnchantmentType.ARMOR_LEGS, new EntityEquipmentSlot[]{EntityEquipmentSlot.LEGS});
        this.setName("somber");
        this.setRegistryName(new ResourceLocation(Pitchants.MODID + ":somber"));

        EnchantmentInit.DARK_ENCHANTMENTS.add(this);
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
        return 1;
    }

    @Override
    protected boolean canApplyTogether(@NotNull Enchantment ench) {
        return super.canApplyTogether(ench) && (!EnchantmentInit.ENCHANTMENTS.contains(ench) || !EnchantmentInit.RAGE_ENCHANTMENTS.contains(ench));
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onDamage(LivingHurtEvent event) {
        if(event.getEntityLiving() != null && event.getSource().getTrueSource() instanceof EntityLivingBase) {
            EntityLivingBase entityLiving = event.getEntityLiving();
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.SOMBER, entityLiving.getItemStackFromSlot(EntityEquipmentSlot.LEGS));
            if(level > 0) {
                ((EntityLivingBase) event.getSource().getTrueSource()).addPotionEffect(new PotionEffect(PotionInit.VENOM, 10, 0, true, false));
            }
        }
    }
}
