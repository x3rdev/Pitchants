package com.github.x3rmination.common.enchantments.sword.rare;

import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.pitchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentBillionare extends Enchantment {

    public EnchantmentBillionare() {
        super(Rarity.VERY_RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
        this.setName("billionare");
        this.setRegistryName(new ResourceLocation(pitchants.MODID + ":billionare"));
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
    public void onDamage(LivingHurtEvent event) {
        if(event.getEntityLiving() instanceof EntityLiving && event.getSource().getTrueSource() instanceof EntityLivingBase) {
            EntityLivingBase source = (EntityLivingBase) event.getSource().getTrueSource();
            ItemStack itemStack = source.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.BILLIONARE, itemStack);
            if(level > 0) {
                itemStack.damageItem(itemStack.getMaxDamage()/20, source);
                event.setAmount((float) (event.getAmount()*((Math.pow(level, 2) * -0.005) + (0.355 * level) + 0.98)));
            }
        }
    }
}
