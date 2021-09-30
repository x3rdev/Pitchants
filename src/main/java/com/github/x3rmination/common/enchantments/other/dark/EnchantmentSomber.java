package com.github.x3rmination.common.enchantments.other.dark;

import com.github.x3rmination.core.helpers.CheckEnchantments;
import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.pitchants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiUtilRenderComponents;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.jetbrains.annotations.NotNull;


public class EnchantmentSomber extends Enchantment {

    public EnchantmentSomber() {
        super(Rarity.VERY_RARE, EnumEnchantmentType.ARMOR_LEGS, new EntityEquipmentSlot[]{EntityEquipmentSlot.LEGS});
        this.setName("somber");
        this.setRegistryName(new ResourceLocation(pitchants.MODID + ":somber"));

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
        return super.canApplyTogether(ench) && !EnchantmentInit.ENCHANTMENTS.contains(ench);
    }

    @SubscribeEvent
    public void onDamage(LivingHurtEvent event) {
        if(event.getEntityLiving() != null && event.getSource().getTrueSource() instanceof EntityLivingBase) {
            EntityLivingBase entityLiving = event.getEntityLiving();
            EntityLivingBase source = (EntityLivingBase) event.getSource().getTrueSource();
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.SOMBER, entityLiving.getItemStackFromSlot(EntityEquipmentSlot.LEGS));
            if(level > 0) {
                CheckEnchantments eC = new CheckEnchantments();
                if(eC.isNormalEnchantment(source.getItemStackFromSlot(EntityEquipmentSlot.LEGS))) {
                    event.setCanceled(true);
                    entityLiving.attackEntityFrom(DamageSource.GENERIC, event.getAmount());
                    return;
                }
                if(eC.isNormalEnchantment(source.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND))) {
                    event.setCanceled(true);
                    entityLiving.attackEntityFrom(DamageSource.GENERIC, event.getAmount());
                }
            }
        }
    }
}
