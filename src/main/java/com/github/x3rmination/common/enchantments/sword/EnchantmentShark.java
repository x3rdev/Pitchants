package com.github.x3rmination.common.enchantments.sword;

import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.pitchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid=pitchants.MODID)
public class EnchantmentShark extends Enchantment {

    private int subSixHealth = 0;
    private int iterator = 0;
    private AxisAlignedBB bounding;

    public EnchantmentShark() {
        super(Enchantment.Rarity.RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
        this.setName("shark");
        this.setRegistryName(new ResourceLocation(pitchants.MODID + ":shark"));
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
    public void onDamage(LivingHurtEvent event) {
        if(event.getEntityLiving() instanceof EntityLiving && event.getSource().getTrueSource() instanceof EntityLivingBase) {
            EntityLivingBase source = (EntityLivingBase) event.getSource().getTrueSource();
            World world = event.getEntityLiving().getEntityWorld();
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.SHARK, source.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND));
            if(level > 0) {
                bounding = new AxisAlignedBB(source.getPosition().getX() - 16D, source.getPosition().getY() - 16D, source.getPosition().getZ() - 16D, source.getPosition().getX() + 16D, source.getPosition().getY() + 16D, source.getPosition().getZ() + 16D);
                while(iterator < world.getEntitiesWithinAABB(EntityLivingBase.class, bounding).size() ) {
                    if(world.getEntitiesWithinAABB(EntityLivingBase.class, bounding).get(iterator).getHealth() <= 12){
                        subSixHealth+=1;
                    }
                    iterator+=1;
                }
                if(world.getEntitiesWithinAABB(EntityLivingBase.class, bounding).size() <= 1) {
                    subSixHealth = 0;
                    iterator = 0;
                }
                event.setAmount((float) (event.getAmount() + (event.getAmount() * subSixHealth * (Math.pow(level, 2) * 0.005) + (0.005 * level) + 0.01)));
            }

        }
    }
}
