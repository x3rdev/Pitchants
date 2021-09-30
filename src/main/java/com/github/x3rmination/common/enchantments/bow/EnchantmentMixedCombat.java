package com.github.x3rmination.common.enchantments.bow;

import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.init.PotionInit;
import com.github.x3rmination.pitchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid=pitchants.MODID)
public class EnchantmentMixedCombat extends Enchantment {

    private static boolean empowered = false;

    public EnchantmentMixedCombat() {
        super(Enchantment.Rarity.RARE, EnumEnchantmentType.BOW, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
        this.setName("mixed_combat");
        this.setRegistryName(new ResourceLocation(pitchants.MODID + ":mixed_combat"));

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
    public void onAttack(LivingHurtEvent event) {
        if (event.getSource().getTrueSource() instanceof EntityLivingBase) {
            EntityLivingBase player = (EntityLivingBase) event.getSource().getTrueSource();
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.MIXED_COMBAT, player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND));
            if (level > 0 && !player.isPotionActive(PotionInit.VENOM)) {
                if (event.getSource().getTrueSource() instanceof EntityPlayer && event.getSource().damageType.equals("player")) {
                    if (empowered) {
                        event.setAmount((float) (event.getAmount() + (event.getAmount() * (0.1 * level))));
                    }
                    empowered = false;
                }
                if (event.getSource().getTrueSource() instanceof EntityPlayer && event.getSource().isProjectile()) {

                    empowered = true;
                }
            }
        }
    }
}
