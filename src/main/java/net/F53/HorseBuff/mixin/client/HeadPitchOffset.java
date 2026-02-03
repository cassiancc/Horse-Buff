package net.F53.HorseBuff.mixin.client;

import net.F53.HorseBuff.config.ModConfig;
import net.F53.HorseBuff.render.entity.state.ExtendedRideableEntityRenderState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.animal.equine.AbstractEquineModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.EquineRenderState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = AbstractEquineModel.class, priority = 960)
public abstract class HeadPitchOffset {

    @Shadow @Final protected ModelPart headParts;

    // Thanks dorianpb#9929 for the tip on where to mixin
    @Inject(method = "setupAnim(Lnet/minecraft/client/renderer/entity/state/EquineRenderState;)V", at = @At("TAIL"))
    void headPitch(EquineRenderState livingHorseEntityRenderState, CallbackInfo ci) {
        if (livingHorseEntityRenderState instanceof ExtendedRideableEntityRenderState extendedRideableEntityRenderState) {
            if (extendedRideableEntityRenderState.horsebuff$isPlayerPassenger() && Minecraft.getInstance().options.getCameraType().isFirstPerson()) {
                this.headParts.xRot = Math.min(this.headParts.xRot + ModConfig.getInstance().client.horseHeadAngleOffset / 100f, 1.5f);
            }
        }
    }
}
