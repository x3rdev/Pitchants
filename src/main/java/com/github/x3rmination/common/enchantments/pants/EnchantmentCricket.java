package com.github.x3rmination.common.enchantments.pants;

import com.github.x3rmination.Pitchants;
import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.init.PotionInit;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

 


public class EnchantmentCricket extends Enchantment {

    private boolean onGrass = false;
    public EnchantmentCricket() {
        super(Rarity.RARE, EnumEnchantmentType.ARMOR_LEGS, new EntityEquipmentSlot[]{EntityEquipmentSlot.LEGS});
        this.setName("cricket");
        this.setRegistryName(new ResourceLocation(Pitchants.MODID + ":cricket"));

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
    public void onTick(TickEvent.PlayerTickEvent event) {
        EntityPlayer player = event.player;
        BlockPos posBelow = player.getPosition().down();
        IBlockState blockStateBelow = player.world.getBlockState(posBelow);
        int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.CRICKET, player.getItemStackFromSlot(EntityEquipmentSlot.LEGS));
        if (level > 0 && Block.isEqualTo(blockStateBelow.getBlock(), Blocks.GRASS) && !(player.isPotionActive(PotionInit.VENOM) || EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.SOMBER, player.getItemStackFromSlot(EntityEquipmentSlot.LEGS)) > 0)) {
            if(!player.isPotionActive(MobEffects.REGENERATION)) {
                player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 40, 0, true, true));
            }
            onGrass = true;
        }
        if(!Block.isEqualTo(blockStateBelow.getBlock(), Blocks.GRASS)){
            onGrass = false;
        }
    }

    @SubscribeEvent
    public void onHit(LivingHurtEvent event) {
        if(event.getEntityLiving() != null) {
            EntityLivingBase entityLivingBase = (EntityLivingBase) event.getEntity();
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.CRICKET, entityLivingBase.getItemStackFromSlot(EntityEquipmentSlot.LEGS));
            if (level > 0 && onGrass && !(entityLivingBase.isPotionActive(PotionInit.VENOM) || EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.SOMBER, entityLivingBase.getItemStackFromSlot(EntityEquipmentSlot.LEGS)) > 0)) {
                float percent = (float) ((Math.pow(level, 2) * 0.08) - (0.22 * level) + 0.19);
                event.setAmount(event.getAmount()-(event.getAmount()*percent));
            }
        }
    }

}
