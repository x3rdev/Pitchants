package com.github.x3rmination.common.enchantments.sword;

import com.github.x3rmination.Pitchants;
import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.init.PotionInit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

 


public class EnchantmentBulletTime extends Enchantment {

    public EnchantmentBulletTime() {
        super(Enchantment.Rarity.RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
        this.setName("bullet_time");
        this.setRegistryName(new ResourceLocation(Pitchants.MODID + ":bullet_time"));
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
    public void onArrowLand(ProjectileImpactEvent event) {
        if(event.getRayTraceResult().entityHit instanceof EntityPlayer) {
            EntityPlayer victim = (EntityPlayer) event.getRayTraceResult().entityHit;
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.BULLET_TIME, victim.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND));
            if(level > 0 && !(victim.isPotionActive(PotionInit.VENOM) || EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.SOMBER, victim.getItemStackFromSlot(EntityEquipmentSlot.LEGS)) > 0)) {
                victim.heal((float) (1.0+level));
            }
        }
    }
}
