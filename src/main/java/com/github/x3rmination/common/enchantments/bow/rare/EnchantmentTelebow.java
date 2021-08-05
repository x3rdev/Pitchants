package com.github.x3rmination.common.enchantments.bow.rare;

import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.pitchants;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockJukebox;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.network.play.server.SPacketTitle;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid=pitchants.MODID)
public class EnchantmentTelebow extends Enchantment {

    private boolean isReady = true;
    private int coolDown = 0;
    public EnchantmentTelebow() {
        super(Rarity.RARE, EnumEnchantmentType.BOW, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND});
        this.setName("telebow");
        this.setRegistryName(new ResourceLocation(pitchants.MODID + ":telebow"));

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
    public void onProjectileLand(ProjectileImpactEvent event) {

        if (event.getEntity() instanceof EntityArrow && ((EntityArrow) event.getEntity()).shootingEntity instanceof EntityPlayer) {
            EntityArrow arrow = (EntityArrow) event.getEntity();
            EntityPlayer player = (EntityPlayer) arrow.shootingEntity;
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.TELE_BOW, player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND));
            if (isReady && level > 0 && player.isSneaking()) {
                coolDown = (int) (((Math.pow(level, 2) * 10) - (75 * level) + 155)*100);
                player.setPosition(event.getEntity().getPosition().getX(), event.getEntity().getPosition().getY(), event.getEntity().getPosition().getZ());
                isReady = false;
                new Thread(() -> {
                    try {
                        while(coolDown > 0) {
                            Thread.sleep(10);
                            coolDown-=1;
                        }
                        isReady = true;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
            if(!isReady && level > 0) {
                player.sendStatusMessage(new TextComponentString("Cooldown " + (coolDown/100) + " seconds"), true);
            }

        }
    }

    @Override
    public void onEntityDamaged(EntityLivingBase user, Entity target, int level) {
        coolDown -= 300;
    }
}

