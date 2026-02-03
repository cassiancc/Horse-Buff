package net.F53.HorseBuff.utils;

import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import org.spongepowered.asm.mixin.Unique;

public class Modifiers {
    public static final AttributeModifier mountedStepHeight = new AttributeModifier(Identifier.fromNamespaceAndPath("horse-buff", "mounted-step-height"), 0.1, AttributeModifier.Operation.ADD_VALUE);
    public static final AttributeModifier mountedBreakSpeed = new AttributeModifier(Identifier.fromNamespaceAndPath("horse-buff", "mounted-break-speed"), 5, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
}
