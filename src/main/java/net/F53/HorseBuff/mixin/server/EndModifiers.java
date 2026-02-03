package net.F53.HorseBuff.mixin.server;

import net.F53.HorseBuff.config.ModConfig;
import net.F53.HorseBuff.utils.Modifiers;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.equine.AbstractHorse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Entity.class, priority = 960)
public class EndModifiers {

    @Inject(method = "startRiding(Lnet/minecraft/world/entity/Entity;ZZ)Z", at = @At(value = "RETURN"))
    public void startRiding(Entity entity, boolean force, boolean emitEvent, CallbackInfoReturnable<Boolean> cir) {
        if (entity.level() instanceof ServerLevel && entity instanceof AbstractHorse horse) {
            if (ModConfig.getInstance().server.stepHeight) {
                AttributeInstance stepHeight = horse.getAttribute(Attributes.STEP_HEIGHT);
                if (stepHeight != null) stepHeight.addTransientModifier(Modifiers.mountedStepHeight);
            }

            if (ModConfig.getInstance().server.breakSpeed) {
                AttributeInstance breakSpeed = horse.getAttribute(Attributes.BLOCK_BREAK_SPEED);
                if (breakSpeed != null) breakSpeed.addTransientModifier(Modifiers.mountedBreakSpeed);
            }
        }
    }
}
