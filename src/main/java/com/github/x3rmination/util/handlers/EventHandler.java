package com.github.x3rmination.util.handlers;

import com.github.x3rmination.init.PotionInit;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class EventHandler {

    @SubscribeEvent(priority= EventPriority.HIGHEST)
    public void onEntityJump(LivingEvent.LivingJumpEvent event) {

        if(event.getEntityLiving().isPotionActive(PotionInit.STUN)) {
            event.getEntityLiving().setVelocity(0,0,0);
            System.out.println("jumpcancelled");
        }

    }
}
