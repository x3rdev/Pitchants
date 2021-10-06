package com.github.x3rmination.common.enchantments.bow.rare;

import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.init.PotionInit;
import com.github.x3rmination.pitchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid= pitchants.MODID)
public class EnchantmentExplosive extends Enchantment {

    public EnchantmentExplosive() {
        super(Rarity.VERY_RARE, EnumEnchantmentType.BOW, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND});
        this.setName("explosive");
        this.setRegistryName(new ResourceLocation(pitchants.MODID + ":explosive"));
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
            if (arrow.shootingEntity instanceof EntityPlayer) {
                EntityPlayer entityLiving = (EntityPlayer) arrow.shootingEntity;
                int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.EXPLOSIVE, entityLiving.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND));
                if (level > 0 && !world.isRemote && !(entityLiving.isPotionActive(PotionInit.VENOM) || EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.SOMBER, entityLiving.getItemStackFromSlot(EntityEquipmentSlot.LEGS)) > 0)) {
                    world.createExplosion(null, arrow.posX, arrow.posY, arrow.posZ, (float) (level + 1.0), false);
                }
            }
        }
    }
}
