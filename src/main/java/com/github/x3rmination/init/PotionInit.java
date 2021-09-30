package com.github.x3rmination.init;


import com.github.x3rmination.common.potions.*;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.common.registry.ForgeRegistries;


public final class PotionInit {

    private PotionInit() {

    }
    public static final Potion STUN = new StunPotion("stun", true, 0x4d5a6e, 0, 0).registerPotionAttributeModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "C821B10E-651F-4135-8D29-B0270B71A274", -1D, 2);
    public static final Potion PIN_DOWN = new PinDownPotion("pin_down", true, 5123996, 1, 0);
    public static final Potion RESURRECTED = new ResurrectedPotion("resurrected", false, 0xff4400, 2, 0);
    public static final Potion BLEEDING = new BleedingPotion("bleeding", true, 0xff0000, 3, 0).registerPotionAttributeModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "FA395A1E-D8DE-4850-942F-034F5274938E", -0.1D, 2);
    public static final Potion VENOM = new VenomPotion("venom", true, 0x48ff00, 3, 0);

    public static void registerPotions(){
        registerPotion(STUN);
        registerPotion(PIN_DOWN);
        registerPotion(RESURRECTED);
        registerPotion(BLEEDING);
    }

    private static void registerPotion(Potion effect) {
        ForgeRegistries.POTIONS.register(effect);
    }
}
