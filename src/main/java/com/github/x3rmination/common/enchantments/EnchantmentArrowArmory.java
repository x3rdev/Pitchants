package com.github.x3rmination.common.enchantments;

import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.pitchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.walkers.ItemStackData;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.client.config.GuiEditArrayEntries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


@Mod.EventBusSubscriber(modid=pitchants.MODID)
public class EnchantmentArrowArmory extends Enchantment {


    public EnchantmentArrowArmory() {
        super(Rarity.COMMON, EnumEnchantmentType.BOW, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
        this.setName("arrow_armory");
        this.setRegistryName(new ResourceLocation(pitchants.MODID + ":arrow_armory"));

        EnchantmentInit.ENCHANTMENTS.add(this);
    }


//    @Override
//    public void Items.BOW.onPlayerStoppedUsing(bow, event.getWorld(), event.getEntityLiving(), 10);


    @Override
    public void onEntityDamaged(EntityLivingBase user, Entity target, int level) {

        EntityPlayer player = ((EntityPlayer) user);
        ItemStack arrow = new ItemStack(Items.ARROW);
        arrow.setCount(3);
        user.getLastDamageSource();


        // 12% 3 arrows (-1)
        // 25% 5 arrows (-1)
        // 60% 8 arrows (-1)


//        if(player.isCreative() || ((EnchantmentHelper.getEnchantments(player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND))).get(Enchantments.INFINITY)) == 1){
//            target.attackEntityFrom(DamageSource.GENERIC, );
//        }
//        else(player.inventory.hasItemStack(arrow)) {
//
//        }
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
    public static void onAttack(LivingHurtEvent event) {



        if (event.getSource().getTrueSource() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.ARROW_ARMORY, player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND));

            if (level > 0) {
                ItemStack arrow = new ItemStack(Items.ARROW);
                arrow.setCount(3);
//                arrow.setCount((int) ((0.5*(Math.pow(level, 2)))+(0.5*level)+2));

                if(player.inventory.hasItemStack(arrow)) {
                    System.out.println("has arrows");
                    EntityLiving entity = (EntityLiving) event.getEntityLiving();
                    float damage = event.getAmount();
                    double percentDamage = (damage * ((11 * (Math.pow(level, 2))) - (20 * level) + 21) / 100);
                    entity.attackEntityFrom(DamageSource.GENERIC, (float) (damage + percentDamage));
                }

            }
        }
    }
}