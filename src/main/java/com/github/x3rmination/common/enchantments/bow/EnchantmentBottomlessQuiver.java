package com.github.x3rmination.common.enchantments.bow;

import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.pitchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class EnchantmentBottomlessQuiver extends Enchantment {

    private static boolean handled = false;

    public EnchantmentBottomlessQuiver() {
        super(Rarity.RARE, EnumEnchantmentType.BOW, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
        this.setName("bottomless_quiver");
        this.setRegistryName(new ResourceLocation(pitchants.MODID + ":bottomless_quiver"));

        EnchantmentInit.ENCHANTMENTS.add(this);
    }

    @Override
    public void onEntityDamaged(EntityLivingBase user, Entity target, int level) {
        if(!((EntityPlayer)user).isCreative()) {
            if (handled) {
                handled = false;
                return;
            }

            if (!(target instanceof EntityLiving)) return;
            DamageSource lastHit = ((EntityLiving) target).getLastDamageSource();
            assert lastHit != null;
            if (lastHit.isProjectile()) {
                ItemStack arrow = new ItemStack(Items.ARROW);
                int v = (int) (1.5 * (Math.pow(level, 2)) + (-2.5 * level) + 2);
                arrow.setCount(v);
                EntityPlayer player = (EntityPlayer) user;
                player.inventory.addItemStackToInventory(arrow);

            }
        }
        handled = true;
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel)
    {
        return 8 * enchantmentLevel;
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

    @Override
	protected boolean canApplyTogether(Enchantment ench)
	{
		return super.canApplyTogether(ench) && ench != Enchantments.INFINITY;
	}
}
