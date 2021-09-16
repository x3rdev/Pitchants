package com.github.x3rmination.common.enchantments.pants;

import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.pitchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid=pitchants.MODID)
public class EnchantmentPitBlob extends Enchantment {
    private EntitySlime blob;
    private int kills = 0;
    private int slimeSize = 0;
    private EntityPlayer entityPlayer;
    private EntityLiving revengeTarget;

    public EnchantmentPitBlob() {
        super(Enchantment.Rarity.RARE, EnumEnchantmentType.ARMOR_LEGS, new EntityEquipmentSlot[]{EntityEquipmentSlot.LEGS});
        this.setName("pit_blob");
        this.setRegistryName(new ResourceLocation(pitchants.MODID + ":pit_blob"));
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
    public void onLivingDeath(LivingDeathEvent event) {
        if (event.getEntityLiving() instanceof EntityLiving && event.getSource().getTrueSource() instanceof EntityPlayer) {
            World world = event.getEntityLiving().getEntityWorld();
            EntityPlayer user = (EntityPlayer) event.getSource().getTrueSource();
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.PIT_BLOB, user.getItemStackFromSlot(EntityEquipmentSlot.LEGS));
            if (level > 0 && !world.isRemote && slimeSize <= 10) {
                entityPlayer = user;
                kills += 1;
                short killreq = (short) (4 - level);
                if (!world.getEntities(EntitySlime.class, EntitySelectors.IS_ALIVE).contains(blob) && kills >= killreq) {
                    blob = new EntitySlime(world);
                    blob.setDropItemsWhenDead(false);
                    blob.setLocationAndAngles(user.posX, user.posY, user.posZ, 0.0F, 0.0F);
                    world.spawnEntity(blob);
                    blob.setHealth(blob.getMaxHealth());
                    slimeSize = 0;
                    kills = 0;
                } else {
                    if (kills >= killreq) {
                        kills = 0;
                        new Thread(() -> {
                            try {
                                Thread.sleep(10);
                                slimeSize += 1;
                                NBTTagCompound tag = new NBTTagCompound();
                                tag.setInteger("Size", slimeSize);
                                blob.setLocationAndAngles(user.posX, user.posY, user.posZ, 0.0F, 0.0F);
                                blob.setDropItemsWhenDead(false);
                                blob.readEntityFromNBT(tag);
                                blob.setHealth(blob.getMaxHealth());
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                                Thread.currentThread().interrupt();
                            }
                        }).start();
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onTick(LivingAttackEvent event) {
        if(entityPlayer != null && event.getEntityLiving() == entityPlayer && event.getSource().getTrueSource() == blob) {
            event.setCanceled(true);

        }
    }

    @SubscribeEvent
    public void onTick(LivingEvent.LivingUpdateEvent event) {
        if (blob != null && event.getEntity() == blob) {
            AxisAlignedBB bounding = new AxisAlignedBB(blob.getPosition().getX() - 8D, blob.getPosition().getY(), blob.getPosition().getZ() - 8D, blob.getPosition().getX() + 16D, blob.getPosition().getY() + 8D, blob.getPosition().getZ() + 16D);
            revengeTarget = blob.getEntityWorld().getEntitiesWithinAABB(EntityLiving.class, bounding).get(0);
            if(revengeTarget == blob) {
                revengeTarget = blob.getEntityWorld().getEntitiesWithinAABB(EntityLiving.class, bounding).remove(0);
            }
            if(revengeTarget != blob) {
                blob.setAttackTarget(revengeTarget);
                AxisAlignedBB blobBB = blob.getEntityBoundingBox();
                if (blobBB.intersects(revengeTarget.getEntityBoundingBox())) {
                    revengeTarget.attackEntityFrom(DamageSource.GENERIC, (float) Math.sqrt(slimeSize));
                }
            }

        }

    }

}
