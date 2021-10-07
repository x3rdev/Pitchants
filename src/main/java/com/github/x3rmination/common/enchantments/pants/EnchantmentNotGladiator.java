package com.github.x3rmination.common.enchantments.pants;

import com.github.x3rmination.Pitchants;
import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.init.PotionInit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid= Pitchants.MODID)
public class EnchantmentNotGladiator extends Enchantment {

    public EnchantmentNotGladiator() {
        super(Enchantment.Rarity.RARE, EnumEnchantmentType.ARMOR_LEGS, new EntityEquipmentSlot[]{EntityEquipmentSlot.LEGS});
        this.setName("not_gladiator");
        this.setRegistryName(new ResourceLocation(Pitchants.MODID + ":not_gladiator"));
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
        if(event.getEntityLiving() != null) {
            EntityLivingBase entityLiving = event.getEntityLiving();
            World world = entityLiving.getEntityWorld();
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.NOT_GLADIATOR, entityLiving.getItemStackFromSlot(EntityEquipmentSlot.LEGS));
            if(level > 0 && !(entityLiving.isPotionActive(PotionInit.VENOM) || EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.SOMBER, entityLiving.getItemStackFromSlot(EntityEquipmentSlot.LEGS)) > 0)) {
                AxisAlignedBB bounding = new AxisAlignedBB(entityLiving.getPosition().getX() - 16D, entityLiving.getPosition().getY() - 16D, entityLiving.getPosition().getZ() - 16D, entityLiving.getPosition().getX() + 16D, entityLiving.getPosition().getY() + 16D, entityLiving.getPosition().getZ() + 16D);
                double damageReduc = event.getAmount() * ((0.005*level) + 0.005) * world.getEntitiesWithinAABB(EntityLivingBase.class, bounding).size();
                event.setAmount((float) (event.getAmount() - damageReduc));
            }
        }
    }
}
