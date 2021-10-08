package com.github.x3rmination.util.handlers;


import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.init.PotionInit;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public final class RegistryHandler {

    private RegistryHandler() {

    }

    @SubscribeEvent
    public static void registerEnchantment(RegistryEvent.Register<Enchantment> event) {
        event.getRegistry().registerAll(EnchantmentInit.ENCHANTMENTS.toArray(new Enchantment[0]));
        event.getRegistry().registerAll(EnchantmentInit.DARK_ENCHANTMENTS.toArray(new Enchantment[0]));
    }

    public static void preInitRegistries() {
        PotionInit.registerPotions();
    }
}
