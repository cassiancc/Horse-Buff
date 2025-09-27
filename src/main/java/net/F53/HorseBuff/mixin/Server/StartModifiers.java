package net.F53.HorseBuff.mixin.Server;

import net.F53.HorseBuff.utils.Modifiers;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

// adds DataPack attribute modifiers to player & horse while mounted to
// - increase horse's StepHeight by 10% (when enabled)
// - remove BreakSpeed debuff from not being grounded (when enabled)
@Mixin(value = PlayerEntity.class, priority = 960)
public abstract class StartModifiers extends LivingEntity {

    protected StartModifiers(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void stopRiding() {
        if (!(super.getEntityWorld() instanceof ServerWorld && getVehicle() instanceof AbstractHorseEntity horse)) {
            super.stopRiding();
            return;
        }

        EntityAttributeInstance stepHeight = horse.getAttributeInstance(EntityAttributes.STEP_HEIGHT);
        if (stepHeight != null) stepHeight.removeModifier(Modifiers.mountedStepHeight);

        EntityAttributeInstance breakSpeed = getAttributeInstance(EntityAttributes.BLOCK_BREAK_SPEED);
        if (breakSpeed != null) breakSpeed.removeModifier(Modifiers.mountedBreakSpeed);

        super.stopRiding();
    }
}
