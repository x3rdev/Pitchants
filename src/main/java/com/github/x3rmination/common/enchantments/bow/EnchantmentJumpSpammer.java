package com.github.x3rmination.common.enchantments.bow;

import com.github.x3rmination.Pitchants;
import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.init.PotionInit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid= Pitchants.MODID)
public class EnchantmentJumpSpammer extends Enchantment {

    public EnchantmentJumpSpammer() {
        super(Enchantment.Rarity.RARE, EnumEnchantmentType.BOW, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
        this.setName("jump_spammer");
        this.setRegistryName(new ResourceLocation(Pitchants.MODID + ":jump_spammer"));

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
        if(event.getEntityLiving() != null) {
            EntityLivingBase entityLiving = event.getEntityLiving();
            if(entityLiving.isAirBorne) {
                int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.JUMP_SPAMMER, entityLiving.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND));
                if (level > 0 && !(entityLiving.isPotionActive(PotionInit.VENOM) || EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.SOMBER, entityLiving.getItemStackFromSlot(EntityEquipmentSlot.LEGS)) > 0)) {
                    float baseDamage = event.getAmount();
                    event.setAmount(baseDamage * ((10*level) - 10));
                }
            }
        }

    }
    @SubscribeEvent
    public void onAttack(LivingHurtEvent event) {

        if (event.getSource().getTrueSource() instanceof EntityPlayer && event.getSource().isProjectile()) {
            EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.JUMP_SPAMMER, player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND));
            if (level > 0 && !(player.isPotionActive(PotionInit.VENOM) || EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.SOMBER, player.getItemStackFromSlot(EntityEquipmentSlot.LEGS)) > 0)) {
                int x = (int) event.getAmount();
                int calcAmount = (int) (Math.pow(x, 2) + 3*x + 6);
                event.getEntityLiving().attackEntityFrom(DamageSource.GENERIC, calcAmount);
            }
        }
    }
}
