package net.F53.HorseBuff.mixin.server;

import net.F53.HorseBuff.utils.Modifiers;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.equine.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;

// adds DataPack attribute modifiers to player & horse while mounted to
// - increase horse's StepHeight by 10% (when enabled)
// - remove BreakSpeed debuff from not being grounded (when enabled)
@Mixin(value = Player.class, priority = 960)
public abstract class StartModifiers extends LivingEntity {

    protected StartModifiers(EntityType<? extends LivingEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    public void stopRiding() {
        if (!(super.level() instanceof ServerLevel && getVehicle() instanceof AbstractHorse horse)) {
            super.stopRiding();
            return;
        }

        AttributeInstance stepHeight = horse.getAttribute(Attributes.STEP_HEIGHT);
        if (stepHeight != null) stepHeight.removeModifier(Modifiers.mountedStepHeight);

        AttributeInstance breakSpeed = getAttribute(Attributes.BLOCK_BREAK_SPEED);
        if (breakSpeed != null) breakSpeed.removeModifier(Modifiers.mountedBreakSpeed);

        super.stopRiding();
    }
}
