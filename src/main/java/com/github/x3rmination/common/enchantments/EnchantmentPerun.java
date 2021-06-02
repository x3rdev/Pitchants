package com.github.x3rmination.common.enchantments;


import com.github.x3rmination.core.damagesources.TrueDamage;
import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.pitchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;

import static com.github.x3rmination.core.damagesources.TrueDamage.TRUE_DAMAGE;

public class EnchantmentPerun extends Enchantment
{
	public EnchantmentPerun() {
		super(Rarity.UNCOMMON, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND});
		this.setName("perun");
		this.setRegistryName(new ResourceLocation(pitchants.MODID + ":perun"));
		
		EnchantmentInit.ENCHANTMENTS.add(this);
	}




	/* sets enemy on fire
	@Override
	public void onEntityDamaged(EntityLivingBase user, Entity target, int level){
		super.onEntityDamaged(user, target, level);
		if (true) {
			target.setFire(10);
		}
	}
	*/

	@Override
	public void onEntityDamaged(EntityLivingBase user, Entity target, int level) {
		float truedamage = (float) (1 + (0.5 * level));
		if(!user.isSwingInProgress) {
			target.attackEntityFrom(EnchantmentInit.TRUE_DAMAGE, truedamage);
			user.world.addWeatherEffect(new EntityLightningBolt(user.world, target.posX, target.posY, target.posZ, true));

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
	
//	@Override
//	protected boolean canApplyTogether(Enchantment ench)
//	{
//		return super.canApplyTogether(ench) && ench != Enchantments.FROST_WALKER && ench != Enchantments.DEPTH_STRIDER;
//	}
}