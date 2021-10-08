package com.github.x3rmination.common.enchantments.pants.rare;

import com.github.x3rmination.Pitchants;
import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.init.PotionInit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

 


public class EnchantmentPhoenix extends Enchantment {

    public EnchantmentPhoenix() {
        super(Rarity.VERY_RARE, EnumEnchantmentType.ARMOR_LEGS, new EntityEquipmentSlot[]{EntityEquipmentSlot.LEGS});
        this.setName("phoenix");
        this.setRegistryName(new ResourceLocation(Pitchants.MODID + ":phoenix"));

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
    public void onDeath(LivingDeathEvent event) {
        if(event.getEntityLiving() instanceof EntityPlayer) {
            EntityPlayer entityLiving = (EntityPlayer) event.getEntityLiving();
            ItemStack legs = entityLiving.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.PHOENIX, legs);
            int itemDura = legs.getMaxDamage()/10;
            if(itemDura < legs.getMaxDamage()-legs.getItemDamage() && level > 0 && !(entityLiving.isPotionActive(PotionInit.VENOM) || EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.SOMBER, entityLiving.getItemStackFromSlot(EntityEquipmentSlot.LEGS)) > 0)) {
                event.setCanceled(true);
                entityLiving.addPotionEffect(new PotionEffect(PotionInit.RESURRECTED, 60, 0));
                legs.damageItem(itemDura, entityLiving);
                entityLiving.sendStatusMessage(new TextComponentString("Resurrected").setStyle(new Style().setColor(TextFormatting.GREEN)), true);
            }
        }
    }

    @SubscribeEvent
    public void onHit(LivingHurtEvent event) {
        if(event.getSource().getTrueSource() instanceof EntityPlayer) {
            EntityPlayer entityLiving = (EntityPlayer) event.getSource().getTrueSource();
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.PHOENIX, entityLiving.getItemStackFromSlot(EntityEquipmentSlot.LEGS));
            if(level > 1 && entityLiving.isPotionActive(PotionInit.RESURRECTED) && !(entityLiving.isPotionActive(PotionInit.VENOM) || EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.SOMBER, entityLiving.getItemStackFromSlot(EntityEquipmentSlot.LEGS)) > 0)) {
                event.setAmount((float) (event.getAmount()+(event.getAmount()*((Math.pow(level, 2) * -0.025) + (0.175*level) - 0.15))));
            }
        }
    }
}
