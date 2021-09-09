package com.github.x3rmination.common.enchantments.pants.rare;

import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.pitchants;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.List;

public class EnchantmentEscapePod extends Enchantment {

    private boolean isReady = true;
    private static boolean handled = false;

    public EnchantmentEscapePod() {
        super(Rarity.RARE, EnumEnchantmentType.ARMOR_LEGS, new EntityEquipmentSlot[]{EntityEquipmentSlot.LEGS});
        this.setName("escape_pod");
        this.setRegistryName(new ResourceLocation(pitchants.MODID + ":escape_pod"));

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
    public void onDeath(LivingHurtEvent event) {
        if(event.getEntityLiving() instanceof EntityPlayer) {
            System.out.println(event.getEntityLiving()+"-");
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.ESCAPE_POD, player.getItemStackFromSlot(EntityEquipmentSlot.LEGS));
            if (level > 0 && player.getHealth() <= 5 && isReady) {
                isReady = false;
                event.setCanceled(true);
                player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, ((5*level)+15)*20, level));
                player.addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 15, 9));
                player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 40, 4));
                player.getEntityWorld().spawnParticle(EnumParticleTypes.CLOUD, player.posX, player.posY, player.posZ, 1, 1, 1);

                AxisAlignedBB bounding = new AxisAlignedBB(player.getPosition().getX() - 2D, player.getPosition().getY() - 2D, player.getPosition().getZ() - 2D, player.getPosition().getX() + 2D, player.getPosition().getY() + 2D, player.getPosition().getZ() + 2D);
                List<Entity> dmgList = player.getEntityWorld().getEntitiesWithinAABBExcludingEntity(player, bounding);
                System.out.println("- " + dmgList);
                int index = 0;
                while(index < dmgList.size()) {
                    Entity bT = dmgList.get(index);
                    index+=1;
                    if(bT instanceof EntityLiving) {
                        bT.attackEntityFrom(DamageSource.FLY_INTO_WALL, level * 2.0F);
                    }
                }
                new Thread(() -> {
                    try {
                        Thread.sleep(1);
                        isReady = true;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                }).start();

            }
        }
    }
}
