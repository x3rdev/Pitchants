package com.github.x3rmination.common.enchantments.sword;

import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.init.PotionInit;
import com.github.x3rmination.Pitchants;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid= Pitchants.MODID)
public class EnchantmentGrasshopper extends Enchantment {

    public EnchantmentGrasshopper() {
        super(Enchantment.Rarity.RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
        this.setName("grasshopper");
        this.setRegistryName(new ResourceLocation(Pitchants.MODID + ":grasshopper"));
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
    public void onHurt(LivingHurtEvent event) {
        if(event.getEntityLiving() instanceof EntityLiving && event.getSource().getTrueSource() instanceof EntityLivingBase) {
            EntityLivingBase source = (EntityLivingBase) event.getSource().getTrueSource();
            EntityLivingBase livingBase = event.getEntityLiving();
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.GRASSHOPPER, source.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND));
            BlockPos posBelow = livingBase.getPosition().down();
            IBlockState blockStateBelow = livingBase.world.getBlockState(posBelow);
            if(level > 0 && Block.isEqualTo(blockStateBelow.getBlock(), Blocks.GRASS) && !(source.isPotionActive(PotionInit.VENOM) || EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.SOMBER, source.getItemStackFromSlot(EntityEquipmentSlot.LEGS)) > 0)) {
                event.setAmount((float) (event.getAmount() + (event.getAmount() * ((Math.pow(level, 2) * 0.01) + (0.01 * level) + 0.03))));
            }
        }
    }
}
