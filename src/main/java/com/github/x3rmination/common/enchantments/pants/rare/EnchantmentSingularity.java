package com.github.x3rmination.common.enchantments.pants.rare;

import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.init.PotionInit;
import com.github.x3rmination.Pitchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid= Pitchants.MODID)
public class EnchantmentSingularity extends Enchantment {

    public EnchantmentSingularity() {
        super(Rarity.VERY_RARE, EnumEnchantmentType.ARMOR_LEGS, new EntityEquipmentSlot[]{EntityEquipmentSlot.LEGS});
        this.setName("singularity");
        this.setRegistryName(new ResourceLocation(Pitchants.MODID + ":singularity"));

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
    public void onHit(LivingHurtEvent event) {
        if(event.getEntityLiving() != null) {
            EntityLivingBase entityLiving = event.getEntityLiving();
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.SINGULARITY, entityLiving.getItemStackFromSlot(EntityEquipmentSlot.LEGS));
            if(level > 0 && !(entityLiving.isPotionActive(PotionInit.VENOM) || EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.SOMBER, entityLiving.getItemStackFromSlot(EntityEquipmentSlot.LEGS)) > 0)) {
                int max = (int) ((Math.pow(level, 2) * 0.5) - (3.5 * level) + 9);
                if(event.getAmount() > max){
                    event.setAmount(max);
                }
            }
        }
    }
}
