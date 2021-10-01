package com.github.x3rmination.common.enchantments.pants;

import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.init.PotionInit;
import com.github.x3rmination.pitchants;
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
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Mod.EventBusSubscriber(modid=pitchants.MODID)
public class EnchantmentHearts extends Enchantment {

    public EnchantmentHearts() {
        super(Rarity.RARE, EnumEnchantmentType.ARMOR_LEGS, new EntityEquipmentSlot[]{EntityEquipmentSlot.LEGS});
        this.setName("hearts");
        this.setRegistryName(new ResourceLocation(pitchants.MODID + ":hearts"));
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
        int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.HEARTS, living.getItemStackFromSlot(EntityEquipmentSlot.LEGS));
        if (level > 0 && !(living.isPotionActive(PotionInit.VENOM) || EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.SOMBER, living.getItemStackFromSlot(EntityEquipmentSlot.LEGS)) > 0)) {
            float attribute = (float) (0.25 * Math.pow(level, 2) - (0.25 * level) + 0.5);
            Collection<AttributeModifier> collection = eventItem.getAttributeModifiers(EntityEquipmentSlot.LEGS).get("generic.armor");
            if(!collection.isEmpty()){
                AttributeModifier attMod = (AttributeModifier) (new ArrayList(collection).get(0));
                eventItem.addAttributeModifier(SharedMonsterAttributes.MAX_HEALTH.getName(), new AttributeModifier(UUID.fromString("adf1cb25-c542-4543-83a5-af13479b2d4e"), "placeholder", attribute, 0), EntityEquipmentSlot.LEGS);
                eventItem.addAttributeModifier(SharedMonsterAttributes.ARMOR.getName(), new AttributeModifier(UUID.fromString("b8c33982-3db2-4a38-9f8d-8eb156697489"), "placeholder1", attMod.getAmount(), 0), EntityEquipmentSlot.LEGS);
            }
        }
    }
}
