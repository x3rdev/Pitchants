package com.github.x3rmination.common.enchantments.pants;

import com.github.x3rmination.Pitchants;
import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.init.PotionInit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Objects;

@Mod.EventBusSubscriber(modid= Pitchants.MODID)
public class EnchantmentDiamondAllergy extends Enchantment {

    public EnchantmentDiamondAllergy() {
        super(Rarity.RARE, EnumEnchantmentType.ARMOR_LEGS, new EntityEquipmentSlot[]{EntityEquipmentSlot.LEGS});
        this.setName("diamond_allergy");
        this.setRegistryName(new ResourceLocation(Pitchants.MODID + ":diamond_allergy"));
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
    public void onTick(LivingHurtEvent event) {
        if (event.getSource().getTrueSource() instanceof EntityLivingBase && event.getEntityLiving() != null) {
            EntityLivingBase victimEntity = event.getEntityLiving();
            EntityLivingBase attackerEntity = (EntityLivingBase) event.getSource().getTrueSource();
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.DIAMOND_ALLERGY, victimEntity.getItemStackFromSlot(EntityEquipmentSlot.LEGS));
            if (level > 0 && Objects.requireNonNull(attackerEntity).getHeldItemMainhand().isItemEqual(new ItemStack(Items.DIAMOND_SWORD)) && !(victimEntity.isPotionActive(PotionInit.VENOM) || EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.SOMBER, victimEntity.getItemStackFromSlot(EntityEquipmentSlot.LEGS)) > 0)) {
                float percent = (float) (0.10 * level);
                event.setAmount(event.getAmount() - (event.getAmount() * percent));
            }
        }
    }
}
