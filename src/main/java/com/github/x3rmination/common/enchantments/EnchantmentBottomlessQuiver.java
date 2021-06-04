package com.github.x3rmination.common.enchantments;

import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.pitchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class EnchantmentBottomlessQuiver extends Enchantment {

    public EnchantmentBottomlessQuiver() {
        super(Rarity.UNCOMMON, EnumEnchantmentType.BOW, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
        this.setName("bottomless_quiver");
        this.setRegistryName(new ResourceLocation(pitchants.MODID + ":bottomless_quiver"));

        EnchantmentInit.ENCHANTMENTS.add(this);
    }

    @Override
    public void onEntityDamaged(EntityLivingBase user, Entity target, int level) {

        ItemStack arrow = new ItemStack(Items.ARROW);
        int v = (int)(1.5 * (Math.pow(level, 2)) + (-2.5 * level) + 2);
        arrow.setCount(8);
        System.out.println("arrows to give: " + v);

        if(user instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)user;
            player.inventory.addItemStackToInventory(arrow);
        }
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel)
    {
        return 20 * enchantmentLevel;
    }

    @Override
    public int getMaxEnchantability(int enchantmentLevel)
    {
        return this.getMinEnchantability(enchantmentLevel) + 10;
    }

    @Override
    public int getMaxLevel()
    {
        return 3;
    }
}
