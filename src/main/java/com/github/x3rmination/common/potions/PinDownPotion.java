package com.github.x3rmination.common.potions;

import akka.japi.Effect;
import com.github.x3rmination.pitchants;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PinDownPotion extends Potion {

    public PinDownPotion(String name, boolean isBadPotion, int color, int iconIndexX, int iconIndexY) {
        super(isBadPotion, color);
        setPotionName("effect." + name);
        setIconIndex(iconIndexX, iconIndexY);
        setRegistryName(new ResourceLocation(pitchants.MODID + ":" + name));
    }

    @Override
    public boolean hasStatusIcon() {
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(pitchants.MODID + "textures/gui/potion_effects.png"));
        return true;
    }
}
