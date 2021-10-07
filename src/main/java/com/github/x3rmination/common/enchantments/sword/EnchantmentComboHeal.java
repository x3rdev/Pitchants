package com.github.x3rmination.common.enchantments.sword;

import com.github.x3rmination.Pitchants;
import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.init.PotionInit;
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

public class EnchantmentComboHeal extends Enchantment {

    public EnchantmentComboHeal() {
        super(Enchantment.Rarity.RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
        this.setName("combo_heal");
        this.setRegistryName(new ResourceLocation(Pitchants.MODID + ":combo_heal"));
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
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.COMBO_HEAL, itemStack);
            if(level > 0 && !(source.isPotionActive(PotionInit.VENOM) || EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.SOMBER, source.getItemStackFromSlot(EntityEquipmentSlot.LEGS)) > 0)) {
                source.heal((float) (0.4 * level));
                source.setAbsorptionAmount((float) (source.getAbsorptionAmount() + (0.4 * level)));
            }
        }
    }
}
