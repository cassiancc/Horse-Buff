package net.F53.HorseBuff.utils;

import net.F53.HorseBuff.config.ModConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.state.CamelRenderState;
import net.minecraft.client.renderer.entity.state.EquineRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.entity.state.LlamaRenderState;

public class RenderUtils {
    public static boolean isJeb(LivingEntityRenderState entityRenderState) {
        return ModConfig.getInstance().client.jeb_Horses && entityRenderState.nameTag != null && "jeb_".equals(entityRenderState.nameTag.getString());
    }

    public static int getAlpha(boolean isPlayerPassenger) {
        ModConfig.FadeConfig pitchFadeConfig = ModConfig.getInstance().pitchFade;
        if (!pitchFadeConfig.enabled) return 255;
        Minecraft client = Minecraft.getInstance();
        if (client.player == null || !client.options.getCameraType().isFirstPerson() || !isPlayerPassenger)
            return 255;

        int minAlpha = 255 - Math.round(pitchFadeConfig.maxTransparency * 2.25f);
        int rate = (255 - minAlpha) / (pitchFadeConfig.startAngle - pitchFadeConfig.endAngle);
        int unclampedAlpha = Math.round(rate * (client.player.xBob - pitchFadeConfig.endAngle));

        return Math.min(Math.max(unclampedAlpha, minAlpha), 255);
    }

    public static boolean isRideableEntityRenderState(LivingEntityRenderState livingEntityRenderState) {
        return livingEntityRenderState instanceof EquineRenderState ||
                livingEntityRenderState instanceof LlamaRenderState ||
                livingEntityRenderState instanceof CamelRenderState;
    }
}
