package com.github.x3rmination.common.enchantments.pants;

import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.pitchants;
import net.minecraft.block.BlockTNT;
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
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


@Mod.EventBusSubscriber(modid=pitchants.MODID)
public class EnchantmentTNT extends Enchantment {

    private ItemStack tnt;

    public EnchantmentTNT() {
        super(Enchantment.Rarity.RARE, EnumEnchantmentType.ARMOR_LEGS, new EntityEquipmentSlot[]{EntityEquipmentSlot.LEGS});
        this.setName("tnt");
        this.setRegistryName(new ResourceLocation(pitchants.MODID + ":tnt"));
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
    public void onPlace(BlockEvent.EntityPlaceEvent event) {
        if(event.getEntity() instanceof EntityPlayer && event.getPlacedBlock().getBlock() instanceof BlockTNT) {
            EntityPlayer entityLiving = (EntityPlayer) event.getEntity();
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.TNT, entityLiving.getItemStackFromSlot(EntityEquipmentSlot.LEGS));
            if(level > 0 && entityLiving.getHeldItemMainhand().getDisplayName().equals((new TextComponentString("TNT").setStyle(new Style().setColor(TextFormatting.RED))).getFormattedText())) {
                BlockTNT tntBlock = (BlockTNT) event.getPlacedBlock().getBlock();
                tntBlock.removedByPlayer(event.getPlacedBlock(), event.getWorld(), event.getPos(), entityLiving, false);
                EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(event.getWorld(), event.getPos().getX() + 0.5F, event.getPos().getY(), event.getPos().getZ() + 0.5F, entityLiving);
                entitytntprimed.setFuse(40);
                event.getWorld().spawnEntity(entitytntprimed);
                new Thread(() -> {
                    try {
                        Thread.sleep(1500);
                        event.getWorld().removeEntity(entitytntprimed);
                        event.getWorld().createExplosion(null, event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), (float) ((0.2*level) + 0.3), false);
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
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.TNT, entityLiving.getItemStackFromSlot(EntityEquipmentSlot.LEGS));
            if(level > 0) {
                tnt = new ItemStack(Item.getItemById(46), level);
                tnt.setStackDisplayName((new TextComponentString("TNT").setStyle(new Style().setColor(TextFormatting.RED))).getFormattedText());
                entityLiving.addItemStackToInventory(tnt);
            }
        }
    }
}
