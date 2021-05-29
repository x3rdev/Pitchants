package com.github.x3rmination.core.world;


import com.github.x3rmination.common.gui.StunnedGui;
import com.github.x3rmination.init.PotionInit;
import com.github.x3rmination.pitchants;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber(modid=pitchants.MODID)
public class WorldEvents {



    @SubscribeEvent
    public static void stunPotionActive(TickEvent.PlayerTickEvent event) {



        boolean isActive = false;

        if(event.player.isPotionActive(PotionInit.STUN)) {
            isActive = true;
        }

        if(isActive && !event.player.isCreative()) {
            event.player.closeScreen();
            event.player.stopActiveHand();
        }

    }


}
