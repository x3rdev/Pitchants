package com.github.x3rmination.common.enchantments.bow.rare;

import com.github.x3rmination.Pitchants;
import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.init.PotionInit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

 


public class EnchantmentDevilChicks extends Enchantment {

    public EnchantmentDevilChicks() {
        super(Rarity.VERY_RARE, EnumEnchantmentType.BOW, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND});
        this.setName("devil_chicks");
        this.setRegistryName(new ResourceLocation(Pitchants.MODID + ":devil_chicks"));
        EnchantmentInit.ENCHANTMENTS.add(this);
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 10 * enchantmentLevel;
    }

    @Override
    public int getMaxEnchantability(int enchantmentLevel) {
        return this.getMinEnchantability(enchantmentLevel) + 20;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @SubscribeEvent
    public void onImpact(ProjectileImpactEvent event) {
        if(event.getEntity() instanceof EntityArrow) {
            EntityArrow arrow = (EntityArrow) event.getEntity();
            World world = event.getEntity().world;
            if(arrow.shootingEntity instanceof EntityPlayer) {
                EntityPlayer entityPlayer = (EntityPlayer) arrow.shootingEntity;
                int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.DEVIL_CHICKS, entityPlayer.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND));
                if (level > 0 && !(entityPlayer.isPotionActive(PotionInit.VENOM) || EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.SOMBER, entityPlayer.getItemStackFromSlot(EntityEquipmentSlot.LEGS)) > 0)) {
                    int i = (int) (0.5 * Math.pow(level, 2) - (0.5 * level) + 1);
                    while (i > 0 && !world.isRemote) {
                        BlockPos pos = event.getEntity().getPosition();
                        EntityChicken devilChick = new EntityChicken(world);
                        devilChick.setGrowingAge(-24000);
                        float dPosX = (float) (pos.getX() + (Math.random() * 2));
                        float dPosY = (float) (pos.getY() + (Math.random() * 2));
                        float dPosZ = (float) (pos.getZ() + (Math.random() * 2));
                        devilChick.setLocationAndAngles(dPosX, dPosY, dPosZ, 0.0F, 0.0F);
                        world.spawnEntity(devilChick);
                        world.removeEntity(event.getEntity());
                        new Thread(() -> {
                            try {
                                Thread.sleep(1000);
                                world.createExplosion(null, devilChick.posX, devilChick.posY, devilChick.posZ, 1.5F, false);
                                devilChick.setHealth(-1);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                                Thread.currentThread().interrupt();
                            }
                        }).start();
                        i--;
                    }
                }
            }
        }
    }
}
