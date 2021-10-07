package com.github.x3rmination.common.enchantments.pants.rare;

import com.github.x3rmination.Pitchants;
import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.init.PotionInit;
import net.minecraft.block.BlockTNT;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid= Pitchants.MODID)
public class EnchantmentInstaboom extends Enchantment {

    private boolean triggered = false;
    public EnchantmentInstaboom() {
        super(Rarity.VERY_RARE, EnumEnchantmentType.ARMOR_LEGS, new EntityEquipmentSlot[]{EntityEquipmentSlot.LEGS});
        this.setName("instaboom");
        this.setRegistryName(new ResourceLocation(Pitchants.MODID + ":instaboom"));

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
    public void onPlace(BlockEvent.EntityPlaceEvent event) {
        if(event.getEntity() instanceof EntityPlayer && event.getPlacedBlock().getBlock() instanceof BlockTNT) {
            EntityPlayer entityLiving = (EntityPlayer) event.getEntity();
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.INSTABOOM, entityLiving.getItemStackFromSlot(EntityEquipmentSlot.LEGS));
            if(level > 0 && entityLiving.getHeldItemMainhand().getDisplayName().equals((new TextComponentString("Instaboom TNT").setStyle(new Style().setColor(TextFormatting.RED))).getFormattedText()) && !(entityLiving.isPotionActive(PotionInit.VENOM) || EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.SOMBER, entityLiving.getItemStackFromSlot(EntityEquipmentSlot.LEGS)) > 0)) {
                triggered = true;
                BlockTNT tntBlock = (BlockTNT) event.getPlacedBlock().getBlock();
                tntBlock.removedByPlayer(event.getPlacedBlock(), event.getWorld(), event.getPos(), entityLiving, false);
                EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(event.getWorld(), event.getPos().getX()+0.5f, event.getPos().getY(), event.getPos().getZ()+0.5f, entityLiving);
                entitytntprimed.setFuse(2);
                event.getWorld().spawnEntity(entitytntprimed);
                new Thread(() -> {
                    try {
                        Thread.sleep(1);
                        event.getWorld().removeEntity(entitytntprimed);
                        event.getWorld().createExplosion(null, event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), 0, false);
                        Minecraft.getMinecraft().player.addVelocity((-4*entityLiving.getLookVec().x), (-1*entityLiving.getLookVec().y), (-4*entityLiving.getLookVec().z));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                }).start();
            }
        }
    }
    @SubscribeEvent
    public void onDeath(LivingDeathEvent event) {
        if(event.getSource().getTrueSource() instanceof EntityPlayer) {
            EntityPlayer entityLiving = (EntityPlayer) event.getSource().getTrueSource();
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.INSTABOOM, entityLiving.getItemStackFromSlot(EntityEquipmentSlot.LEGS));
            if(level > 0 && !(entityLiving.isPotionActive(PotionInit.VENOM) || EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.SOMBER, entityLiving.getItemStackFromSlot(EntityEquipmentSlot.LEGS)) > 0)) {
                ItemStack tnt = new ItemStack(Item.getItemById(46), level);
                tnt.setStackDisplayName((new TextComponentString("Instaboom TNT").setStyle(new Style().setColor(TextFormatting.RED))).getFormattedText());
                if(EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.INSTABOOM, tnt) <= 0) {
                    tnt.addEnchantment(EnchantmentInit.INSTABOOM, 1);
                }
                entityLiving.addItemStackToInventory(tnt);
            }
        }
    }

    @SubscribeEvent
    public void onFall(LivingFallEvent event) {
        if(event.getEntityLiving() instanceof EntityPlayer) {
            EntityPlayer entityLiving = (EntityPlayer) event.getEntityLiving();
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.INSTABOOM, entityLiving.getItemStackFromSlot(EntityEquipmentSlot.LEGS));
            if(level > 0 && triggered && !(entityLiving.isPotionActive(PotionInit.VENOM) || EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.SOMBER, entityLiving.getItemStackFromSlot(EntityEquipmentSlot.LEGS)) > 0)) {
                event.setCanceled(true);
                new Thread(() -> {
                    try {
                        Thread.sleep(10);
                        triggered = false;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                }).start();
            }
        }
    }
}
