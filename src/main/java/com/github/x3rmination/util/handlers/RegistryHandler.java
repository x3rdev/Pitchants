package com.github.x3rmination.util.handlers;


import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.init.PotionInit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class RegistryHandler {


    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
    }


    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event)
    {

    }

    @SubscribeEvent
    public static void registerEnchantment(RegistryEvent.Register<Enchantment> event) {
        event.getRegistry().registerAll(EnchantmentInit.ENCHANTMENTS.toArray(new Enchantment[0]));
    }

    public static void preInitRegistries(FMLPreInitializationEvent event) {
        PotionInit.registerPotions();
    }

//    public static void postInitRegistries(FMLPreInitializationEvent event) {
//
//    }
}
