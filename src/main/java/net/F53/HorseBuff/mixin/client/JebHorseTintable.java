package net.F53.HorseBuff.mixin.client;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Map;
import net.minecraft.client.renderer.entity.HorseRenderer;
import net.minecraft.client.renderer.entity.state.HorseRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.animal.equine.Variant;

import static net.F53.HorseBuff.utils.RenderUtils.isJeb;

@Mixin(value = HorseRenderer.class, priority = 960)
public abstract class JebHorseTintable {

    @Final @Shadow private static Map<Variant, Identifier> LOCATION_BY_VARIANT;

    @Redirect(method = "getTextureLocation(Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;)Lnet/minecraft/resources/Identifier;",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/HorseRenderer;getTextureLocation(Lnet/minecraft/client/renderer/entity/state/HorseRenderState;)Lnet/minecraft/resources/Identifier;"))
    Identifier jebHorseTintable(HorseRenderer instance, HorseRenderState horseEntityRenderState) {
        if (isJeb(horseEntityRenderState)) {
            return LOCATION_BY_VARIANT.get(Variant.WHITE);
        }
        return LOCATION_BY_VARIANT.get(horseEntityRenderState.variant);
    }
}
