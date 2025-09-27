package net.F53.HorseBuff.utils;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Unique;

public class Modifiers {
    public static final EntityAttributeModifier mountedStepHeight = new EntityAttributeModifier(Identifier.of("horse-buff", "mounted-step-height"), 0.1, EntityAttributeModifier.Operation.ADD_VALUE);
    public static final EntityAttributeModifier mountedBreakSpeed = new EntityAttributeModifier(Identifier.of("horse-buff", "mounted-break-speed"), 5, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE);
}
