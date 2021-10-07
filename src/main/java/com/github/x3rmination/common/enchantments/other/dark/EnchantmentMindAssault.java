package com.github.x3rmination.common.enchantments.other.dark;

import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.Pitchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.jetbrains.annotations.NotNull;

public class EnchantmentMindAssault extends Enchantment {

    public EnchantmentMindAssault() {
        super(Rarity.VERY_RARE, EnumEnchantmentType.ARMOR_LEGS, new EntityEquipmentSlot[]{EntityEquipmentSlot.LEGS});
        this.setName("mind_assault");
        this.setRegistryName(new ResourceLocation(Pitchants.MODID + ":mind_assault"));

        EnchantmentInit.DARK_ENCHANTMENTS.add(this);
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
        return 1;
    }

    @Override
    protected boolean canApplyTogether(@NotNull Enchantment ench) {
        return super.canApplyTogether(ench) && (!EnchantmentInit.ENCHANTMENTS.contains(ench) || !EnchantmentInit.RAGE_ENCHANTMENTS.contains(ench));
    }

    @SubscribeEvent
    public void onDamage(LivingHurtEvent event) {
        if(event.getEntityLiving() != null && event.getSource().getTrueSource() instanceof EntityLivingBase) {
            EntityLivingBase entityLiving = (EntityLivingBase) event.getSource().getTrueSource();
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.MIND_ASSAULT, entityLiving.getItemStackFromSlot(EntityEquipmentSlot.LEGS));
            if(level > 0) {
                AxisAlignedBB bounding = new AxisAlignedBB(entityLiving.getPosition().getX() - 11D, entityLiving.getPosition().getY() - 11D, entityLiving.getPosition().getZ() - 11D, entityLiving.getPosition().getX() + 11D, entityLiving.getPosition().getY() + 11D, entityLiving.getPosition().getZ() + 11D);

                event.setAmount((float) (event.getAmount() - (event.getAmount() * 0.6)));
                float dmgB = (float) (0.75 * entityLiving.getEntityWorld().getEntitiesWithinAABB(EntityLivingBase.class, bounding).size());
                if(dmgB > 16) {
                    dmgB = 16;
                }
                event.setAmount(event.getAmount() + dmgB);
            }
        }
    }
}
