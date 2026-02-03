package net.F53.HorseBuff.mixin.server;

import net.F53.HorseBuff.config.ModConfig;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.equine.AbstractHorse;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

// Lower wander speed for saddled horses
@Mixin(value = LivingEntity.class, priority = 960)
public abstract class NoWander {

    @ModifyArg(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;travel(Lnet/minecraft/world/phys/Vec3;)V"))
    private Vec3 lowerWanderSpeed(Vec3 input) {
        if (ModConfig.getInstance().server.noWander
          && (horsebuff$thiz() instanceof AbstractHorse horse
          && horse.isSaddled()))
            return(Vec3.ZERO);
        return input;
    }

    @Unique
    private LivingEntity horsebuff$thiz() {
        return ((LivingEntity)(Object)this);
    }
}
