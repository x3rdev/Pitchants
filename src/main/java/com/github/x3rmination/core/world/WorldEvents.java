package com.github.x3rmination.core.world;


import com.github.x3rmination.common.gui.StunnedGui;
import com.github.x3rmination.init.PotionInit;
import com.github.x3rmination.pitchants;
import net.minecraft.init.MobEffects;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber(modid=pitchants.MODID)
public class WorldEvents {



    @SubscribeEvent
    public static void stunPotionActive(TickEvent.PlayerTickEvent event) {
        if(event.player.isPotionActive(PotionInit.STUN)) {
            event.player.closeScreen();
            event.player.stopActiveHand();
        }
    }

    @SubscribeEvent
    public static void pinDownPotionActive(TickEvent.PlayerTickEvent event) {
        if(event.player.isPotionActive(PotionInit.PIN_DOWN)) {
            event.player.removeActivePotionEffect(MobEffects.SPEED);
            event.player.removeActivePotionEffect(MobEffects.JUMP_BOOST);
        }
    }

}
