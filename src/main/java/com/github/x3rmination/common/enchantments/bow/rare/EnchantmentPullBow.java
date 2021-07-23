package com.github.x3rmination.common.enchantments.bow.rare;

import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.pitchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Objects;

@Mod.EventBusSubscriber(modid=pitchants.MODID)
public class EnchantmentPullBow extends Enchantment {


    public EnchantmentPullBow() {
        super(Rarity.RARE, EnumEnchantmentType.BOW, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND});
        this.setName("pull_bow");
        this.setRegistryName(new ResourceLocation(pitchants.MODID + ":pull_bow"));

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
    public void onKnockBack(LivingKnockBackEvent event) {
        if (event.getAttacker() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getAttacker();
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.PULL_BOW, player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND));
            if (level > 0) {

                float distance = (float) Math.sqrt(Math.pow(event.getEntity().posX-event.getAttacker().posX, 2)+Math.pow(event.getEntity().posY-event.getAttacker().posY, 2)+Math.pow(event.getEntity().posZ-event.getAttacker().posZ, 2));
                event.setStrength((float) (-distance*0.37));


            }
        }
    }
}
