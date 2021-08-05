package com.github.x3rmination.common.enchantments.bow.rare;

import com.github.x3rmination.core.helpers.FindAmmo;
import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.pitchants;
import net.minecraft.block.BlockDispenser;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIRunAroundLikeCrazy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import scala.reflect.reify.codegen.GenTypes;

import static net.minecraft.item.ItemBow.getArrowVelocity;

@Mod.EventBusSubscriber(modid= pitchants.MODID)
public class EnchantmentVolley extends Enchantment {

    public EnchantmentVolley() {
        super(Enchantment.Rarity.UNCOMMON, EnumEnchantmentType.BOW, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
        this.setName("volley");
        this.setRegistryName(new ResourceLocation(pitchants.MODID + ":volley"));

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
    public void onArrowLoose(ArrowLooseEvent event) {
        FindAmmo ammoFinder = new FindAmmo();
        ItemStack itemArrow;
        int i = event.getCharge();
        float f = getArrowVelocity(i);
        int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.VOLLEY, event.getEntityLiving().getItemStackFromSlot(EntityEquipmentSlot.MAINHAND));
        if (level > 0 && !event.getWorld().isRemote) {
            itemArrow = ammoFinder.findAmmo(event.getEntityPlayer());
            if (ammoFinder.findAmmo(event.getEntityPlayer()).getItem() instanceof ItemArrow || event.getEntityPlayer().isCreative()) {
                if(level == 1) {
                    EntityArrow entityArrow = ((ItemArrow) itemArrow.getItem()).createArrow(event.getWorld(), ammoFinder.findAmmo(event.getEntityPlayer()), event.getEntityLiving());
                    entityArrow.setEnchantmentEffectsFromEntity(event.getEntityLiving(), 1);
                    entityArrow.shoot(event.getEntityLiving(), event.getEntityLiving().rotationPitch, event.getEntityLiving().rotationYaw, 1, f * 3.0F, 8);
                    event.getWorld().spawnEntity(entityArrow);

                    EntityArrow entityArrow1 = ((ItemArrow) itemArrow.getItem()).createArrow(event.getWorld(), ammoFinder.findAmmo(event.getEntityPlayer()), event.getEntityLiving());
                    entityArrow1.setEnchantmentEffectsFromEntity(event.getEntityLiving(), 1);
                    entityArrow1.shoot(event.getEntityLiving(), event.getEntityLiving().rotationPitch, event.getEntityLiving().rotationYaw, 1, f * 3.0F, 8);
                    event.getWorld().spawnEntity(entityArrow1);
                }
                if(level == 2) {
                    EntityArrow entityArrow = ((ItemArrow) itemArrow.getItem()).createArrow(event.getWorld(), ammoFinder.findAmmo(event.getEntityPlayer()), event.getEntityLiving());
                    entityArrow.setEnchantmentEffectsFromEntity(event.getEntityLiving(), 1);
                    entityArrow.shoot(event.getEntityLiving(), event.getEntityLiving().rotationPitch, event.getEntityLiving().rotationYaw, 1, f * 3.0F, 8);
                    event.getWorld().spawnEntity(entityArrow);

                    EntityArrow entityArrow1 = ((ItemArrow) itemArrow.getItem()).createArrow(event.getWorld(), ammoFinder.findAmmo(event.getEntityPlayer()), event.getEntityLiving());
                    entityArrow1.setEnchantmentEffectsFromEntity(event.getEntityLiving(), 1);
                    entityArrow1.shoot(event.getEntityLiving(), event.getEntityLiving().rotationPitch, event.getEntityLiving().rotationYaw, 1, f * 3.0F, 8);
                    event.getWorld().spawnEntity(entityArrow1);

                    EntityArrow entityArrow2 = ((ItemArrow) itemArrow.getItem()).createArrow(event.getWorld(), ammoFinder.findAmmo(event.getEntityPlayer()), event.getEntityLiving());
                    entityArrow2.setEnchantmentEffectsFromEntity(event.getEntityLiving(), 1);
                    entityArrow2.shoot(event.getEntityLiving(), event.getEntityLiving().rotationPitch, event.getEntityLiving().rotationYaw, 1, f * 3.0F, 8);
                    event.getWorld().spawnEntity(entityArrow2);
                }
                if(level > 2) {
                    EntityArrow entityArrow = ((ItemArrow) itemArrow.getItem()).createArrow(event.getWorld(), ammoFinder.findAmmo(event.getEntityPlayer()), event.getEntityLiving());
                    entityArrow.setEnchantmentEffectsFromEntity(event.getEntityLiving(), 1);
                    entityArrow.shoot(event.getEntityLiving(), event.getEntityLiving().rotationPitch, event.getEntityLiving().rotationYaw, 1, f * 3.0F, 8);
                    event.getWorld().spawnEntity(entityArrow);

                    EntityArrow entityArrow1 = ((ItemArrow) itemArrow.getItem()).createArrow(event.getWorld(), ammoFinder.findAmmo(event.getEntityPlayer()), event.getEntityLiving());
                    entityArrow1.setEnchantmentEffectsFromEntity(event.getEntityLiving(), 1);
                    entityArrow1.shoot(event.getEntityLiving(), event.getEntityLiving().rotationPitch, event.getEntityLiving().rotationYaw, 1, f * 3.0F, 8);
                    event.getWorld().spawnEntity(entityArrow1);

                    EntityArrow entityArrow2 = ((ItemArrow) itemArrow.getItem()).createArrow(event.getWorld(), ammoFinder.findAmmo(event.getEntityPlayer()), event.getEntityLiving());
                    entityArrow2.setEnchantmentEffectsFromEntity(event.getEntityLiving(), 1);
                    entityArrow2.shoot(event.getEntityLiving(), event.getEntityLiving().rotationPitch, event.getEntityLiving().rotationYaw, 1, f * 3.0F, 8);
                    event.getWorld().spawnEntity(entityArrow2);

                    EntityArrow entityArrow3 = ((ItemArrow) itemArrow.getItem()).createArrow(event.getWorld(), ammoFinder.findAmmo(event.getEntityPlayer()), event.getEntityLiving());
                    entityArrow3.setEnchantmentEffectsFromEntity(event.getEntityLiving(), 1);
                    entityArrow3.shoot(event.getEntityLiving(), event.getEntityLiving().rotationPitch, event.getEntityLiving().rotationYaw, 1, f * 3.0F, 8);
                    event.getWorld().spawnEntity(entityArrow3);
                }





            }
        }
    }
}
