package com.github.x3rmination.common.enchantments.pants.rare;

import com.github.x3rmination.Pitchants;
import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.init.PotionInit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentAssassin extends Enchantment {

    private boolean isReady = true;
    private static boolean handled = false;
    private EntityLivingBase entityLivingBase = null;
    private EntityLivingBase attacker = null;

    public EnchantmentAssassin() {
        super(Rarity.VERY_RARE, EnumEnchantmentType.ARMOR_LEGS, new EntityEquipmentSlot[]{EntityEquipmentSlot.LEGS});
        this.setName("assassin");
        this.setRegistryName(new ResourceLocation(Pitchants.MODID + ":assassin"));

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
        if(event.getEntityLiving() != null && event.getSource().getTrueSource() != null) {
            if (event.getSource().getTrueSource() instanceof EntityLivingBase) {
                attacker = (EntityLivingBase) event.getSource().getTrueSource();
                entityLivingBase = (EntityLivingBase) event.getEntity();
            } else if (event.getSource().getTrueSource() instanceof EntityArrow) {
                attacker = (EntityLivingBase) ((EntityArrow) event.getSource().getTrueSource()).shootingEntity;
                entityLivingBase = (EntityLivingBase) event.getEntity();
            }
            if (attacker != null && entityLivingBase != null) {
                int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.ASSASSIN, entityLivingBase.getItemStackFromSlot(EntityEquipmentSlot.LEGS));
                if (level > 0 && isReady && entityLivingBase.isSneaking() && !(entityLivingBase.isPotionActive(PotionInit.VENOM) || EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.SOMBER, entityLivingBase.getItemStackFromSlot(EntityEquipmentSlot.LEGS)) > 0)) {
                    isReady = false;
                    entityLivingBase.attemptTeleport(attacker.posX + (-2.5 * attacker.getLookVec().x), attacker.posY + 1, attacker.posZ + (-2.5 * attacker.getLookVec().z));
                    new Thread(() -> {
                        try {
                            Thread.sleep((long) (1000 * ((Math.pow(level, 2) * 2.5) - (12.5 * level) + 20)));
                            isReady = true;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }).start();
                }
            }
        }
    }
}
