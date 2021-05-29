package com.github.x3rmination.common.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

public class StunnedGui extends Gui {

    String text = "Stunned!";
    public StunnedGui(Minecraft mc) {
        ScaledResolution scaled = new ScaledResolution(mc);
        mc.getTextureManager().bindTexture(new ResourceLocation("pitchants:textures/gui/stun_overlay.png"));
        mc.ingameGUI.drawCenteredString(mc.fontRenderer, text, scaled.getScaledWidth()/2, (scaled.getScaledHeight()/2) + 30, Integer.parseInt("A60000", 16));
    }
}
