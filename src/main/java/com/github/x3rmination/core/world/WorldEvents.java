package com.github.x3rmination.core.world;


import com.github.x3rmination.init.PotionInit;
import com.github.x3rmination.pitchants;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid=pitchants.MODID)
public class WorldEvents {

    @SubscribeEvent
    public static void stunPotionActive(LivingEvent.LivingUpdateEvent event) {
        if(event.getEntityLiving().isPotionActive(PotionInit.STUN)) {
            if(event.getEntityLiving() instanceof EntityPlayer) {
                EntityPlayer entityPlayer = (EntityPlayer) event.getEntityLiving();
                entityPlayer.closeScreen();
            }
            event.getEntityLiving().stopActiveHand();
            event.getEntityLiving().setJumping(false);
        }
    }

    @SubscribeEvent
    public static void pinDownPotionActive(LivingEvent.LivingUpdateEvent event) {
        if(event.getEntityLiving().isPotionActive(PotionInit.PIN_DOWN)) {
            event.getEntityLiving().removeActivePotionEffect(MobEffects.SPEED);
            event.getEntityLiving().removeActivePotionEffect(MobEffects.JUMP_BOOST);
        }
    }

    @SubscribeEvent
    public static void resurrectedPotionActive(LivingEvent.LivingUpdateEvent event) {
        EntityLivingBase entityLivingBase = event.getEntityLiving();
        if(entityLivingBase.isPotionActive(PotionInit.RESURRECTED)) {
            if(entityLivingBase.getHealth() - 2 <= entityLivingBase.getMaxHealth()) {
                entityLivingBase.setHealth(entityLivingBase.getHealth() + 2);
            } else {
                entityLivingBase.setHealth(entityLivingBase.getMaxHealth());
            }
        }
    }
    @SubscribeEvent
    public static void bleedingPotionActive(LivingEvent.LivingUpdateEvent event) {
        EntityLivingBase entityLivingBase = event.getEntityLiving();
        if(entityLivingBase.isPotionActive(PotionInit.BLEEDING)) {
            entityLivingBase.setAbsorptionAmount(0);
        }
    }
}
