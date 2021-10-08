package com.github.x3rmination.common.enchantments.pants;

import com.github.x3rmination.Pitchants;
import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.init.PotionInit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

 


public class EnchantmentGottaGoFast extends Enchantment {

    public EnchantmentGottaGoFast() {
        super(Rarity.RARE, EnumEnchantmentType.ARMOR_LEGS, new EntityEquipmentSlot[]{EntityEquipmentSlot.LEGS});
        this.setName("gotta_go_fast");
        this.setRegistryName(new ResourceLocation(Pitchants.MODID + ":gotta_go_fast"));
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
    public void onTick(LivingEquipmentChangeEvent event) {
        EntityLivingBase living = event.getEntityLiving();
        ItemStack eventItem = event.getEntityLiving().getItemStackFromSlot(EntityEquipmentSlot.LEGS);
        int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.GOTTA_GO_FAST, living.getItemStackFromSlot(EntityEquipmentSlot.LEGS));
        if (level > 0 && !(living.isPotionActive(PotionInit.VENOM) || EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.SOMBER, living.getItemStackFromSlot(EntityEquipmentSlot.LEGS)) > 0)) {
            float attribute = (float) (0.02 * Math.pow(level, 2) + 0.02);
            Collection<AttributeModifier> collection = eventItem.getAttributeModifiers(EntityEquipmentSlot.LEGS).get("generic.armor");
            if(!collection.isEmpty()){
                AttributeModifier attMod = (new ArrayList<AttributeModifier>(collection).get(0));
                eventItem.addAttributeModifier("Movement Speed", new AttributeModifier(UUID.fromString("37FEC639-D92C-4682-AB9E-7C72788B5B7F"), "placeholder", attribute, 2), EntityEquipmentSlot.LEGS);
                eventItem.addAttributeModifier(SharedMonsterAttributes.ARMOR.getName(), new AttributeModifier(UUID.fromString("8D87F9E0-13AD-4A20-AB3F-41E7DC58D738"), "placeholder1", attMod.getAmount(), 0), EntityEquipmentSlot.LEGS);
            }
        }
    }
}

