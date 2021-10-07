package com.github.x3rmination.common.enchantments.pants;

import com.github.x3rmination.Pitchants;
import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.init.PotionInit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber(modid= Pitchants.MODID)
public class EnchantmentDangerClose extends Enchantment {

    private boolean isReady = true;

    public EnchantmentDangerClose() {
        super(Rarity.RARE, EnumEnchantmentType.ARMOR_LEGS, new EntityEquipmentSlot[]{EntityEquipmentSlot.LEGS});
        this.setName("danger_close");
        this.setRegistryName(new ResourceLocation(Pitchants.MODID + ":danger_close"));
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
    public void onTick(TickEvent.PlayerTickEvent event) {
        EntityPlayer player = event.player;
        int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.DANGER_CLOSE, player.getItemStackFromSlot(EntityEquipmentSlot.LEGS));
        if (level > 0 && event.player.getHealth() <= 8 && isReady && !(player.isPotionActive(PotionInit.VENOM) || EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.SOMBER, player.getItemStackFromSlot(EntityEquipmentSlot.LEGS)) > 0)) {
            player.addPotionEffect(new PotionEffect(MobEffects.SPEED, (3*level)*20, 2, true, true));
            isReady = false;
            new Thread(() -> {
                try {
                    Thread.sleep(10000);
                    isReady = true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }).start();
        }
    }
}
