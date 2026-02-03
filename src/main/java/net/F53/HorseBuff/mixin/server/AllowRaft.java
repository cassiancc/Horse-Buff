package net.F53.HorseBuff.mixin.server;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.equine.AbstractHorse;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
import net.minecraft.world.entity.vehicle.boat.Raft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AbstractBoat.class)
public abstract class AllowRaft {

    @ModifyReturnValue(method = "hasEnoughSpaceFor", at = @At("RETURN"))
    private boolean allowRaft(boolean original, @Local(argsOnly = true) Entity entity) {
        if (entity instanceof AbstractHorse && horsebuff$thiz() instanceof Raft)
            return true;
        return original;
    }

    @Unique
    private AbstractBoat horsebuff$thiz() {
        return ((AbstractBoat) (Object) this);
    }
}
