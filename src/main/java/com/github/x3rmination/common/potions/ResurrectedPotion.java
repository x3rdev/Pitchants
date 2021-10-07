package com.github.x3rmination.common.potions;

import com.github.x3rmination.Pitchants;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class ResurrectedPotion extends Potion {

    public ResurrectedPotion(String name, boolean isBadPotion, int color, int iconIndexX, int iconIndexY) {
        super(isBadPotion, color);
        setPotionName("effect." + name);
        setIconIndex(iconIndexX, iconIndexY);
        setRegistryName(new ResourceLocation(Pitchants.MODID + ":" + name));
    }

    @Override
    public boolean hasStatusIcon() {
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(Pitchants.MODID + "textures/gui/potion_effects.png"));
        return true;
    }
}
