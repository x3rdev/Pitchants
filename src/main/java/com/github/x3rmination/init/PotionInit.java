package com.github.x3rmination.init;


import com.github.x3rmination.common.potions.StunPotion;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.common.registry.ForgeRegistries;


public class PotionInit {
    public static final Potion STUN = new StunPotion("stun", true, 14980693, 0, 0).registerPotionAttributeModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "C821B10E-651F-4135-8D29-B0270B71A274", -1D, 2);
    // .registerPotionAttributeModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "C821B10E-651F-4135-8D29-B0270B71A274", -0.15000000596046448D, 2)
    public static void registerPotions(){
        registerPotion(STUN);
    }

    private static void registerPotion(Potion effect) {
        ForgeRegistries.POTIONS.register(effect);
    }
}
