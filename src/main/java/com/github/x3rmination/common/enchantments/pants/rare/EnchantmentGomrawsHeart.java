package com.github.x3rmination.common.enchantments.pants.rare;

import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.init.PotionInit;
import com.github.x3rmination.pitchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber(modid=pitchants.MODID)
public class EnchantmentGomrawsHeart extends Enchantment {

    private boolean inCombat = false;
    private boolean isReady = true;
    public EnchantmentGomrawsHeart() {
        super(Rarity.VERY_RARE, EnumEnchantmentType.ARMOR_LEGS, new EntityEquipmentSlot[]{EntityEquipmentSlot.LEGS});
        this.setName("gomraws_heart");
        this.setRegistryName(new ResourceLocation(pitchants.MODID + ":gomraws_heart"));

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

    @Override
    public void onUserHurt(EntityLivingBase user, Entity attacker, int level) {
        if(!inCombat) {
            inCombat = true;
            new Thread(() -> {
                try {
                    Thread.sleep(10000);
                    inCombat = false;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }).start();
        }
    }

    @Override
    public void onEntityDamaged(EntityLivingBase user, Entity target, int level) {
        if(!inCombat) {
            inCombat = true;
            new Thread(() -> {
                try {
                    Thread.sleep(10000);
                    inCombat = false;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }).start();
        }
    }
    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event) {
        int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.GOMRAWS_HEART, event.player.getItemStackFromSlot(EntityEquipmentSlot.LEGS));
        if(level > 1 && inCombat && isReady && !(event.player.isPotionActive(PotionInit.VENOM) || EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.SOMBER, event.player.getItemStackFromSlot(EntityEquipmentSlot.LEGS)) > 0)){
            isReady = false;
            event.player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, (level-1)*20, 3));
        }
        if(level > 0 && !inCombat && !(event.player.isPotionActive(PotionInit.VENOM) || EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.SOMBER, event.player.getItemStackFromSlot(EntityEquipmentSlot.LEGS)) > 0)){
            isReady = true;
            event.player.setHealth(event.player.getMaxHealth());
        }
    }
}
