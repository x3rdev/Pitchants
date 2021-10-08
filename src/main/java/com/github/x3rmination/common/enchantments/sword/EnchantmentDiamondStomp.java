package com.github.x3rmination.common.enchantments.sword;

import com.github.x3rmination.Pitchants;
import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.init.PotionInit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

 


public class EnchantmentDiamondStomp extends Enchantment {

    public EnchantmentDiamondStomp() {
        super(Enchantment.Rarity.RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
        this.setName("diamond_stomp");
        this.setRegistryName(new ResourceLocation(Pitchants.MODID + ":diamond_stomp"));
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
    public void onHurt(LivingHurtEvent event) {
        if(event.getEntityLiving() instanceof EntityLiving && event.getSource().getTrueSource() instanceof EntityLivingBase) {
            EntityLivingBase source = (EntityLivingBase) event.getSource().getTrueSource();
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.DIAMOND_STOMP, source.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND));
            if(level > 0 && !(source.isPotionActive(PotionInit.VENOM) || EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.SOMBER, source.getItemStackFromSlot(EntityEquipmentSlot.LEGS)) > 0)) {
                EntityLivingBase living = event.getEntityLiving();
                if(living.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem().equals(Items.DIAMOND_HELMET) || living.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem().equals(Items.DIAMOND_CHESTPLATE) || living.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem().equals(Items.DIAMOND_LEGGINGS) || living.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem().equals(Items.DIAMOND_BOOTS)) {
                    event.setAmount((float) (event.getAmount() + (event.getAmount() * ((Math.pow(level, 2) * 0.035) - (0.045 * level)  + 0.07))));
                }
            }
        }
    }
}
