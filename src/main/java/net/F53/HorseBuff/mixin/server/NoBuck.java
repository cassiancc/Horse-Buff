package net.F53.HorseBuff.mixin.server;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.F53.HorseBuff.config.ModConfig;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.equine.AbstractHorse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = AbstractHorse.class, priority = 960)
public abstract class NoBuck {

    @Shadow protected boolean allowStandSliding;
    @Shadow public abstract boolean isTamed();
    @Shadow public abstract LivingEntity getControllingPassenger();

    @ModifyReturnValue(method = "isStanding", at = @At("RETURN"))
    private boolean isAngry(boolean original) {
        if (ModConfig.getInstance().client.noBuck
            && allowStandSliding
            && isTamed()
            && getControllingPassenger() != null) {
            return false;
        }
        return original;
    }
}
