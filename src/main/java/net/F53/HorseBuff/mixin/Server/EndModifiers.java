package net.F53.HorseBuff.mixin.Server;

import net.F53.HorseBuff.config.ModConfig;
import net.F53.HorseBuff.utils.Modifiers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Entity.class, priority = 960)
public class EndModifiers {

    @Inject(method = "startRiding(Lnet/minecraft/entity/Entity;ZZ)Z", at = @At(value = "RETURN"))
    public void startRiding(Entity entity, boolean force, boolean emitEvent, CallbackInfoReturnable<Boolean> cir) {
        if (entity.getEntityWorld() instanceof ServerWorld && entity instanceof AbstractHorseEntity horse) {
            if (ModConfig.getInstance().stepHeight) {
                EntityAttributeInstance stepHeight = horse.getAttributeInstance(EntityAttributes.STEP_HEIGHT);
                if (stepHeight != null) stepHeight.addTemporaryModifier(Modifiers.mountedStepHeight);
            }

            if (ModConfig.getInstance().breakSpeed) {
                EntityAttributeInstance breakSpeed = horse.getAttributeInstance(EntityAttributes.BLOCK_BREAK_SPEED);
                if (breakSpeed != null) breakSpeed.addTemporaryModifier(Modifiers.mountedBreakSpeed);
            }
        }
    }
}
