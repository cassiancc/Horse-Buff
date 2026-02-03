package net.F53.HorseBuff.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.F53.HorseBuff.ClientInit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = Minecraft.class, priority = 960)
public abstract class InventoryAccessor {

    @WrapOperation(method = "handleKeybinds()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/MultiPlayerGameMode;isServerControlledInventory()Z"))
    boolean playerInventoryAccess(MultiPlayerGameMode instance, Operation<Boolean> original) {
        if (ClientInit.horsePlayerInventory.isDown()) {
            return false;
        } else {
            return original.call(instance);
        }
    }
}
