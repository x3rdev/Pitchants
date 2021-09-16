package com.github.x3rmination.core.world;


import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.init.PotionInit;
import com.github.x3rmination.pitchants;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
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

    @SubscribeEvent
    public static void resurrectedActive(TickEvent.PlayerTickEvent event) {
        EntityPlayer player = event.player;
        if(player.isPotionActive(PotionInit.RESURRECTED_POTION)) {
            if(player.getHealth() - 2 <= player.getMaxHealth()) {
                player.setHealth(player.getHealth() + 2);
            } else {
                player.setHealth(player.getMaxHealth());
            }
        }
    }


}
