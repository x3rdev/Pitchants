package com.github.x3rmination.common.enchantments.sword.rare;

import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.init.PotionInit;
import com.github.x3rmination.pitchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemBow;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid=pitchants.MODID)
public class EnchantmentHemorrhage extends Enchantment {

    private boolean isReady = true;

    public EnchantmentHemorrhage() {
        super(Enchantment.Rarity.VERY_RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
        this.setName("hemorrhage");
        this.setRegistryName(new ResourceLocation(pitchants.MODID + ":hemorrhage"));
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
    public void onAttack(LivingHurtEvent event) {
        if(event.getEntityLiving() instanceof EntityLiving && event.getSource().getTrueSource() instanceof EntityLivingBase) {
            EntityLivingBase victim = event.getEntityLiving();
            EntityLivingBase source = (EntityLivingBase) event.getSource().getTrueSource();
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.HEMORRHAGE, source.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND));
            if(level > 0 && isReady && !source.isPotionActive(PotionInit.VENOM)) {
                isReady = false;
                new Thread(() -> {
                    try {
                        Thread.sleep(8000-(level* 2000L));
                        isReady = true;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                }).start();
                victim.addPotionEffect(new PotionEffect(PotionInit.BLEEDING, 80, 0));
            }
        }
    }
}
