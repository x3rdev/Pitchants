package com.github.x3rmination.common.enchantments;

import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.pitchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Iterator;



@Mod.EventBusSubscriber(modid=pitchants.MODID)
public class EnchantmentArrowArmory extends Enchantment {

    public EnchantmentArrowArmory() {
        super(Rarity.UNCOMMON, EnumEnchantmentType.BOW, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
        this.setName("arrow_armory");
        this.setRegistryName(new ResourceLocation(pitchants.MODID + ":arrow_armory"));

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
    public void onAttack(LivingHurtEvent event) {

        if (event.getSource().getTrueSource() instanceof EntityPlayer && event.getSource().isProjectile()) {

            EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.ARROW_ARMORY, player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND));

            if (level > 0) {
                float damage = event.getAmount();
                double percentcalc = ((11 * (Math.pow(level, 2))) - (20 * level) + 21) / 100;
                int percentDamage = (int) (percentcalc * damage);
                int arrowCount = (int) ((0.5 * (Math.pow(level, 2))) + (0.5 * level) + 2);
                ItemStack arrow = new ItemStack(Items.ARROW);
                arrow.setCount(arrowCount);


                if (player.isCreative() || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND)) > 0) {
                    EntityLiving entity = (EntityLiving) event.getEntityLiving();
                    entity.attackEntityFrom(DamageSource.GENERIC, damage + percentDamage);
                } else if (itemStackMatching(player, arrowCount)) {
                    ItemStack playerArrows = new ItemStack(Items.ARROW);
                    try {
                        int inventorySlot = player.inventory.getSlotFor(new ItemStack(Items.ARROW));
                        playerArrows.setCount(player.inventory.getStackInSlot(inventorySlot).getCount() - arrowCount - 1);
                        player.inventory.setInventorySlotContents(inventorySlot, playerArrows);

                    } catch (ArrayIndexOutOfBoundsException exception) {
                        playerArrows.setCount(player.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND).getCount() - arrowCount - 1);
                        player.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, playerArrows);
                    }
                    EntityLiving entity = (EntityLiving) event.getEntityLiving();
                    entity.attackEntityFrom(DamageSource.GENERIC, damage + percentDamage);
                }

            }
        }
    }

    private boolean itemStackMatching(EntityPlayer player, int arrowCount) {

        ItemStack arrow = new ItemStack(Items.ARROW);
        arrow.setCount(arrowCount);


        Iterator<ItemStack> invIterator = player.inventory.mainInventory.iterator();
        Iterator<ItemStack> armorIterator = player.inventory.armorInventory.iterator();
        Iterator<ItemStack> offhandIterator = player.inventory.offHandInventory.iterator();

        while (invIterator.hasNext()) {

            ItemStack itemstack = invIterator.next();

            if (itemCountMatching(itemstack, arrow)) {
                return true;
            }

        }
        while (armorIterator.hasNext()) {

            ItemStack itemstack = armorIterator.next();

            if (itemCountMatching(itemstack, arrow)) {
                return true;
            }

        }
        while (offhandIterator.hasNext()) {

            ItemStack itemstack = offhandIterator.next();

            if (itemCountMatching(itemstack, arrow)) {
                return true;
            }

        }

        return false;
    }

    private boolean itemCountMatching(ItemStack itemStack1, ItemStack arrowstack) {
        return !itemStack1.isEmpty() && !arrowstack.isEmpty() && itemStack1.getItem() == arrowstack.getItem() && itemStack1.getCount() >= arrowstack.getCount();
    }
}