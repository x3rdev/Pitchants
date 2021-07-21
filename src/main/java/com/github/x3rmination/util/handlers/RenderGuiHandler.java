package com.github.x3rmination.util.handlers;


import com.github.x3rmination.common.gui.StunnedGui;
import com.github.x3rmination.core.world.WorldEvents;
import com.github.x3rmination.init.PotionInit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RenderGuiHandler {

    @SubscribeEvent
    public void onRenderGui(RenderGameOverlayEvent.Pre renderEvent) {

        if (renderEvent.getType() != RenderGameOverlayEvent.ElementType.EXPERIENCE && isEffectActive() ) {
            new StunnedGui(Minecraft.getMinecraft());
        }

    }

    private boolean isEffectActive() {
        EntityPlayer currentPlayer = Minecraft.getMinecraft().player;
        return currentPlayer.isPotionActive(PotionInit.STUN);
    }
}
