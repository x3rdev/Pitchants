package com.github.x3rmination.common.enchantments.pants.rare;

import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.init.PotionInit;
import com.github.x3rmination.pitchants;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class EnchantmentAssassin extends Enchantment {

    private boolean isReady = true;
    private static boolean handled = false;
    public EnchantmentAssassin() {
        super(Rarity.VERY_RARE, EnumEnchantmentType.ARMOR_LEGS, new EntityEquipmentSlot[]{EntityEquipmentSlot.LEGS});
        this.setName("assassin");
        this.setRegistryName(new ResourceLocation(pitchants.MODID + ":assassin"));

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

    @Override
    public void onUserHurt(EntityLivingBase user, Entity attacker, int level) {
        if(user.isSneaking() && isReady && !(user.isPotionActive(PotionInit.VENOM) || EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.SOMBER, user.getItemStackFromSlot(EntityEquipmentSlot.LEGS)) > 0)) {
            if (handled) {
                handled = false;
                return;
            }
            BlockPos pos = new BlockPos(attacker.posX+(-2*attacker.getLookVec().x), attacker.posY+10, attacker.posZ+(-2*attacker.getLookVec().z));
            user.setPosition(pos.getX(), pos.getY(), pos.getZ());
            new Thread(() -> {
                try {
                    Thread.sleep((long) (1000*((Math.pow(level, 2)*2.5)-(12.5*level)+20)));
                    isReady = true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }).start();

            handled = true;
        }
    }
}
