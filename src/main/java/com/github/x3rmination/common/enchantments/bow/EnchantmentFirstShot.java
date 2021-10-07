package com.github.x3rmination.common.enchantments.bow;

import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.init.PotionInit;
import com.github.x3rmination.Pitchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid= Pitchants.MODID)
public class EnchantmentFirstShot extends Enchantment {


    public EnchantmentFirstShot() {
        super(Rarity.RARE, EnumEnchantmentType.BOW, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
        this.setName("first_shot");
        this.setRegistryName(new ResourceLocation(Pitchants.MODID + ":first_shot"));

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
    public void onAttack(LivingHurtEvent event) {
        if (event.getSource().getTrueSource() instanceof EntityPlayer && event.getSource().isProjectile()) {

            EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.FIRST_SHOT, player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND));
            if (level > 0 && (event.getEntityLiving().getHealth() + 1 >= event.getEntityLiving().getMaxHealth()) && !(player.isPotionActive(PotionInit.VENOM) || EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.SOMBER, player.getItemStackFromSlot(EntityEquipmentSlot.LEGS)) > 0)) {
                int x = (int) event.getAmount();
                int calcAmount = (int) (1.5*(Math.pow(x, 2)) + 1.5*x + 7);
                event.getEntityLiving().attackEntityFrom(DamageSource.GENERIC, calcAmount);
            }
        }
    }
}
