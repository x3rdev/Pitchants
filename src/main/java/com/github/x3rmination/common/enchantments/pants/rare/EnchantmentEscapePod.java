package com.github.x3rmination.common.enchantments.pants.rare;

import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.init.PotionInit;
import com.github.x3rmination.pitchants;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

@Mod.EventBusSubscriber(modid=pitchants.MODID)
public class EnchantmentEscapePod extends Enchantment {

    private boolean isReady = true;


    public EnchantmentEscapePod() {
        super(Rarity.VERY_RARE, EnumEnchantmentType.ARMOR_LEGS, new EntityEquipmentSlot[]{EntityEquipmentSlot.LEGS});
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
    public void onHurt(LivingHurtEvent event) {
        if(event.getEntityLiving() != null) {
            EntityLivingBase entityLivingBase = event.getEntityLiving();
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.ESCAPE_POD, entityLivingBase.getItemStackFromSlot(EntityEquipmentSlot.LEGS));
            if (level > 0 && entityLivingBase.getHealth() <= 5 && isReady && !entityLivingBase.isPotionActive(PotionInit.VENOM)) {
                isReady = false;
                event.setCanceled(true);
                entityLivingBase.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, ((5*level)+15)*20, level));
                entityLivingBase.addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 15, 9));
                entityLivingBase.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 40, 4));
                entityLivingBase.getEntityWorld().spawnParticle(EnumParticleTypes.CLOUD, entityLivingBase.posX, entityLivingBase.posY, entityLivingBase.posZ, 1, 1, 1);

                AxisAlignedBB bounding = new AxisAlignedBB(entityLivingBase.getPosition().getX() - 2D, entityLivingBase.getPosition().getY() - 2D, entityLivingBase.getPosition().getZ() - 2D, entityLivingBase.getPosition().getX() + 2D, entityLivingBase.getPosition().getY() + 2D, entityLivingBase.getPosition().getZ() + 2D);
                List<Entity> dmgList = entityLivingBase.getEntityWorld().getEntitiesWithinAABBExcludingEntity(entityLivingBase, bounding);
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
                        Thread.sleep(100000);
                        isReady = true;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                }).start();
            }
            if(level > 0 && entityLivingBase.getHealth() <= 5 && !isReady) {
                Minecraft.getMinecraft().player.sendStatusMessage(new TextComponentString("Escape Pod is still on cooldown!"), true);
            }
        }
    }
}
