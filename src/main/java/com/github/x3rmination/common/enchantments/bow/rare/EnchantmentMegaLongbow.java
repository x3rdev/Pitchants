package com.github.x3rmination.common.enchantments.bow.rare;

import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.pitchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid=pitchants.MODID)
public class EnchantmentMegaLongbow extends Enchantment {

    private boolean isReady = true;
    public EnchantmentMegaLongbow() {
        super(Rarity.RARE, EnumEnchantmentType.BOW, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND});
        this.setName("mega_longbow");
        this.setRegistryName(new ResourceLocation(pitchants.MODID + ":mega_longbow"));

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
    public void onLetGo(ArrowLooseEvent event) throws InterruptedException {

        EntityPlayer player = event.getEntityPlayer();
        ItemStack mainHandBow = player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);
        int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.MEGA_LONGBOW, mainHandBow);
        if(level > 0) {
            player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 40, level, true, false));
            event.setCharge(27000);
            isReady = false;
        }

    }
    @SubscribeEvent
    public void onRight(PlayerInteractEvent.RightClickItem event) {
        EntityPlayer player = event.getEntityPlayer();
        ItemStack mainHandBow = player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);
        int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.MEGA_LONGBOW, mainHandBow);
        if(!isReady && level>0) {
            event.isCanceled();
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        isReady = true;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }

}
