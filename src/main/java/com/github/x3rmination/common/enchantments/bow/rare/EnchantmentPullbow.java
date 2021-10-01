package com.github.x3rmination.common.enchantments.bow.rare;

import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.init.PotionInit;
import com.github.x3rmination.pitchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid=pitchants.MODID)
public class EnchantmentPullbow extends Enchantment {

    private boolean isReady = true;
    public EnchantmentPullbow() {
        super(Rarity.VERY_RARE, EnumEnchantmentType.BOW, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND});
        this.setName("pullbow");
        this.setRegistryName(new ResourceLocation(pitchants.MODID + ":pullbow"));

        EnchantmentInit.ENCHANTMENTS.add(this);
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 10 * enchantmentLevel;
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
    public void onKnockBack(LivingKnockBackEvent event) {
        if (event.getAttacker() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getAttacker();
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.PULL_BOW, player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND));
            if(isReady && level > 0 && !(player.isPotionActive(PotionInit.VENOM) || EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.SOMBER, player.getItemStackFromSlot(EntityEquipmentSlot.LEGS)) > 0)) {
                float distance = (float) Math.sqrt(Math.pow(event.getEntity().posX-event.getAttacker().posX, 2)+Math.pow(event.getEntity().posY-event.getAttacker().posY, 2)+Math.pow(event.getEntity().posZ-event.getAttacker().posZ, 2));
                event.setStrength((float) (-distance*0.37));
                isReady = false;
                new Thread(() -> {
                    try {
                        Thread.sleep(8000);
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
